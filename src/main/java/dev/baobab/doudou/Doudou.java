package dev.baobab.doudou;

import net.fabricmc.api.ModInitializer;

import net.minecraft.resources.Identifier;

import dev.baobab.doudou.Blocks.DoudouBlocks;
import dev.baobab.doudou.Entities.DoudouBlockEntities;
import dev.baobab.doudou.Items.DoudouItems;

public class Doudou implements ModInitializer {

	public static final String MOD_ID = "doudou";

	@Override
	public void onInitialize() {
		DoudouItems.initialize();
		DoudouBlocks.initialize();
		DoudouBlockEntities.initialize();
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
