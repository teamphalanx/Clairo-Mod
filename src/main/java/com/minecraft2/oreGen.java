package com.minecraft2;

import com.minecraft2.Init.BlockInit;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;


public class oreGen {

    public static void generateOre()
    {

        for(Biome biome : ForgeRegistries.BIOMES)
        {
            ConfiguredPlacement customConfig_silver = Placement.COUNT_RANGE.configure(new CountRangeConfig(4, 5, 5, 40));
            ConfiguredPlacement customConfig_copper = Placement.COUNT_RANGE.configure(new CountRangeConfig(12, 5, 5, 65));
            ConfiguredPlacement customConfig_zinc = Placement.COUNT_RANGE.configure(new CountRangeConfig(8, 5, 5, 50));
            ConfiguredPlacement customConfig_salt = Placement.COUNT_RANGE.configure(new CountRangeConfig(20, 15, 15, 70));
            ConfiguredPlacement customConfig_tin = Placement.COUNT_RANGE.configure(new CountRangeConfig(8, 5, 5, 50));
            ConfiguredPlacement customConfig_titanium = Placement.COUNT_RANGE.configure(new CountRangeConfig(1, 1, 1, 10));

            biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                    Feature.ORE.withConfiguration(new OreFeatureConfig(
                            OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.silver_block.getDefaultState(), 9)).withPlacement(customConfig_silver));
            if(biome == Biomes.DESERT)
            {
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                        Feature.ORE.withConfiguration(new OreFeatureConfig(
                                OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.copper_block.getDefaultState(), 6)).withPlacement(customConfig_copper));
            }
            biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                    Feature.ORE.withConfiguration(new OreFeatureConfig(
                            OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.zinc_block.getDefaultState(), 5)).withPlacement(customConfig_zinc));
            biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                    Feature.ORE.withConfiguration(new OreFeatureConfig(
                            OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.salt_block.getDefaultState(), 10)).withPlacement(customConfig_salt));
            biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                    Feature.ORE.withConfiguration(new OreFeatureConfig(
                            OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.tin_block.getDefaultState(), 5)).withPlacement(customConfig_tin));
            biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                    Feature.ORE.withConfiguration(new OreFeatureConfig(
                            OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.titanium_block.getDefaultState(), 12)).withPlacement(customConfig_titanium));
        }
    }
}
