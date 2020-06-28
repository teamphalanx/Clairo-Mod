package com.minecraft2;

import com.minecraft2.Init.ItemInit;
import com.minecraft2.Init.SoundInit;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.PotionItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtils;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class Clairo extends AnimalEntity implements INamedContainerProvider {

    private LazyOptional<IItemHandler> handler = LazyOptional.of(this::createHandler);


    public Clairo(EntityType<? extends AnimalEntity> p_i48568_1_, World p_i48568_2_) {

        super(p_i48568_1_, p_i48568_2_);
    }

    @Nullable
    @Override
    public AgeableEntity createChild(AgeableEntity ageableEntity) {
        return null;
    }


    @Override
    public boolean processInteract(PlayerEntity p_184645_1_, Hand p_184645_2_) {

        if(!world.isRemote)
        {
            if(this instanceof INamedContainerProvider)
            {
                NetworkHooks.openGui((ServerPlayerEntity) p_184645_1_, (INamedContainerProvider) this, packetBuffer -> packetBuffer.writeVarInt(this.getEntityId()) );
            }
            else {
                throw new IllegalStateException("Our named container provider is missing!");
            }

            return true;
        }



        return super.processInteract(p_184645_1_, p_184645_2_);
    }



    @Override
    public void onStruckByLightning(LightningBoltEntity p_70077_1_) {
        this.setGlowing(true);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(5, new LookAtWithoutMovingGoal(this, PlayerEntity.class, 0.4f, 6.0f ));
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 0.4));
        this.goalSelector.addGoal(2, new OpenDoorGoal(this, true));
        this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 6.0f));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomWalkingGoal(this, 1.0));

        super.registerGoals();
    }

    @Override
    protected void updateAITasks() {
        super.updateAITasks();
    }

    public Entity entitygetter()
    {
        return this;
    }

    @Override
    public void livingTick() {
        if(this.world.isRemote)
        {

        }
        super.livingTick();
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2);
        this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(20.0);
        this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(10.0);
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {




        return new ClairoContainer(i, playerEntity.world, playerInventory, playerEntity, this);
    }

    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent(getType().getRegistryName().getPath());
    }


    private IItemHandler createHandler() {

        return new ItemStackHandler(9)
        {
            @Override
            protected void onContentsChanged(int slot) {
                handler.ifPresent(h ->
                {
                    if(slot == 2)
                    {
                        minecraft2mod.logger.info("SLOT 2 CHANGED");
                        if(h.getStackInSlot(2).getItem() == Items.AIR)
                        {
                            h.extractItem(0, 1, false);
                            h.extractItem(1, 1, false);
                        }
                    }
                    if((h.getStackInSlot(0).getItem() == ItemInit.bag.getItem() && h.getStackInSlot(1).getItem() == Items.GLASS_BOTTLE && h.getStackInSlot(2).getItem() == Items.AIR) || h.getStackInSlot(1).getItem() == ItemInit.bag.getItem() && h.getStackInSlot(0).getItem() == Items.GLASS_BOTTLE && h.getStackInSlot(2).getItem() == Items.AIR )
                    {
                        h.insertItem(2, PotionUtils.addPotionToItemStack(new ItemStack(new IItemProvider() {
                            @Override
                            public Item asItem() {
                                return Items.POTION;
                            }
                        }), minecraft2mod.RegistryEvents.immunity_potion), false);
                    }


                });
            }


        };

    }
    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return this.handler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void read(CompoundNBT tag) {
        CompoundNBT invTag = tag.getCompound("inv");
        handler.ifPresent(h -> ((INBTSerializable<CompoundNBT>)h).deserializeNBT(invTag));
        super.read(tag);
    }

    @Override
    public void writeAdditional(CompoundNBT tag) {
        handler.ifPresent(h -> {
            CompoundNBT compound = ((INBTSerializable<CompoundNBT>)h).serializeNBT();
            tag.put("inv", compound);
        });
        super.writeAdditional(tag);
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundInit.AMBIENT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
        return SoundInit.HURT.get();
    }
}
