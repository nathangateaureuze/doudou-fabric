package dev.baobab.doudou.mixin;

import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.datafixers.util.Either;

import dev.baobab.doudou.Blocks.Doudou.DoudouBlock;
import dev.baobab.doudou.Entities.Bed.BedBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Prediction;
import net.minecraft.util.Unit;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.attribute.BedRule;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Player.BedSleepingProblem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractBedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.block.entity.BlockEntity;

@Mixin (AbstractBedBlock.class)
public abstract class AbstractBedBlockMixin implements EntityBlock{

    @Nullable 
    @Override 
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState)
    {
        if (blockState.getValue(AbstractBedBlock.PART) == BedPart.HEAD) {
            return new BedBlockEntity(blockPos, blockState);
        }
        return null;
    }
    
    @Invoker("getNeighbourDirection")
    static Direction TemplateMod$getNeighbourDirection(final BedPart part, final Direction facing) {
        throw new AssertionError("Untransformed @Accessor");
    }

    @Inject(method="onStopSleeping", at = @At("HEAD"))
    public void TemplateMod$onStopSleeping(final Level level, final BlockPos pos, CallbackInfo ci)
    {
        if (level.getBlockEntity(pos) instanceof BedBlockEntity bedBlockEntity) {
            if (bedBlockEntity.getSleeper() == null) {
                return ;
            }
            if (bedBlockEntity.getSleeper().isSleepingLongEnough()) {
                return ;
            }
            bedBlockEntity.resetSleeper();
        }
    }

    @Inject(method="useWithoutItem", at = @At("HEAD"), cancellable = true)
    protected void TemplateMod$DoudouInteract$useWithoutItem(BlockState state, final Level level, BlockPos pos, final Player player, final BlockHitResult hitResult, CallbackInfoReturnable<InteractionResult> ci)
    {
        if (!player.level().isClientSide()) {
            if (state.getValue(AbstractBedBlock.PART) == BedPart.FOOT) {
                pos = pos.relative(TemplateMod$getNeighbourDirection(BedPart.FOOT, (Direction)state.getValue(HorizontalDirectionalBlock.FACING)));
            }
            if (doudouInteract(level, pos, player)) {
                level.playSound(null, pos, SoundEvents.WOOL_PLACE, SoundSource.BLOCKS);
                ci.setReturnValue(InteractionResult.SUCCESS);
                return ;
            }
        }
    }

    @WrapOperation(method="useWithoutItem", at = @At(value="INVOKE", target = "Lnet/minecraft/world/entity/player/Player;startSleepInBed(Lnet/minecraft/world/level/block/AbstractBedBlock;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/attribute/BedRule;Lnet/minecraft/core/BlockPos;)Lcom/mojang/datafixers/util/Either;"))
    protected Either<BedSleepingProblem, Unit> TemplateMod$AssignSleeper$useWithoutItem(Player instance, final AbstractBedBlock bedBlock, final BlockState bedBlockState, final BedRule rule, final BlockPos pos, Operation<Either<BedSleepingProblem, Unit>> operation)
    {
        Either<BedSleepingProblem,Unit> result = operation.call(instance, bedBlock, bedBlockState, rule, pos);
        if (result.left().isEmpty())
        {
            if (instance.level().getBlockEntity(pos) instanceof BedBlockEntity bedBlockEntity) 
            {
                bedBlockEntity.setSleeper(instance);
            }
        }
        return result;
    }

    private boolean doudouInteract(final Level level, BlockPos pos, final Player player)
    { 
        if ((level.getBlockEntity(pos) instanceof BedBlockEntity bedBlockEntity)) 
        {
            boolean bedHasItem = !bedBlockEntity.isEmpty();
            ItemStack playerItem = player.getItemInHand(player.getUsedItemHand());

            if (!playerItem.isEmpty() && !bedHasItem) 
            {
                if (Block.byItem(playerItem.getItem()) instanceof DoudouBlock) 
                {
                    bedBlockEntity.setTheItem(playerItem.copyWithCount(1));
                    playerItem.shrink(1);
                    return true;
                }
            } 
            else if(bedHasItem && playerItem.isEmpty() && player.getPose() == Pose.CROUCHING)
            {
                if(!player.getInventory().add(bedBlockEntity.getTheItem()))
                {
                    player.drop(bedBlockEntity.getTheItem(), false, Prediction.PREDICTED);
                }
                bedBlockEntity.clearContent();
                return true;
            }
        }
        return false;
    }
}