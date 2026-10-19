package dev.baobab.doudou.Items;

import java.util.function.Function;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import dev.baobab.doudou.Doudou;
import dev.baobab.doudou.Blocks.DoudouBlocks;

public class DoudouItems {

	public static final ResourceKey<CreativeModeTab> CREATIVE_TAB_KEY = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), Doudou.id("creative_tab"));

	public static final CreativeModeTab DOUDOU_CREATIVE_TAB = FabricCreativeModeTab.builder()
		.icon(() -> new ItemStack(DoudouBlocks.RABBIT_DOUDOU))
		.title(Component.translatable(Doudou.MOD_ID + "." + "creative_tab"))
		.build();

	public static Item register(ResourceKey<Item> itemKey, Function<Item.Properties, Item> itemFactory, Item.Properties settings) {

		Item item = itemFactory.apply(settings.setId(itemKey));
		Registry.register(BuiltInRegistries.ITEM, itemKey, item);
		return item;
	}

    public static void initialize() {
		Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, CREATIVE_TAB_KEY, DOUDOU_CREATIVE_TAB);
    }

}