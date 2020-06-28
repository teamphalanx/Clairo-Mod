package com.minecraft2.Init;

import com.minecraft2.ClairoSpawnItem;
import com.minecraft2.minecraft2mod;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EventHandler {
    @SubscribeEvent
    public void interact(PlayerInteractEvent event) {
        if (!event.getWorld().isRemote) {
            if (event.getHand() == Hand.MAIN_HAND) {
                minecraft2mod.logger.info("INTERACTED");
                //minecraft2mod.logger.info(event.getSide());
                if (event.getWorld().getBlockState(event.getPos()).getBlock() == Blocks.OAK_DOOR) {
                    ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
                    int index = 0;
                    boolean removed = false;

                    if (player.inventory.hasItemStack(new ItemStack(new IItemProvider() {
                        @Override
                        public Item asItem() {
                            return ItemInit.bag;
                        }
                    })) && !player.inventory.hasItemStack(new ItemStack(new IItemProvider() {
                        @Override
                        public Item asItem() {
                            return minecraft2mod.RegistryEvents.clairo_egg;
                        }
                    }))) {
                        player.inventory.addItemStackToInventory(new ItemStack(new IItemProvider() {
                            @Override
                            public Item asItem() {
                                return minecraft2mod.RegistryEvents.clairo_egg;
                            }
                        }));
                        for (ItemStack stack : player.inventory.mainInventory)
                        {
                            index ++;
                            if (stack.getItem() == ItemInit.bag.getItem() && !removed)
                            {
                                player.inventory.removeStackFromSlot(index-1);
                                removed = true;
                            }
                        }

                    }
                }
            }
        }
    }

}
