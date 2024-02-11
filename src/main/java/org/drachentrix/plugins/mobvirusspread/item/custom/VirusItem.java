package org.drachentrix.plugins.mobvirusspread.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.drachentrix.plugins.mobvirusspread.effect.VirusEffects;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Create:
 *
 * @author Drachentrix (Florian)
 */
public class VirusItem extends Item {

    public VirusItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        if (!level.isClientSide){
            if (player.hasEffect(VirusEffects.INFECTED.get())){
                if (player.getEffect(VirusEffects.INFECTED.get()).getAmplifier() < 3){
                    player.addEffect(new MobEffectInstance(VirusEffects.INFECTED.get(), 1000,
                            player.getEffect(VirusEffects.INFECTED.get()).getAmplifier() + 1 ));
                } else{
                    player.sendSystemMessage(Component.literal("Dieser Effect ist schon auf dem Maximum"));
                }
            }else {
                player.addEffect(new MobEffectInstance(VirusEffects.INFECTED.get(), 1000, 0));
            }
        }
        return super.use(level, player, interactionHand);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {
        if (Screen.hasShiftDown()){
            components.add(Component.literal("A weird looking Virus that seems to infect people, when they Right Click it")
                    .withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.DARK_RED));
        }else{
            components.add(Component.literal("Press SHIFT for more info").withStyle(ChatFormatting.BOLD));
        }
        super.appendHoverText(itemStack, level, components, tooltipFlag);
    }
}
