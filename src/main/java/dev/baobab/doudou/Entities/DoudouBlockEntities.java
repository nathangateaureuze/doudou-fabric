package dev.baobab.doudou.Entities;

import dev.baobab.doudou.Doudou;
import dev.baobab.doudou.Entities.Bed.BedBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class DoudouBlockEntities {
    
    public static final BlockEntityType<BedBlockEntity> BED_BLOCK_ENTITY = register("bed", BedBlockEntity::new, 
																					Blocks.BED.black(),
																					Blocks.BED.blue(),
																					Blocks.BED.brown(),
																					Blocks.BED.cyan(),
																					Blocks.BED.gray(),
																					Blocks.BED.green(),
																					Blocks.BED.lightBlue(),
																					Blocks.BED.lightGray(),
																					Blocks.BED.lime(),
																					Blocks.BED.magenta(),
																					Blocks.BED.orange(),
																					Blocks.BED.pink(),
																					Blocks.BED.purple(),
																					Blocks.BED.red(),
																					Blocks.BED.white(),
																					Blocks.BED.yellow(),
																					Blocks.STRAW_BED);

	private static <T extends BlockEntity> BlockEntityType<T> register(
			String name,
			FabricBlockEntityTypeBuilder.Factory<? extends T> entityFactory,
			Block... blocks
	) {
		Identifier id = Doudou.id(name);
		return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, id, FabricBlockEntityTypeBuilder.<T>create(entityFactory, blocks).build());
	}

    public static void initialize()
    {}
}
