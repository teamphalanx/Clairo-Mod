package com.minecraft2;

import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

public class ModBlocks {

    @ObjectHolder("minecraft2:furnaceblock")
    public static furnaceblock furnaceblock;

    @ObjectHolder("minecraft2:furnaceblock")
    public static TileEntityType<furnaceBlockTile> FURNACEBLOCK_TILE;

    @ObjectHolder("minecraft2:furnaceblock")
    public static ContainerType<furnaceBlockContainer> FURNACEBLOCK_CONTAINER;

    @ObjectHolder("minecraft2:clairo_entity")
    public static ContainerType<ClairoContainer> CLAIRO_CONTAINER;

}
