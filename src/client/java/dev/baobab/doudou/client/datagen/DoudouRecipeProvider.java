package dev.baobab.doudou.client.datagen;

import java.util.concurrent.CompletableFuture;

import dev.baobab.doudou.Blocks.DoudouBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;

public class DoudouRecipeProvider extends FabricRecipeProvider{

    public DoudouRecipeProvider(FabricPackOutput output, CompletableFuture<Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(Provider registries, BootstrapContext<Recipe<?>> recipes, BootstrapContext<Advancement> advancements) 
    {
        return new RecipeProvider(recipes, advancements) 
            {
                @Override
                public void buildRecipes() 
                {
                    HolderLookup.RegistryLookup<Item> itemLookup = registries.lookupOrThrow(Registries.ITEM);

                    shapeless(RecipeCategory.DECORATIONS, DoudouBlocks.BEE_DOUDOU)
                        .requires(Items.HONEYCOMB)
                        .requires(ItemTags.WOOL)
                        .requires(ItemTags.WOOL)
                        .requires(Items.STRING, 2)
                        .unlockedBy(getHasName(Items.HONEYCOMB), has(Items.HONEYCOMB))
                        .save(output);

                    shapeless(RecipeCategory.DECORATIONS, DoudouBlocks.CHICKEN_DOUDOU)
                        .requires(Items.FEATHER)
                        .requires(ItemTags.WOOL)
                        .requires(ItemTags.WOOL)
                        .requires(Items.STRING, 2)
                        .unlockedBy(getHasName(Items.FEATHER), has(Items.FEATHER))
                        .save(output);

                    shapeless(RecipeCategory.DECORATIONS, DoudouBlocks.BAT_DOUDOU)
                        .requires(Items.GLOW_BERRIES)
                        .requires(ItemTags.WOOL)
                        .requires(ItemTags.WOOL)
                        .requires(Items.STRING, 2)
                        .unlockedBy(getHasName(Items.GLOW_BERRIES), has(Items.GLOW_BERRIES))
                        .save(output);

                    shapeless(RecipeCategory.DECORATIONS, DoudouBlocks.DOLPHIN_DOUDOU)
                        .requires(Items.SEAGRASS)
                        .requires(ItemTags.WOOL)
                        .requires(ItemTags.WOOL)
                        .requires(Items.STRING, 2)
                        .unlockedBy(getHasName(Items.SEAGRASS), has(Items.SEAGRASS))
                        .save(output);

                    shapeless(RecipeCategory.DECORATIONS, DoudouBlocks.RABBIT_DOUDOU)
                        .requires(Items.RABBIT_FOOT)
                        .requires(ItemTags.WOOL)
                        .requires(ItemTags.WOOL)
                        .requires(Items.STRING, 2)
                        .unlockedBy(getHasName(Items.RABBIT_FOOT), has(Items.RABBIT_FOOT))
                        .save(output);
                }
            };
    }

	@Override
	public String getName() {
		return "TemplateModRecipeProvider";
	}
}
