package dev.baobab.doudou.Items;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import dev.baobab.doudou.Doudou;

public class DoudouItemIds {

	public static ResourceKey<Item> create(String name) {
		
		return ResourceKey.create(Registries.ITEM, Doudou.id(name));
	}
}