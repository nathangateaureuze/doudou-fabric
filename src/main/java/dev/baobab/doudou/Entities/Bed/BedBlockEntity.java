package dev.baobab.doudou.Entities.Bed;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StrawBedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import dev.baobab.doudou.Doudou;
import dev.baobab.doudou.Blocks.Doudou.DoudouBlock;
import dev.baobab.doudou.Entities.DoudouBlockEntities;
import dev.baobab.doudou.Events.WakeUpAllPlayersCallback;

public class BedBlockEntity extends BlockEntity implements BedContainer {
    
    private final NonNullList<ItemStack> DoudouItem = NonNullList.withSize(1, ItemStack.EMPTY);
    private BlockState doudou;
    private Player sleeper;
    private BedType bedType = BedType.BED;
    
    private void refreshModel()
    {
        Block block = Block.byItem(getTheItem().getItem());
        this.doudou = block.defaultBlockState();
        
        if (this.getBlockState().getBlock() instanceof StrawBedBlock) {
            this.bedType = BedType.STRAW_BED;
        }
    }

    public BedType getBedType()
    {
        return this.bedType;
    }

    public BlockState getDoudou()
    {
        return this.doudou;
    }

    public BedBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(DoudouBlockEntities.BED_BLOCK_ENTITY, worldPosition, blockState);
        WakeUpAllPlayersCallback.EVENT.register((serverLevel) -> {onWakeUpAllPlayers(serverLevel);});
    }

    private  void applyDoudouFunction(Player player)
    {
        if (doudou.getBlock() instanceof DoudouBlock doudou) {
            doudou.DoudouFunction(player,this);
        }
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        this.clearContent();
        super.loadAdditional(input);
        ContainerHelper.loadAllItems(input, this.DoudouItem);
        refreshModel();
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        ContainerHelper.saveAllItems(output, this.DoudouItem);
        super.saveAdditional(output);
    }

    @Override
    public BlockEntity getContainerBlockEntity() {
        return this;
    }

    @Override
    public ItemStack getTheItem() {
        return this.DoudouItem.get(0);
    }

    @Override
    public void clearContent() {
        this.DoudouItem.set(0, ItemStack.EMPTY);
        this.doudou = null;
        this.setChanged();
    }

    @Override
    public void setTheItem(ItemStack itemStack) {
        this.DoudouItem.set(0, itemStack.copyWithCount(1));
        refreshModel();
        this.setChanged();
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registryLookup) {
        return this.saveWithoutMetadata(registryLookup);
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void setChanged() {
        super.setChanged();
        if (level == null) {
            return ;
        }
        
        BlockState blockState = this.getBlockState();
        level.sendBlockUpdated(worldPosition, blockState, blockState, Block.UPDATE_ALL);
    }

    public Player getSleeper()
    {
        return this.sleeper;
    }

    public void setSleeper(Player sleeper)
    {
        this.sleeper = sleeper;
    }

    public void resetSleeper()
    {
        this.sleeper = null;
    }

    private void onWakeUpAllPlayers(ServerLevel serverLevel)
    {
        if (this.getSleeper() == null) 
        {
            return ;
        }
        this.applyDoudouFunction(this.getSleeper());
        this.resetSleeper();
    }
}
