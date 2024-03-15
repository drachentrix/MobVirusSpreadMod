package org.drachentrix.plugins.mobvirusspread.ui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.drachentrix.plugins.mobvirusspread.player.features.ImmuneSystem;

@OnlyIn(Dist.CLIENT)
public class ImmuneSystemUI {
    public static float immuneSystem = 100f;
    private static final Minecraft mc = Minecraft.getInstance();

    public ImmuneSystemUI() {

    }

    private static final ResourceLocation CUSTOM_PNG = new ResourceLocation("mobvirusspread", "textures/ui/immune_system.png");

    @SubscribeEvent
    public void onGuiRender(RenderGuiOverlayEvent.Post event) {
        if (Minecraft.getInstance().player != null) {
            int scaling = Minecraft.getInstance().options.guiScale().get();
            if (scaling == 0) {
                scaling = 4; // TODO asuming having 1080px gui scale and option "auto" selected.
            }

            int screenWidth = mc.getWindow().getGuiScaledWidth() * scaling;
            int screenHeight = mc.getWindow().getGuiScaledHeight() * scaling;

            int heartsBarX = screenWidth / 2 - 9 * scaling;
            int heartsBarY = screenHeight - 56 * scaling; // TODO bei einer stage ändert sich das blut nicht korrekt (2 blut glaub ich); 2 letze stages hionzufügen, wo das blut langsam herunterrinnen beginnt.

            // (int) (((System.currentTimeMillis() / 1000 % 20) + 1) * 16)
            renderOverlay(event.getGuiGraphics(), heartsBarX, heartsBarY, 18, 20, 304, 20, getOffsetXForImmuneSystem(immuneSystem), 0, scaling, CUSTOM_PNG);
        }
    }

    // value: 100 ->  0 * 16 -> 0
    // value:  75 ->  5 * 16 -> 80
    // value:  50 -> 10 * 16 -> 160
    // value:  25 -> 15 * 16 -> 240
    // value:   0 -> 20 * 16 -> 320
    private static int getOffsetXForImmuneSystem(float value) {
        //return Math.round(value) / 5 * 16;
        System.out.println(value);
        return ((100 - Math.round(value)) / 5) * 16;
    }

    public static void renderOverlay(GuiGraphics guiGraphics, int x, int y, int displayWidth, int displayHeight, int imageWidth, int imageHeight, int offsetX, int offsetY, int scale, ResourceLocation resourceLocation) {
        x /= scale;
        y /= scale;


        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, resourceLocation);

        RenderSystem.enableDepthTest();
        RenderSystem.depthFunc(519);

        guiGraphics.blit(resourceLocation, x, y, offsetX, offsetY, displayWidth, displayHeight, imageWidth, imageHeight);

        RenderSystem.depthFunc(515);
        RenderSystem.disableDepthTest();
    }


}