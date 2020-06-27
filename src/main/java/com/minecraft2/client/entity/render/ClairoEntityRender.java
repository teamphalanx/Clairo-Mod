package com.minecraft2.client.entity.render;

import com.minecraft2.Clairo;
import com.minecraft2.client.entity.model.ClairoEntityModel;
import com.minecraft2.minecraft2mod;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class ClairoEntityRender extends MobRenderer<Clairo, ClairoEntityModel<Clairo>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(minecraft2mod.MOD_ID, "textures/entity/clairo_entity.png");

    public ClairoEntityRender(EntityRendererManager rendererManagerIn) {
        super(rendererManagerIn, new ClairoEntityModel<Clairo>(), 1.0f);
    }



    @Override
    public ResourceLocation getEntityTexture(Clairo clairo) {
        return TEXTURE;
    }
}
