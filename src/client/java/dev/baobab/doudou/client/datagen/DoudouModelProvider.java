package dev.baobab.doudou.client.datagen;

import dev.baobab.doudou.Blocks.DoudouBlocks;
import dev.baobab.doudou.Blocks.Doudou.DoudouBlock;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.BlockModelDefinitionGenerator;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;

public class DoudouModelProvider extends FabricModelProvider{

    public DoudouModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        
        blockModelGenerators.blockStateOutput.accept(createOrientableBlockState(DoudouBlocks.BEE_DOUDOU));
        blockModelGenerators.blockStateOutput.accept(createOrientableBlockState(DoudouBlocks.CHICKEN_DOUDOU));
        blockModelGenerators.blockStateOutput.accept(createOrientableBlockState(DoudouBlocks.BAT_DOUDOU));
        blockModelGenerators.blockStateOutput.accept(createOrientableBlockState(DoudouBlocks.DOLPHIN_DOUDOU));
        blockModelGenerators.blockStateOutput.accept(createOrientableBlockState(DoudouBlocks.RABBIT_DOUDOU));
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
    }

    private static BlockModelDefinitionGenerator createOrientableBlockState(Block doudouBlock) {
	MultiVariant doudouModel = BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(doudouBlock));
	return MultiVariantGenerator.dispatch(doudouBlock)
        .with(PropertyDispatch.initial(DoudouBlock.FACING)
            .select(Direction.NORTH, doudouModel)
            .select(Direction.EAST, doudouModel.with(BlockModelGenerators.Y_ROT_90))
            .select(Direction.SOUTH, doudouModel.with(BlockModelGenerators.Y_ROT_180))
            .select(Direction.WEST, doudouModel.with(BlockModelGenerators.Y_ROT_270))
        );
    }
}
