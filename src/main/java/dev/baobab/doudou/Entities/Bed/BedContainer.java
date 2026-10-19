package dev.baobab.doudou.Entities.Bed;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.ticks.ContainerSingleItem;

public interface BedContainer extends ContainerSingleItem.BlockContainerSingleItem{


    @Override 
    default boolean isEmpty() {
        return getTheItem().isEmpty();
    }

    @Override
    default void setChanged() {
    }

    @Override
    default boolean stillValid(Player player) {
        return true;
    }

    @Override
    default boolean canTakeItem(Container into, int slot, ItemStack itemStack) {
        return false;
    }

    @Override
    default boolean canPlaceItem(int slot, ItemStack itemStack) {
        return false;
    }
}
