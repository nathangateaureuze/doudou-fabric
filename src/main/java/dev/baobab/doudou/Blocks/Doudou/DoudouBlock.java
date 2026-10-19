package dev.baobab.doudou.Blocks.Doudou;

import org.jspecify.annotations.Nullable;

import dev.baobab.doudou.Entities.Bed.BedBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public abstract class DoudouBlock extends Block implements SimpleWaterloggedBlock{

  public static final EnumProperty<Direction> FACING;
  public static final BooleanProperty WATERLOGGED;

  protected static final VoxelShape NORTH_SHAPE = box(7,0,7,9,2,9);
  protected static final VoxelShape SOUTH_SHAPE = box(7,0,7,9,2,9);
  protected static final VoxelShape WEST_SHAPE = box(7,0,7,9,2,9);
  protected static final VoxelShape EAST_SHAPE = box(7,0,7,9,2,9);

  public DoudouBlock(Properties properties) {
    super(properties);
    registerDefaultState(defaultBlockState().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
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
  public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
    FluidState replacedFluidState = context.getLevel().getFluidState(context.getClickedPos());
    return defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(WATERLOGGED, replacedFluidState.is(Fluids.WATER));
  }

  @Override
  protected FluidState getFluidState(BlockState state) {
    return (Boolean)state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
  }

  public abstract void DoudouFunction(Player player, BedBlockEntity bedBlockEntity);

  @Override
  protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
      super.createBlockStateDefinition(builder);
      builder.add(FACING);
      builder.add(WATERLOGGED);
  }

  static {
    FACING = BlockStateProperties.HORIZONTAL_FACING;
    WATERLOGGED = BlockStateProperties.WATERLOGGED;
  }
}
