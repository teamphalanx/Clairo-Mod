package com.minecraft2.Init;

import com.minecraft2.minecraft2mod;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(minecraft2mod.MOD_ID)
@Mod.EventBusSubscriber(modid = minecraft2mod.MOD_ID, bus= Mod.EventBusSubscriber.Bus.MOD)
public class BlockInit {
    public static final Block example_block = null;
    public static final Block copper_block = null;
    public static final Block zinc_block = null;
    public static final Block salt_block = null;
    public static final Block tin_block = null;
    public static final Block titanium_block = null;

    @SubscribeEvent
    public static void registerBlocks(final RegistryEvent.Register<Block> event)
    {
        event.getRegistry().register(new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(4.0f,15.0f).sound(SoundType.STONE).harvestLevel(3)).setRegistryName("example_block"));
        event.getRegistry().register(new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(2.0f,15.0f).sound(SoundType.STONE).harvestLevel(1)).setRegistryName("copper_block"));
        event.getRegistry().register(new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(2.0f,15.0f).sound(SoundType.STONE).harvestLevel(1)).setRegistryName("zinc_block"));
        event.getRegistry().register(new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(2.0f,15.0f).sound(SoundType.STONE).harvestLevel(1)).setRegistryName("tin_block"));
        event.getRegistry().register(new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(1.0f,15.0f).sound(SoundType.STONE).harvestLevel(1)).setRegistryName("salt_block"));
        event.getRegistry().register(new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(8.0f,15.0f).sound(SoundType.STONE).harvestLevel(4)).setRegistryName("titanium_block"));
    }
    @SubscribeEvent
    public static void registerBlockItems(final RegistryEvent.Register<Item> event)
    {
        event.getRegistry().register(new BlockItem(example_block, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName("example_block"));
        event.getRegistry().register(new BlockItem(copper_block, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName("copper_block"));
        event.getRegistry().register(new BlockItem(zinc_block, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName("zinc_block"));
        event.getRegistry().register(new BlockItem(salt_block, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName("salt_block"));
        event.getRegistry().register(new BlockItem(tin_block, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName("tin_block"));
        event.getRegistry().register(new BlockItem(titanium_block, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName("titanium_block"));
    }
}
