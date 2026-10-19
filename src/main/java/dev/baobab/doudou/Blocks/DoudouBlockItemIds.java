package dev.baobab.doudou.Blocks;

import dev.baobab.doudou.Doudou;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.Identifier;

public class DoudouBlockItemIds {

    public static final BlockItemId BEE_DOUDOU = create("bee_doudou");
    public static final BlockItemId CHICKEN_DOUDOU = create("chicken_doudou");
    public static final BlockItemId BAT_DOUDOU = create("bat_doudou");
    public static final BlockItemId DOLPHIN_DOUDOU = create("dolphin_doudou");
    public static final BlockItemId RABBIT_DOUDOU = create("rabbit_doudou");

    private static BlockItemId create(String name)
    {
        Identifier id = Doudou.id(name);
        return BlockItemId.create(id, id);
    }
}
