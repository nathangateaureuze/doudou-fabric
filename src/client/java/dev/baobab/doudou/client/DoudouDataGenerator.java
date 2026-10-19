package dev.baobab.doudou.client;

import dev.baobab.doudou.client.datagen.DoudouModelProvider;
import dev.baobab.doudou.client.datagen.DoudouRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator.Pack;

public class DoudouDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(DoudouModelProvider::new);
		pack.addProvider(DoudouRecipeProvider::new);
	}
}
