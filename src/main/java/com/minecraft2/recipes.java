package com.minecraft2;

import com.minecraft2.Init.ItemInit;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraftforge.items.IItemHandler;

public class recipes {


    public static Item alloy (IItemHandler h)
    {
        Item right = h.getStackInSlot(1).getItem();
        Item left  = h.getStackInSlot(0).getItem();
        if((right == ItemInit.copper_ingot.asItem() && left == ItemInit.tin_ingot.asItem()) || (right == ItemInit.tin_ingot.asItem() && left == ItemInit.copper_ingot.asItem()) )
        {
            h.extractItem(1, 1, false);
            h.extractItem(0, 1, false);

            return ItemInit.bronze_ingot;
        }
        else if((right == ItemInit.copper_ingot.asItem() && left == ItemInit.zinc_ingot.asItem()) || (right == ItemInit.zinc_ingot.asItem() && left == ItemInit.copper_ingot.asItem()) )
        {
            h.extractItem(1, 1, false);
            h.extractItem(0, 1, false);

            return ItemInit.brass_ingot;
        }
        else if((right == ItemInit.example_item.asItem() && left == Items.GOLD_INGOT.asItem()) || (right == Items.GOLD_INGOT .asItem()&& left == ItemInit.example_item.asItem()) )
        {
            h.extractItem(1, 1, false);
            h.extractItem(0, 1, false);

            return ItemInit.electrum_ingot;
        }
        else if((right == Items.IRON_INGOT && left == Items.COAL) || (right == Items.COAL && left == Items.IRON_INGOT) )
        {
            h.extractItem(1, 1, false);
            h.extractItem(0, 1, false);

            return ItemInit.steel_ingot;
        }

        else
        {
            return Items.AIR;
        }

    }

    public static Item checkAlloy(IItemHandler h)
    {
        Item right = h.getStackInSlot(1).getItem();
        Item left  = h.getStackInSlot(0).getItem();
        if((right == ItemInit.copper_ingot.asItem() && left == ItemInit.tin_ingot.asItem()) || (right == ItemInit.tin_ingot.asItem() && left == ItemInit.copper_ingot.asItem()) )
        {
            return ItemInit.bronze_ingot;
        }
        else if((right == ItemInit.copper_ingot.asItem() && left == ItemInit.zinc_ingot.asItem()) || (right == ItemInit.zinc_ingot.asItem() && left == ItemInit.copper_ingot.asItem()) )
        {
            return ItemInit.brass_ingot;
        }
        else if((right == ItemInit.example_item.asItem() && left == Items.GOLD_INGOT) || (right == Items.GOLD_INGOT && left == ItemInit.example_item.asItem()) )
        {
            return ItemInit.electrum_ingot;
        }
        else if((right == Items.IRON_INGOT && left == Items.COAL) || (right == Items.COAL && left == Items.IRON_INGOT) )
        {
            return ItemInit.steel_ingot;
        }

        else
        {
            return Items.AIR;
        }
    }

}
