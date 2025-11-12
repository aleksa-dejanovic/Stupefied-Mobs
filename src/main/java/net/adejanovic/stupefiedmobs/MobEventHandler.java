package net.adejanovic.stupefiedmobs;

import net.minecraft.world.entity.Mob;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class MobEventHandler {
    private static final Logger LOGGER = LogManager.getLogger();


    @SubscribeEvent
    public void onEntitySpawn(EntityJoinLevelEvent event) {
        if (Config.LOG_ACTIONS.get()) {
            LOGGER.info("Detected mob spawning with name {}", event.getEntity());
        }
        if (!Config.DISABLE_ON_SPAWN.get() || !(event.getEntity() instanceof Mob mob)) {
            return;
        }

        MobAIManager.processMobAI(mob);
    }






}
