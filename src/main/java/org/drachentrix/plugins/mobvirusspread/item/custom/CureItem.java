package org.drachentrix.plugins.mobvirusspread.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.drachentrix.plugins.mobvirusspread.effect.VirusEffects;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CureItem extends Item {

    public CureItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        if (!level.isClientSide()) {
            if (player.getEffect(VirusEffects.INFECTED.get()) != null) {
                player.sendSystemMessage(Component.literal("You have healed yourself succesfully now dont die!! :)"));
                player.removeEffect(VirusEffects.INFECTED.get());
                player.removeEffect(MobEffects.CONFUSION);
                player.removeEffect(MobEffects.WITHER);
                player.removeEffect(MobEffects.DIG_SLOWDOWN);
                player.removeEffect(MobEffects.BLINDNESS);
                player.removeEffect(MobEffects.POISON);
            } else {
                player.sendSystemMessage(Component.literal("You are not ill!"));
            }
        }
        return super.use(level, player, interactionHand);
    }
    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {
        if (Screen.hasShiftDown()){
            components.add(Component.literal("This syringe seems to cure the Infection, but be careful. Use it to often" +
                            "and maybe there will be some resistance from the virus \n Right Click to inject")
                    .withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.BLUE));
        }else{
            components.add(Component.literal("Press SHIFT for more info").withStyle(ChatFormatting.BOLD));
        }
        super.appendHoverText(itemStack, level, components, tooltipFlag);
    }
}
