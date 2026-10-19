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

public class BatDoudou extends DoudouBlock{

    public static final VoxelShape NORTH_SHAPE = box(3,0,4,13,1,12);
    public static final VoxelShape SOUTH_SHAPE = box(3,0,4,13,1,12);
    public static final VoxelShape WEST_SHAPE = box(4,0,3,12,1,13);
    public static final VoxelShape EAST_SHAPE = box(4,0,3,12,1,13);

    public BatDoudou(Properties properties) {
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
        player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 3600));
    }
}
