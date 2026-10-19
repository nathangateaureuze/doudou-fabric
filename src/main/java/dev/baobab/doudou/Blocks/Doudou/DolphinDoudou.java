package dev.baobab.doudou.Blocks.Doudou;

import dev.baobab.doudou.Entities.Bed.BedBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class DolphinDoudou extends DoudouBlock{
    
    public static final VoxelShape NORTH_SHAPE = box(4.5,0,3,11.5,3,13);
    public static final VoxelShape SOUTH_SHAPE = box(4.5,0,3,11.5,3,13);
    public static final VoxelShape WEST_SHAPE = box(3,0,4.5,13,3,11.5);
    public static final VoxelShape EAST_SHAPE = box(3,0,4.5,13,3,11.5);

    public DolphinDoudou(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getBlockSupportShape(BlockState state, BlockGetter level, BlockPos pos) {

        Direction direction = state.getValue(FACING);
        VoxelShape voxelShape;

    switch (direction) {

        case WEST:
        voxelShape = WEST_SHAPE.singleEncompassing();
        break;
        case EAST:
        voxelShape = EAST_SHAPE.singleEncompassing();
        break;
        case SOUTH:
        voxelShape = SOUTH_SHAPE.singleEncompassing();
        break;
        default:
        voxelShape = NORTH_SHAPE.singleEncompassing();
        break;
    }

        return voxelShape;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
    return this.getBlockSupportShape(state, level, pos);
    }

    @Override
    public void DoudouFunction(Player player, BedBlockEntity bedBlockEntity) {
        player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 3600));
    }
}
