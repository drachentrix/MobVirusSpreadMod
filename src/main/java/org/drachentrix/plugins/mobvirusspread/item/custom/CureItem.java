package org.drachentrix.plugins.mobvirusspread.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.drachentrix.plugins.mobvirusspread.effect.VirusEffects;

/**
 * Create:
 *
 * @author Drachentrix (Florian)
 */
public class CureItem extends Item {

    public CureItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        if (!level.isClientSide()) {
            if (player.getEffect(VirusEffects.INFECTED.get()) != null) {
                player.sendSystemMessage(Component.literal("Du hast dich erfolgreich geheilt und jetzt stirb nicht!! :)"));
                player.removeEffect(VirusEffects.INFECTED.get());
                player.removeEffect(MobEffects.CONFUSION);
                player.removeEffect(MobEffects.WITHER);
                player.removeEffect(MobEffects.DIG_SLOWDOWN);
                player.removeEffect(MobEffects.BLINDNESS);
                player.removeEffect(MobEffects.POISON);
            } else {
                player.sendSystemMessage(Component.literal("Du bist ned krank du wixxer!"));
            }
        }
        return super.use(level, player, interactionHand);
    }
}
