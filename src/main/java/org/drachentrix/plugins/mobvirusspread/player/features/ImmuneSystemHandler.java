package org.drachentrix.plugins.mobvirusspread.player.features;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.network.CustomPayloadEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.SimpleChannel;
import org.drachentrix.plugins.mobvirusspread.MobVirusSpread;
import org.drachentrix.plugins.mobvirusspread.ui.ImmuneSystemUI;

import java.util.function.Supplier;

public class ImmuneSystemHandler {
    public static final int PROTOCOL_VERSION = 1;

    public static SimpleChannel INSTANCE;

    private final float value;

    public ImmuneSystemHandler(FriendlyByteBuf buffer) {
        this.value = buffer.readFloat();
    }

    public ImmuneSystemHandler(float value) {
        this.value = value;
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeFloat(value);
    }

    public void handle(CustomPayloadEvent.Context context) {
        context.enqueueWork(() -> {
            if (!context.getDirection().getOriginationSide().isClient()) {
                ImmuneSystemUI.immuneSystem = value;
            } else {
                // Server side code
            }
        });
        context.setPacketHandled(true);
    }

    public static void sendToClient(ImmuneSystemHandler handler, Player player) {
        if (player instanceof LocalPlayer localPlayer) {
            INSTANCE.send(handler, localPlayer.connection.getConnection());
        } else if (player instanceof ServerPlayer serverPlayer) {
            INSTANCE.send(handler, serverPlayer.connection.getConnection());
        }
    }

    public static void sendToServer(ImmuneSystemHandler handler) {
        INSTANCE.send(handler, Minecraft.getInstance().getConnection().getConnection());
    }
}
