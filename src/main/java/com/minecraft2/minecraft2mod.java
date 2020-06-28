package com.minecraft2;


import com.google.common.collect.Lists;

import com.minecraft2.Init.*;
import io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.*;
import net.minecraft.potion.*;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.thread.SidedThreadGroup;
import net.minecraftforge.fml.common.thread.SidedThreadGroups;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.commons.lang3.tuple.Pair;
import net.minecraftforge.fml.config.ModConfig.Reloading;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import net.minecraft.item.AxeItem;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;


import static net.minecraftforge.common.MinecraftForge.EVENT_BUS;



@Mod(minecraft2mod.MOD_ID)
@Mod.EventBusSubscriber(modid = minecraft2mod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)

public class minecraft2mod
{
    public static final String MOD_ID = "minecraft2";
    private static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, "minecraft");



    public static IProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());

    public static Logger logger = LogManager.getLogger(MOD_ID);
    private static final String PROTOCOL_VERSION = "1";


    public minecraft2mod()
    {
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, SERVER_SPEC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onConfigurationChangedEvent);
        SoundInit.SOUNDS.register(FMLJavaModLoadingContext.get().getModEventBus());

        EVENT_BUS.addListener(this::serverstart);
        EVENT_BUS.register(new EventHandler());


        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        //ModContainerTypes.CONTAINER_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModEntityTypes.ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
        int id = 0;




    }

    private void setup(final FMLCommonSetupEvent event) {
        //setup.init();
        proxy.init();
    }


    @SubscribeEvent
    public static void loadCompleteEvent(FMLLoadCompleteEvent event)
    {
        oreGen.generateOre();
    }


    public void serverstart(FMLServerStartedEvent e) {
        if(e.getServer().getExecutionThread().getThreadGroup() == SidedThreadGroups.SERVER) {

            Util.setHarvestTool(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("minecraft:stone")), ToolType.get("pickaxe"));
            Util.setHarvestLevel(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("minecraft:stone")), Integer.parseInt("1"));

            Util.setHarvestTool(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("minecraft:iron_ore")), ToolType.get("pickaxe"));
            Util.setHarvestLevel(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("minecraft:iron_ore")), Integer.parseInt("2"));

            Util.setHarvestTool(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("minecraft:coal_ore")), ToolType.get("pickaxe"));
            Util.setHarvestLevel(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("minecraft:coal_ore")), Integer.parseInt("1"));

            Util.setHarvestTool(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("minecraft:gold_ore")), ToolType.get("pickaxe"));
            Util.setHarvestLevel(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("minecraft:gold_ore")), Integer.parseInt("3"));

            Util.setHarvestTool(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("minecraft:redstone_ore")), ToolType.get("pickaxe"));
            Util.setHarvestLevel(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("minecraft:redstone_ore")), Integer.parseInt("3"));

            Util.setHarvestTool(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("minecraft:diamond_ore")), ToolType.get("pickaxe"));
            Util.setHarvestLevel(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("minecraft:diamond_ore")), Integer.parseInt("4"));

            Util.setHarvestTool(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("minecraft:emerald_ore")), ToolType.get("pickaxe"));
            Util.setHarvestLevel(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("minecraft:emerald_ore")), Integer.parseInt("3"));
            Util.setHarvestTool(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("minecraft:obsidian")), ToolType.get("pickaxe"));
            Util.setHarvestLevel(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("minecraft:obsidian")), Integer.parseInt("5"));
        }
    }

    public void onConfigurationChangedEvent(Reloading event)
    {
        if (MOD_ID.equals(event.getConfig().getModId())) {
            //handleConfig();
        }
    }
    public static final ConfigHandler SERVER;
    public static final ForgeConfigSpec SERVER_SPEC;

    static {
        final Pair<ConfigHandler, ForgeConfigSpec> specPair2 = new ForgeConfigSpec.Builder().configure(ConfigHandler::new);
        SERVER_SPEC = specPair2.getRight();
        SERVER = specPair2.getLeft();
    }
    public static class ConfigHandler {

        public static ForgeConfigSpec.ConfigValue<List<? extends String>> tweak_hardness;

        public static ForgeConfigSpec.ConfigValue<List<? extends String>> tweak_mining_level;

        public ConfigHandler(ForgeConfigSpec.Builder builder) {
            builder.push("general");
            tweak_hardness =
                    builder
                            .comment("The blocks whose hardness should be modified. This needs to be the registry name of the block followed by an @ followed by the new hardness, so for example: 'minecraft:stone@5'")
                            .defineList("tweak_hardness", Lists.newArrayList(), String.class::isInstance);

            tweak_mining_level =
                    builder
                            .comment("The blocks whose mining level should be modified. This needs to be the registry name of the block followed by an @ followed by the tool class ('pickaxe', 'axe' or 'shovel') followed by an @ followed by the harvest level (0 = wood, 1 = stone, 2 = iron, 3 = diamond, custom levels work), so for example: 'minecraft:stone@pickaxe@2'")
                            .defineList("tweak_mining_level", Lists.newArrayList(), String.class::isInstance);
            builder.pop();
        }
    }

    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents
    {
        static Item iron_pick;
        static Item stone_pick;
        static Item diamond_pick;
        static Item gold_pick;
        static Item wood_axe;
        static Item copper_pickaxe;

        static Item bronze_pickaxe;

        static Item brass_pickaxe;
        static Item silver_pickaxe;
        static Item electrum_pickaxe;
        static Item steel_pickaxe;
        static Item titanium_pickaxe, flint_pickaxe;
        static Item copper_axe, flint_axe,  bronze_axe,  silver_axe, brass_axe, electrum_axe, steel_axe, titanium_axe;
        static Item copper_sword, flint_sword,  bronze_sword,  silver_sword, brass_sword, electrum_sword, steel_sword, titanium_sword;
        static Item copper_shovel, flint_shovel,  bronze_shovel,  silver_shovel, brass_shovel, electrum_shovel, steel_shovel, titanium_shovel;
        static Item copper_hoe, flint_hoe,  bronze_hoe, silver_hoe, brass_hoe, electrum_hoe, steel_hoe, titanium_hoe;
        public static Item clairo_egg;
        static Potion immunity_potion;
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> event) {
            event.getRegistry().register(new furnaceblock());
        }
        @SubscribeEvent
        public static void registerItems(final RegistryEvent.Register<Item> event)
        {
            event.getRegistry().registerAll(
                    iron_pick = new PickaxeItem(toolmateriallist.IRON, 1, -2.8f, new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName(new ResourceLocation("minecraft", "iron_pickaxe")),
                    stone_pick = new PickaxeItem(toolmateriallist.STONE, 1, -2.4f, new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName(new ResourceLocation("minecraft", "stone_pickaxe")),
                    diamond_pick = new PickaxeItem(toolmateriallist.DIAMOND, 1, -2.8f, new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName(new ResourceLocation("minecraft", "diamond_pickaxe")),
                    wood_axe = new AxeItem(toolmateriallist.WOOD, 1, -3.2f, new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName(new ResourceLocation("minecraft", "wooden_axe")),
                    gold_pick = new PickaxeItem(toolmateriallist.GOLD, 1, -3.0f, new Item.Properties().group(ItemGroup.TOOLS) ).setRegistryName(new ResourceLocation("minecraft", "golden_pickaxe")),
                    copper_pickaxe = new PickaxeItem(toolmateriallist.COPPER, 1, -2.8f, new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName(new ResourceLocation("minecraft2", "copper_pickaxe")),
                    bronze_pickaxe = new PickaxeItem(toolmateriallist.BRONZE, 1, -2.4f, new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName(new ResourceLocation("minecraft2", "bronze_pickaxe")),
                    //brass_pickaxe = new PickaxeItem(toolmateriallist.BRASS, 1, -2.4f, new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName(new ResourceLocation("minecraft2", "brass_pickaxe")),
                    electrum_pickaxe = new PickaxeItem(toolmateriallist.ELECTRUM, 1, -2.4f, new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName(new ResourceLocation("minecraft2", "electrum_pickaxe")),
                    steel_pickaxe = new PickaxeItem(toolmateriallist.STEEL, 1, -2.4f, new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName(new ResourceLocation("minecraft2", "steel_pickaxe")),
                    titanium_pickaxe = new PickaxeItem(toolmateriallist.TITANIUM, 1, -2.8f, new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName(new ResourceLocation("minecraft2", "titanium_pickaxe")),
                    copper_axe = new AxeItem(toolmateriallist.COPPER, 2.0f, -3.2f, new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName(new ResourceLocation("minecraft2", "copper_axe")),
                    bronze_axe = new AxeItem(toolmateriallist.BRONZE, 2.5f, -3.2f, new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName(new ResourceLocation("minecraft2", "bronze_axe")),
                    silver_pickaxe = new AxeItem(toolmateriallist.SILVER, 1, -2.8f, new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName(new ResourceLocation("minecraft2", "silver_pickaxe")),
                    silver_axe = new AxeItem(toolmateriallist.SILVER, 2.0f, -3.2f, new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName(new ResourceLocation("minecraft2", "silver_axe")),
                    //brass_axe = new AxeItem(toolmateriallist.BRASS, 6.0f, -3.2f, new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName(new ResourceLocation("minecraft2", "brass_axe")),
                    electrum_axe = new AxeItem(toolmateriallist.ELECTRUM, 2.0f, -3.0f, new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName(new ResourceLocation("minecraft2", "electrum_axe")),
                    steel_axe = new AxeItem(toolmateriallist.STEEL, 3.0f, -3.0f, new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName(new ResourceLocation("minecraft2", "steel_axe")),
                    titanium_axe = new AxeItem(toolmateriallist.TITANIUM, 3.0f, -2.8f, new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName(new ResourceLocation("minecraft2", "titanium_axe")),
                    copper_sword = new SwordItem(toolmateriallist.COPPER, 3, -2.4f, new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName(new ResourceLocation("minecraft2", "copper_sword")),
                    bronze_sword = new SwordItem(toolmateriallist.BRONZE, 3, -2.4f, new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName(new ResourceLocation("minecraft2", "bronze_sword")),
                    silver_sword = new SwordItem(toolmateriallist.SILVER, 3, -2.4f, new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName(new ResourceLocation("minecraft2", "silver_sword")),
                    //brass_sword = new SwordItem(toolmateriallist.BRASS, 3, -2.4f, new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName(new ResourceLocation("minecraft2", "brass_sword")),
                    electrum_sword = new SwordItem(toolmateriallist.ELECTRUM, 3, -2.4f, new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName(new ResourceLocation("minecraft2", "electrum_sword")),
                    steel_sword = new SwordItem(toolmateriallist.STEEL, 3, -2.4f, new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName(new ResourceLocation("minecraft2", "steel_sword")),
                    titanium_sword = new SwordItem(toolmateriallist.TITANIUM, 3, -2.4f, new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName(new ResourceLocation("minecraft2", "titanium_sword")),
                    copper_shovel = new ShovelItem(toolmateriallist.COPPER, 1.5F, -3.0F, new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName(new ResourceLocation("minecraft2", "copper_shovel")),
                    bronze_shovel = new ShovelItem(toolmateriallist.BRONZE, 1.5F, -3.0F, new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName(new ResourceLocation("minecraft2", "bronze_shovel")),
                    silver_shovel = new ShovelItem(toolmateriallist.SILVER, 1.5F, -3.0F, new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName(new ResourceLocation("minecraft2", "silver_shovel")),
                    //brass_shovel = new ShovelItem(toolmateriallist.BRASS, 1.5F, -3.0F, new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName(new ResourceLocation("minecraft2", "brass_shovel")),
                    electrum_shovel = new ShovelItem(toolmateriallist.ELECTRUM, 1.5F, -3.0F, new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName(new ResourceLocation("minecraft2", "electrum_shovel")),
                    steel_shovel = new ShovelItem(toolmateriallist.STEEL, 1.5F, -3.0F, new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName(new ResourceLocation("minecraft2", "steel_shovel")),
                    titanium_shovel = new ShovelItem(toolmateriallist.TITANIUM, 1.5F, -3.0F, new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName(new ResourceLocation("minecraft2", "titanium_shovel")),
                    copper_hoe = new HoeItem(toolmateriallist.COPPER, -2.0F, new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName(new ResourceLocation("minecraft2", "copper_hoe")),
                    bronze_hoe = new HoeItem(toolmateriallist.BRONZE, -1.0F, new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName(new ResourceLocation("minecraft2", "bronze_hoe")),
                    silver_hoe = new HoeItem(toolmateriallist.SILVER, -1.0F, new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName(new ResourceLocation("minecraft2", "silver_hoe")),
                    //brass_hoe = new HoeItem(toolmateriallist.BRASS, -1.0F, new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName(new ResourceLocation("minecraft2", "brass_hoe")),
                    electrum_hoe = new HoeItem(toolmateriallist.ELECTRUM, -1.0F, new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName(new ResourceLocation("minecraft2", "electrum_hoe")),
                    steel_hoe = new HoeItem(toolmateriallist.STEEL, 0.0F, new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName(new ResourceLocation("minecraft2", "steel_hoe")),
                    titanium_hoe = new HoeItem(toolmateriallist.TITANIUM, 0.0F, new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName(new ResourceLocation("minecraft2", "titanium_hoe")),
                    flint_pickaxe = new PickaxeItem(toolmateriallist.FLINT, 1, -2.4f, new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName(new ResourceLocation("minecraft2", "flint_pickaxe")),
                    flint_axe = new AxeItem(toolmateriallist.FLINT, 2.0f, -3.2f, new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName(new ResourceLocation("minecraft2", "flint_axe")),
                    flint_sword = new SwordItem(toolmateriallist.FLINT, 3, -2.4f, new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName(new ResourceLocation("minecraft2", "flint_sword")),
                    //flint_shovel = new ShovelItem(toolmateriallist.FLINT, 1.5F, -3.0f, new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName(new ResourceLocation("minecraft2", "flint_shovel")),
                    //flint_hoe = new HoeItem(toolmateriallist.FLINT, -2.0F, new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName(new ResourceLocation("minecraft2", "flint_hoe")),
                    clairo_egg = new ClairoSpawnItem()





                    );
            Item.Properties properties = new Item.Properties()
                    .group(ItemGroup.DECORATIONS);
            event.getRegistry().register(new BlockItem(ModBlocks.furnaceblock, properties).setRegistryName("furnaceblock"));
            logger.info("items registered");
        }
        @SubscribeEvent
        public static void onTileEntityRegistry(final RegistryEvent.Register<TileEntityType<?>> event) {
            event.getRegistry().register(TileEntityType.Builder.create(furnaceBlockTile::new, ModBlocks.furnaceblock).build(null).setRegistryName("furnaceblock"));
        }

        @SubscribeEvent
        public static void TickEvent(TickEvent event)
        {
            logger.info("TICK");
        }
        @SubscribeEvent
        public static void onPotionRegistry(final RegistryEvent.Register<Potion> event)
        {
            immunity_potion = new Potion(new EffectInstance[]{new EffectInstance(Effects.RESISTANCE, 200, 2, true, false, false, null)});

            event.getRegistry().register(immunity_potion.setRegistryName("immunity_potion"));

        }



        @SubscribeEvent
        public static void onContainerRegistry(final RegistryEvent.Register<ContainerType<?>> event) {
            event.getRegistry().register(IForgeContainerType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                return new furnaceBlockContainer(windowId, inv.player.world, pos, inv, inv.player);
            }).setRegistryName("furnaceblock"));

            event.getRegistry().register(IForgeContainerType.create((windowId, inv, data) ->
            {
                //minecraft2mod.logger.info(data.readVarInt());
                return new ClairoContainer(windowId, inv.player.world,  inv, inv.player, inv.player.world.getEntityByID(data.readVarInt()));
            }).setRegistryName("clairo_entity"));
        }



    }


}

