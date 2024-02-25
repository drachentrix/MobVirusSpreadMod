package org.drachentrix.plugins.mobvirusspread.ui;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.layouts.LayoutSettings;
import net.minecraft.client.gui.screens.OptionsScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.main.GameConfig;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.profiling.InactiveProfiler;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.client.gui.overlay.GuiOverlayManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.GameData;

import java.lang.reflect.Field;

public class ImmuneSystem {

    private static final Minecraft mc = Minecraft.getInstance();

    public ImmuneSystem() {

    }

    private static final ResourceLocation CUSTOM_PNG = new ResourceLocation("mobvirusspread", "textures/ui/immune_system.png");

    @SubscribeEvent
    public void onGuiRender(RenderGuiOverlayEvent.Post event) {
        if (Minecraft.getInstance().player != null && Minecraft.getInstance().screen instanceof InventoryScreen) {
            int scaling = Minecraft.getInstance().options.guiScale().get();
            if (scaling == 0) {
                scaling = 4; // TODO asuming having 1080px gui scale and option "auto" selected.
            }

            renderOverlay(event.getGuiGraphics(), (400) / scaling, 200 / scaling, CUSTOM_PNG);
        }
    }

    public static void renderOverlay(GuiGraphics guiGraphics, int x, int y, ResourceLocation resourceLocation) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, resourceLocation);

        RenderSystem.enableDepthTest();
        RenderSystem.depthFunc(519);

        guiGraphics.blit(resourceLocation, x, y, 8, 8, 8, 8); // TODO die werte hier nochmal nachgooglen was das bedeutet

        RenderSystem.depthFunc(515);
        RenderSystem.disableDepthTest();
    }


}