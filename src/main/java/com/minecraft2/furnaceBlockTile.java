package com.minecraft2;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.minecraft2.ModBlocks.FURNACEBLOCK_TILE;

public class furnaceBlockTile extends TileEntity implements ITickableTileEntity, INamedContainerProvider {

    private LazyOptional<IItemHandler> handler = LazyOptional.of(this::createHandler);

    public furnaceBlockTile()
    {
        super(FURNACEBLOCK_TILE);
    }

    private int count;
    private int energy;
    public boolean cooking;



    @Override

    public void tick() {

        if (!world.isRemote) {


            this.world.notifyBlockUpdate(this.pos, this.world.getBlockState(this.pos), this.world.getBlockState(this.pos), 2);

            this.markDirty();

            handler.ifPresent(h -> {

                if (h.getStackInSlot(3).getItem() == Items.AIR || h.getStackInSlot(3).getItem() == recipes.checkAlloy(h)) {
                    if (!h.getStackInSlot(1).isEmpty() && !h.getStackInSlot(0).isEmpty() && (h.getStackInSlot(2).getItem() == Items.COAL || count > 0)) {
                        cooking = true;
                        if (count > 0) {
                            count--;
                            energy++;


                            if (energy == 160) {

                                h.insertItem(3, new ItemStack(new IItemProvider() {
                                    @Override
                                    public Item asItem() {
                                        return recipes.alloy(h);
                                    }
                                }), false);
                                energy = 0;


                            }
                            this.markDirty();
                        }
                        if (count == 0) {
                            //energy = 0;
                            if (h.getStackInSlot(2).getItem() == Items.COAL && recipes.checkAlloy(h) != Items.AIR) {
                                h.extractItem(2, 1, false);

                                count += 1280;
                            }

                        }
                    } else {

                        cooking = false;
                    }
                } else {
                    energy = 0;
                }

            });


            this.markDirty();

        }
        //minecraft2mod.logger.info("ENERGY IS: " + getEnergy());

    }

    public IItemHandler getInventory(@Nullable IItemHandler obj)
    {
        return handler.orElse(obj);
    }

    @Override
    public void read(CompoundNBT tag) {
        CompoundNBT invTag = tag.getCompound("inv");
        handler.ifPresent(h -> ((INBTSerializable<CompoundNBT>)h).deserializeNBT(invTag));
        this.energy = tag.getInt("energy");
        this.count = tag.getInt("count");
        super.read(tag);
    }

    @Override
    public CompoundNBT write(CompoundNBT tag) {
        tag.putInt("energy", this.energy);
        tag.putInt("count", this.count);
        handler.ifPresent(h -> {
            CompoundNBT compound = ((INBTSerializable<CompoundNBT>)h).serializeNBT();
            tag.put("inv", compound);
        });
        return super.write(tag);
    }

    private IItemHandler createHandler() {

        return new ItemStackHandler(9)
        {
            @Override
            protected void onContentsChanged(int slot) {
                markDirty();
            }

        };

    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent(getType().getRegistryName().getPath());
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new furnaceBlockContainer(i, world, pos, playerInventory, playerEntity);
    }

    public int getCount() {
        return this.count;
    }

    public int getEnergy() {
        return (int)(((28.0/160.0)*(double)this.energy));
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.getPos(), -1, this.serializeNBT());
    }
    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        this.deserializeNBT(pkt.getNbtCompound());
    }

    @Override
    public void handleUpdateTag(CompoundNBT tag) {
        super.handleUpdateTag(tag);
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return this.write(new CompoundNBT());
    }

}
