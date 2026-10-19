package dev.baobab.doudou.Blocks;

import java.util.function.Function;

import dev.baobab.doudou.Blocks.Doudou.BatDoudou;
import dev.baobab.doudou.Blocks.Doudou.BeeDoudou;
import dev.baobab.doudou.Blocks.Doudou.ChickenDoudou;
import dev.baobab.doudou.Blocks.Doudou.DolphinDoudou;
import dev.baobab.doudou.Blocks.Doudou.RabbitDoudou;
import dev.baobab.doudou.Items.DoudouItems;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.references.BlockItemId;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class DoudouBlocks {

    public static final Block BEE_DOUDOU = register(DoudouBlockItemIds.BEE_DOUDOU, BeeDoudou::new , BlockBehaviour.Properties.of().sound(SoundType.WOOL));
    public static final Block CHICKEN_DOUDOU = register(DoudouBlockItemIds.CHICKEN_DOUDOU, ChickenDoudou::new , BlockBehaviour.Properties.of().sound(SoundType.WOOL));
    public static final Block BAT_DOUDOU = register(DoudouBlockItemIds.BAT_DOUDOU, BatDoudou::new , BlockBehaviour.Properties.of().sound(SoundType.WOOL));
    public static final Block DOLPHIN_DOUDOU = register(DoudouBlockItemIds.DOLPHIN_DOUDOU, DolphinDoudou::new , BlockBehaviour.Properties.of().sound(SoundType.WOOL));
    public static final Block RABBIT_DOUDOU = register(DoudouBlockItemIds.RABBIT_DOUDOU, RabbitDoudou::new , BlockBehaviour.Properties.of().sound(SoundType.WOOL));
    
	private static Block register(BlockItemId id, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties) {
	    properties.setId(id.block());
        Block block = blockFactory.apply(properties);
        Registry.register(BuiltInRegistries.BLOCK, id.block(), block);

        Item.Properties blockItemProperties = new Item.Properties().useBlockDescriptionPrefix().setId(id.item());
		BlockItem blockItem = new BlockItem(block, blockItemProperties);
		Registry.register(BuiltInRegistries.ITEM, id.item(), blockItem);

		return block;
	}

    public static void initialize() {
        CreativeModeTabEvents.modifyOutputEvent(DoudouItems.CREATIVE_TAB_KEY).register((creativeTab) -> {
            creativeTab.accept(BEE_DOUDOU.asItem());
            creativeTab.accept(CHICKEN_DOUDOU.asItem());
            creativeTab.accept(BAT_DOUDOU.asItem());
            creativeTab.accept(DOLPHIN_DOUDOU.asItem());
            creativeTab.accept(RABBIT_DOUDOU.asItem());
        });
    }
}
