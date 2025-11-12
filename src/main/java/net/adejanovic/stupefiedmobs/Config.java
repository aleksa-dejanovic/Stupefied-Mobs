package net.adejanovic.stupefiedmobs;

import java.util.Arrays;
import java.util.List;
import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec SPEC;

    public static final ModConfigSpec.ConfigValue<List<? extends String>> DISABLED_NAMES;
    public static final ModConfigSpec.BooleanValue DISABLE_ON_SPAWN;
    public static final ModConfigSpec.BooleanValue LOG_ACTIONS;

    static {
        BUILDER.push("Stupefy Mobs Configuration");

        DISABLED_NAMES = BUILDER
                .comment("List of mob name patterns to disable AI for (supports * wildcard)")
                .defineListAllowEmpty(
                        List.of("disabledNames"),
                        () -> Arrays.asList("*noai*", "*disabled*", "*passive*", "*stupefied*"),
                        obj -> obj instanceof String
                );

        DISABLE_ON_SPAWN = BUILDER
                .comment("Whether to disable AI when mobs spawn (recommended: true)")
                .define("disableOnSpawn", true);

        LOG_ACTIONS = BUILDER
                .comment("Log when AI is disabled for mobs (for developers)")
                .define("logActions", false);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }

}
