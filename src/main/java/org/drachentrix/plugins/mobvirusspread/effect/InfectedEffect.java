package org.drachentrix.plugins.mobvirusspread.effect;


import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.OutgoingChatMessage;
import net.minecraft.network.chat.PlayerChatMessage;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;


/**
 * Create:
 *
 * @author Drachentrix (Florian)
 */
public class InfectedEffect extends MobEffect {

    private static LivingEntity entityG;
    private static int amplifier;

    protected InfectedEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

    @Override
    public void onEffectStarted(LivingEntity entity, int pAmplifier) {
        applyEffectTick(entity, pAmplifier);
    }


    @Override
    public void applyEffectTick(LivingEntity entity, int pAmplifier) {
        float reductionMultiplier = 0.00f;
        entityG = entity;
        amplifier = pAmplifier;
        Player player = (Player) entity;
        PlayerChatMessage chatMessage = PlayerChatMessage.unsigned(player.getUUID(), "Amplifiy: " + pAmplifier);
        player.createCommandSourceStack().sendChatMessage(new OutgoingChatMessage.Player(chatMessage), false,
             ChatType.bind(ChatType.CHAT, player));

        switch (pAmplifier) {
            case 0:
                reductionMultiplier = 0.005f;
                break;
            case 1:
                reductionMultiplier = 0.01f;
                entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, Integer.MAX_VALUE, 0));
                entity.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, Integer.MAX_VALUE, 1));
                break;
            case 2:
                reductionMultiplier = 0.021f;
                entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, Integer.MAX_VALUE, 3));
                entity.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, Integer.MAX_VALUE, 3));
                entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, Integer.MAX_VALUE, 3));
                entity.addEffect(new MobEffectInstance(MobEffects.POISON, Integer.MAX_VALUE, 2));
                break;
            case 4:
                reductionMultiplier = 0.03f;
                entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, Integer.MAX_VALUE, 4));
                entity.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, Integer.MAX_VALUE, 4));
                entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, Integer.MAX_VALUE, 4));
                entity.addEffect(new MobEffectInstance(MobEffects.POISON, Integer.MAX_VALUE, 4));
                entity.addEffect(new MobEffectInstance(MobEffects.WITHER, Integer.MAX_VALUE, 4));

        }

        float speedMultiplier = 1.0f - (pAmplifier * reductionMultiplier);
        entity.setSpeed(speedMultiplier);

        float jumpMultiplier = 1.0f - (pAmplifier * reductionMultiplier);
        entity.setDeltaMovement(entity.getX(), entity.getY() - jumpMultiplier, entity.getZ());



        super.applyEffectTick(entity, pAmplifier);
    }

    public static void checkEffectDurations(){
        if (entityG != null && entityG.hasEffect(VirusEffects.INFECTED.get())){
            if (entityG.getEffect(VirusEffects.INFECTED.get()).getDuration() <= 500 && amplifier < 4) {
                entityG.removeAllEffects();
                entityG.addEffect(new MobEffectInstance(VirusEffects.INFECTED.get(), 1000, amplifier + 1));
            }
        }
    }


    @Override
    public boolean isInstantenous() {
        return true;
    }

}
