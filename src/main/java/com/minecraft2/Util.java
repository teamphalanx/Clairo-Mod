package com.minecraft2;

import net.minecraft.block.Block;
import net.minecraftforge.common.ToolType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ItemTier;

import java.lang.reflect.Field;

public class Util {

    public static Field harvestLevel;
    public static Field harvestTool;
    public static Field toolHarvestLevel;

    static {
        try {
            harvestLevel = Block.class.getDeclaredField("harvestLevel");
            harvestLevel.setAccessible(true);

            harvestTool = Block.class.getDeclaredField("harvestTool");
            harvestTool.setAccessible(true);

            toolHarvestLevel = ItemTier.class.getDeclaredField("harvestLevel");
            toolHarvestLevel.setAccessible(true);



        }catch (Exception e){
            //throw new RuntimeException("oh no",e);
        }
    }

    public static void setHarvestLevel(Block b,int level){
        try {

            harvestLevel.setInt(b,level);
        } catch (Exception e){
            //throw new RuntimeException("oh no",e);
        }
    }

    public static void setHarvestTool(Block b, ToolType tool){
        try {
            harvestTool.set(b,tool);
        } catch (Exception e){
            //throw new RuntimeException("oh no",e);
        }
    }

    public static void setToolHarvestLevel(ItemTier tier, int level)
    {
        try
        {
            toolHarvestLevel.set(tier, level);
        }
        catch (Exception  e)
        {
            //throw new RuntimeException("oh no",e);
        }
    }
}
