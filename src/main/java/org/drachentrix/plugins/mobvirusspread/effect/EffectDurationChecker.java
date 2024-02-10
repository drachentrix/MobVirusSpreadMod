package org.drachentrix.plugins.mobvirusspread.effect;

import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EffectDurationChecker {

    private int tickCounter = 0;

    @SubscribeEvent
    public void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            tickCounter++;

            // Check every 25 seconds (20 ticks per second in Minecraft)
            if (tickCounter >= 25 * 20) {
                tickCounter = 0; // Reset tick counter

                // Call the method to check effect duration
                InfectedEffect.checkEffectDurations();
            }
        }
    }
}
