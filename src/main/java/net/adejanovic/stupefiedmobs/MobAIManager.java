package net.adejanovic.stupefiedmobs;

import net.minecraft.world.entity.Mob;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class MobAIManager {
    private static final Logger LOGGER = LogManager.getLogger();

    static void processMobAI(Mob mob) {
        String mobName = mob.getName().getString();

        if (shouldDisableAI(mobName)) {
            disableMobAI(mob, mobName);
        } else{
            enableMobAI(mob, mobName);
        }
    }

    static boolean shouldDisableAI(String mobName) {
        if (mobName.isEmpty()) return false;

        List<? extends String> patterns = Config.DISABLED_NAMES.get();

        for (String pattern : patterns) {
            if (matchesPattern(mobName, pattern)) {
                return true;
            }
        }

        return false;
    }

    static boolean matchesPattern(String text, String pattern) {
        if (pattern.equals("*")) return true;

        String regex = pattern
                .replace("*", ".*")
                .replace("?", ".");

        return text.toLowerCase().matches(".*" + regex.toLowerCase() + ".*");
    }

    static void disableMobAI(Mob mob, String mobName) {
        mob.setNoAi(true);

        if (Config.LOG_ACTIONS.get()) {
            LOGGER.info("Disabled AI for mob: {} at {}", mobName, mob.blockPosition());
        }
    }

    static void enableMobAI(Mob mob, String mobName) {
        mob.setNoAi(false);

        if (Config.LOG_ACTIONS.get()) {
            LOGGER.info("Re-enabled AI for mob: {} at {}", mobName, mob.blockPosition());
        }
    }
}
