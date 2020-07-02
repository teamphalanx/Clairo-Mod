package com.minecraft2;

import com.minecraft2.Init.ItemInit;
import net.minecraft.block.Blocks;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.LazyValue;

import java.util.function.Supplier;

public enum     toolmateriallist implements IItemTier {

    WOOD(0, 59, 0.0F, 0.0F, 15, () -> {
        return Ingredient.fromTag(ItemTags.PLANKS);
    }),
    FLINT(1, 120, 4.0f, 1.0f, 5, () ->{
        return Ingredient.fromItems(new IItemProvider[]{Items.FLINT});
    }),
    STONE(1, 131, 4.0F, 1.0F, 5, () -> {
        return Ingredient.fromItems(new IItemProvider[]{Blocks.COBBLESTONE});
    }),
    IRON(3, 280, 6.0F, 2.0F, 14, () -> {
        return Ingredient.fromItems(new IItemProvider[]{Items.IRON_INGOT});
    }),
    DIAMOND(5, 1600, 8.0F, 3.0F, 10, () -> {
        return Ingredient.fromItems(new IItemProvider[]{Items.DIAMOND});
    }),
    GOLD(0, 32, 12.0F, 0.0F, 22, () -> {
        return Ingredient.fromItems(new IItemProvider[]{Items.GOLD_INGOT});
    }),
    COPPER(1, 180, 4.5F, 1.0F, 7, () -> {
        return Ingredient.fromItems(new IItemProvider[]{ItemInit.copper_ingot});
    }),
    TIN(1, 180, 4.5F, 1.0F, 7, () -> {
        return Ingredient.fromItems(new IItemProvider[]{ItemInit.tin_ingot});
    }),
    BRONZE(2, 200, 5.0F, 1.5F, 8, () -> {
        return Ingredient.fromItems(new IItemProvider[]{ItemInit.bronze_ingot});
    }),
    ZINC(1, 160, 4.0F, 1.0F, 7, () -> {
        return Ingredient.fromItems(new IItemProvider[]{ItemInit.zinc_ingot});
    }),
    BRASS(2, 190, 5.0F, 1.5F, 9, () -> {
        return Ingredient.fromItems(new IItemProvider[]{ItemInit.brass_ingot});
    }),
    ELECTRUM(3, 280, 6.0F, 1.0F, 15, () -> {
        return Ingredient.fromItems(new IItemProvider[]{ItemInit.electrum_ingot});
    }),
    SILVER(3, 230, 5.5F, 1.0F, 10, () -> {
        return Ingredient.fromItems(new IItemProvider[]{ItemInit.steel_ingot});
    }),
    STEEL(4, 560, 6.5F, 2.0F, 10, () -> {
        return Ingredient.fromItems(new IItemProvider[]{ItemInit.steel_ingot});
    }),
    TITANIUM(5, 2000, 8.5F, 4.0F, 18, () -> {
        return Ingredient.fromItems(new IItemProvider[]{ItemInit.titanium_ingot});
    });

    private final int harvestLevel;
    private final int maxUses;
    private final float efficiency;
    private final float attackDamage;
    private final int enchantability;
    private final LazyValue<Ingredient> repairMaterial;

    private toolmateriallist(int p_i48458_3_, int p_i48458_4_, float p_i48458_5_, float p_i48458_6_, int p_i48458_7_, Supplier<Ingredient> p_i48458_8_) {
        this.harvestLevel = p_i48458_3_;
        this.maxUses = p_i48458_4_;
        this.efficiency = p_i48458_5_;
        this.attackDamage = p_i48458_6_;
        this.enchantability = p_i48458_7_;
        this.repairMaterial = new LazyValue(p_i48458_8_);
    }

    public int getMaxUses() {
        return this.maxUses;
    }

    public float getEfficiency() {
        return this.efficiency;
    }

    public float getAttackDamage() {
        return this.attackDamage;
    }

    public int getHarvestLevel() {
        return this.harvestLevel;
    }

    public int getEnchantability() {
        return this.enchantability;
    }

    public Ingredient getRepairMaterial() {
        return (Ingredient)this.repairMaterial.getValue();
    }
}
