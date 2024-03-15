package org.drachentrix.plugins.mobvirusspread.player.features;


import com.mojang.serialization.Decoder;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.*;
import org.apache.logging.log4j.core.jmx.Server;
import org.drachentrix.plugins.mobvirusspread.MobVirusSpread;
import org.drachentrix.plugins.mobvirusspread.player.FoodValues;
import org.drachentrix.plugins.mobvirusspread.ui.ImmuneSystemUI;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

// Server Side class
public class ImmuneSystem {

    private static final Map<LivingEntity, ImmuneSystem> PLAYER_IMMUNE_SYSTEM = new HashMap<>();
    private final LivingEntity entity;
    public float immuneSystem = 100f;
    public int resistanz = 1; // prozent wie sehr die Person resistent ist z.B. mit armor wird weniger verbraucht
    public FoodValues foodValues = new FoodValues();

    private ImmuneSystem(LivingEntity livingEntity) {
        this.entity = livingEntity;

        // TODO load configs here
    }

    public static ImmuneSystem getByPlayer(LivingEntity entity) {
        return PLAYER_IMMUNE_SYSTEM.get(entity);
    }

    public static ImmuneSystem of(LivingEntity entity) {
        // TODO read from file, like double or idk
        ImmuneSystem curImmuneSystem = new ImmuneSystem(entity);
        PLAYER_IMMUNE_SYSTEM.put(entity, curImmuneSystem);

        return curImmuneSystem;
    }

    public static ImmuneSystem disconnect(LivingEntity entity) {
        ImmuneSystem curImmuneSystem = getByPlayer(entity);
        PLAYER_IMMUNE_SYSTEM.remove(entity);
        return curImmuneSystem;
    }


    public float getImmuneSystem() {
        return immuneSystem;
    }

    public void setImmuneSystem(float immuneSystem) {
        if (immuneSystem != this.immuneSystem) {
            this.immuneSystem = immuneSystem;
            updateClient(entity);
        }
    }

    public void onDamage(LivingEntity entity) {
        immuneSystem -= 1 / (float) resistanz;
        updateClient(entity);
    }

    public void onEatingFood(ItemStack itemStack, LivingEntity entity) {
        if (!foodValues.storedFoods.containsKey(itemStack)) {
            foodValues.addToList(itemStack, entity);
        }
        Player player = (Player) entity;
        immuneSystem += foodValues.storedFoods.get(itemStack);

        foodValues.storedFoods.replace(itemStack, (foodValues.storedFoods.get(itemStack) - player.getFoodData().getFoodLevel() -
                new Random().nextInt(1, 10) + new Random().nextInt(1, 5)) * (resistanz / 100));


        if (immuneSystem > 100) {
            immuneSystem = 100;
        } else if (immuneSystem < 0) {
            immuneSystem = 0;
        } else {
            updateClient(entity);
        }
    }

    public void updateClient(LivingEntity player) {

        ImmuneSystemHandler.sendToClient(new ImmuneSystemHandler(immuneSystem), (Player) player);
    }

}
