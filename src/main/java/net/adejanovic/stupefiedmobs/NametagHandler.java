package net.adejanovic.stupefiedmobs;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;


public class NametagHandler {
    private static final Logger LOGGER = LogManager.getLogger();

    @SubscribeEvent
    public void onEntityInteract(PlayerInteractEvent.EntityInteract event) {

        // Check if player is using a name tag on a mob
        ItemStack item = event.getItemStack();
        if (item.getItem() == Items.NAME_TAG && event.getTarget() instanceof Mob mob) {
            if (Config.LOG_ACTIONS.get()) {
                LOGGER.info("Player using nametag on {}", event.getEntity());
            }

            if(event.getLevel().isClientSide()){
                return;
            }

            // Schedule AI check for next tick since the name change happens after this event
            Objects.requireNonNull(event.getLevel().getServer()).execute(() -> {
                MobAIManager.processMobAI(mob);
            });

        }
    }
}
