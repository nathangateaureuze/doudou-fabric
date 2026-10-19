package dev.baobab.doudou.Blocks.Doudou;

import dev.baobab.doudou.Entities.Bed.BedBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class RabbitDoudou extends DoudouBlock{

    public static final VoxelShape SHAPE = box(6,0,6,10,7,10);

    public RabbitDoudou(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public void DoudouFunction(Player player, BedBlockEntity bedBlockEntity) {
        player.addEffect(new MobEffectInstance(MobEffects.JUMP_BOOST, 3600));
    }
}
