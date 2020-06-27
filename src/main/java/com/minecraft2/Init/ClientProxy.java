package com.minecraft2.Init;

import com.minecraft2.client.gui.ClairoScreen;
import com.minecraft2.ModBlocks;
import com.minecraft2.client.entity.render.ClairoEntityRender;
//import com.minecraft2.client.gui.ClairoScreen;
import com.minecraft2.client.gui.furnaceBlockScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy implements IProxy {

    @Override
    public World getClientWorld()
    {
        return Minecraft.getInstance().world;
    }

    @Override
    public PlayerEntity getClientPlayer()
    {
        return Minecraft.getInstance().player;
    }
    @Override
    public void init() {
        ScreenManager.registerFactory(ModBlocks.FURNACEBLOCK_CONTAINER, furnaceBlockScreen::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.clairo_entity.get(), ClairoEntityRender::new);
        ScreenManager.registerFactory(ModBlocks.CLAIRO_CONTAINER, ClairoScreen::new);
    }
}
