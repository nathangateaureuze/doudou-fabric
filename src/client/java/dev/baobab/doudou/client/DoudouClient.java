package dev.baobab.doudou.client;

import dev.baobab.doudou.Entities.DoudouBlockEntities;
import dev.baobab.doudou.client.Entities.Bed.BedBlockEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;

public class DoudouClient implements ClientModInitializer {

	public static final String MOD_ID = "doudou";
	
    public static final DoudouModConfig CONFIG = DoudouModConfig.load();

	@Override
	public void onInitializeClient() {
		BlockEntityRenderers.register(DoudouBlockEntities.BED_BLOCK_ENTITY, BedBlockEntityRenderer::new);
	}
}