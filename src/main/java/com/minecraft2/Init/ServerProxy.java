package com.minecraft2.Init;

import com.minecraft2.ModBlocks;
import com.minecraft2.client.gui.ClairoScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class ServerProxy implements IProxy {

    @Override
    public World getClientWorld()
    {
        throw new IllegalStateException("Only on client.");
    }

    @Override
    public PlayerEntity getClientPlayer()
    {
        throw new IllegalStateException("Only on client.");
    }

    @Override
    public void init()
    {
        //throw new IllegalStateException("only on client.");
    }

}
