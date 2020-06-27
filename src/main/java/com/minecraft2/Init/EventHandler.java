package com.minecraft2.Init;

import com.minecraft2.ClairoSpawnItem;
import com.minecraft2.minecraft2mod;
import net.minecraft.block.Blocks;
import net.minecraft.util.Hand;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EventHandler {
    @SubscribeEvent
    public void interact(PlayerInteractEvent event) {
        if (event.getWorld().isRemote) {
            if (event.getHand() == Hand.MAIN_HAND) {
                minecraft2mod.logger.info("INTERACTED");
                if (event.getWorld().getBlockState(event.getPos()).getBlock() == Blocks.OAK_DOOR) {
                    if (event.getPlayer().inventory.hasItemStack(ItemInit.bag.getDefaultInstance()) && !event.getPlayer().inventory.hasItemStack(minecraft2mod.RegistryEvents.clairo_egg.getDefaultInstance())) {
                        event.getPlayer().inventory.addItemStackToInventory(minecraft2mod.RegistryEvents.clairo_egg.getDefaultInstance());
                        event.getPlayer().inventory.removeStackFromSlot(event.getPlayer().inventory.getSlotFor(ItemInit.bag.getDefaultInstance()));
                    }
                }
            }
        }
    }

}
