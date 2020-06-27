package com.minecraft2.Init;

import com.minecraft2.Clairo;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import com.minecraft2.minecraft2mod;

public class ModEntityTypes {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.ENTITIES, minecraft2mod.MOD_ID);


    public static final RegistryObject<EntityType<Clairo>> clairo_entity = ENTITY_TYPES.register("clairo_entity", () -> EntityType.Builder.<Clairo>create(Clairo::new, EntityClassification.CREATURE)
            .size(0.6f, 1.8f)
            .build(new ResourceLocation(minecraft2mod.MOD_ID, "clairo_entity").toString()));
}
