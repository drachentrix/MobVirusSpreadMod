package org.drachentrix.plugins.mobvirusspread;

import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.drachentrix.plugins.mobvirusspread.block.VirusBlocks;
import org.drachentrix.plugins.mobvirusspread.effect.EffectDurationChecker;
import org.drachentrix.plugins.mobvirusspread.effect.VirusEffects;
import org.drachentrix.plugins.mobvirusspread.item.VirusCreativeTab;
import org.drachentrix.plugins.mobvirusspread.item.VirusCure;
import org.drachentrix.plugins.mobvirusspread.mob.MobInfectedSpawn;
import org.drachentrix.plugins.mobvirusspread.mob.MobNearbyInfectedScanner;
import org.drachentrix.plugins.mobvirusspread.ui.ImmuneSystem;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MobVirusSpread.MOD_ID)
public class MobVirusSpread {

    public static final String MOD_ID = "mobvirusspread";
    private static final Logger LOGGER = LogUtils.getLogger();

    public MobVirusSpread() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        VirusCreativeTab.register(modEventBus);
        VirusCure.register(modEventBus);
        VirusEffects.register(modEventBus);
        VirusBlocks.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(new EffectDurationChecker());
        MinecraftForge.EVENT_BUS.register(new ImmuneSystem());


        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(MobNearbyInfectedScanner.class);
        MinecraftForge.EVENT_BUS.register(MobInfectedSpawn.class);

        modEventBus.addListener(this::addCreative);
        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    public void addCreative(BuildCreativeModeTabContentsEvent event){
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES){
            event.accept(VirusCure.CURE);
            event.accept(VirusCure.VIRUS);
            event.accept(VirusBlocks.VOIDBERRY_CROP);
            event.accept(VirusBlocks.VOIDDIRT_BLOCK);
        }
    }


    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            ItemBlockRenderTypes.setRenderLayer(VirusBlocks.VOIDBERRY_CROP.get(), RenderType.cutout());
        }
    }
}
