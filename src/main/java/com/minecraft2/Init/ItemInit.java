package com.minecraft2.Init;


import com.minecraft2.minecraft2mod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = minecraft2mod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(minecraft2mod.MOD_ID)
public class ItemInit {

    public static final Item example_item = null;
    public static final Item copper_ingot = null;
    public static final Item tin_ingot = null;
    public static final Item zinc_ingot = null;
    public static final Item titanium_ingot = null;
    public static final Item silver_nugget = null;
    public static final Item copper_nugget = null;
    public static final Item tin_nugget = null;
    public static final Item zinc_nugget = null;
    public static final Item titanium_nugget = null;
    public static final Item bronze_ingot = null;
    public static final Item brass_ingot = null;
    public static final Item electrum_ingot = null;
    public static final Item steel_ingot = null;
    public static final Item bronze_nugget = null;
    public static final Item brass_nugget = null;
    public static final Item electrum_nugget = null;
    public static final Item steel_nugget = null;
    public static final Item bag = null;


    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event)
    {
        event.getRegistry().register(new Item(new Item.Properties().group(ItemGroup.MATERIALS)).setRegistryName("example_item"));
        event.getRegistry().register(new Item(new Item.Properties().group(ItemGroup.MATERIALS)).setRegistryName("copper_ingot"));
        event.getRegistry().register(new Item(new Item.Properties().group(ItemGroup.MATERIALS)).setRegistryName("tin_ingot"));
        event.getRegistry().register(new Item(new Item.Properties().group(ItemGroup.MATERIALS)).setRegistryName("zinc_ingot"));
        event.getRegistry().register(new Item(new Item.Properties().group(ItemGroup.MATERIALS)).setRegistryName("titanium_ingot"));
        event.getRegistry().register(new Item(new Item.Properties().group(ItemGroup.MATERIALS)).setRegistryName("silver_nugget"));
        event.getRegistry().register(new Item(new Item.Properties().group(ItemGroup.MATERIALS)).setRegistryName("copper_nugget"));
        event.getRegistry().register(new Item(new Item.Properties().group(ItemGroup.MATERIALS)).setRegistryName("tin_nugget"));
        event.getRegistry().register(new Item(new Item.Properties().group(ItemGroup.MATERIALS)).setRegistryName("zinc_nugget"));
        event.getRegistry().register(new Item(new Item.Properties().group(ItemGroup.MATERIALS)).setRegistryName("titanium_nugget"));
        event.getRegistry().register(new Item(new Item.Properties().group(ItemGroup.MATERIALS)).setRegistryName("bronze_ingot"));
        event.getRegistry().register(new Item(new Item.Properties().group(ItemGroup.MATERIALS)).setRegistryName("brass_ingot"));
        event.getRegistry().register(new Item(new Item.Properties().group(ItemGroup.MATERIALS)).setRegistryName("electrum_ingot"));
        event.getRegistry().register(new Item(new Item.Properties().group(ItemGroup.MATERIALS)).setRegistryName("steel_ingot"));
        event.getRegistry().register(new Item(new Item.Properties().group(ItemGroup.MATERIALS)).setRegistryName("bronze_nugget"));
        event.getRegistry().register(new Item(new Item.Properties().group(ItemGroup.MATERIALS)).setRegistryName("brass_nugget"));
        event.getRegistry().register(new Item(new Item.Properties().group(ItemGroup.MATERIALS)).setRegistryName("electrum_nugget"));
        event.getRegistry().register(new Item(new Item.Properties().group(ItemGroup.MATERIALS)).setRegistryName("steel_nugget"));
        event.getRegistry().register(new Item(new Item.Properties().group(ItemGroup.MATERIALS)).setRegistryName("bag"));

    }



}