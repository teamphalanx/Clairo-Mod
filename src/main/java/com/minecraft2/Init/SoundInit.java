package com.minecraft2.Init;

import com.minecraft2.minecraft2mod;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SoundInit {
    public static final DeferredRegister<SoundEvent> SOUNDS = new DeferredRegister<>(ForgeRegistries.SOUND_EVENTS, minecraft2mod.MOD_ID);

    public static final RegistryObject<SoundEvent> AMBIENT = SOUNDS.register("entity.clairo_entity.ambient", () -> new SoundEvent(new ResourceLocation(minecraft2mod.MOD_ID,"entity.clairo_entity.ambient" )));
    public static final RegistryObject<SoundEvent> HURT = SOUNDS.register("entity.clairo_entity.hurt", () -> new SoundEvent(new ResourceLocation(minecraft2mod.MOD_ID,"entity.clairo_entity.hurt" )));
}
