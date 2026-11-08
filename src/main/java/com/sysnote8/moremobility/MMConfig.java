package com.sysnote8.moremobility;

import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

public final class MMConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    private static final ModConfigSpec.BooleanValue OFFROAD_SLOWNESS = BUILDER
            .comment("Apply slow effect when driving on non-paved road")
            .define("offroadSlowness", true);

    public static final ModConfigSpec SPEC = BUILDER.build();
    public static volatile boolean offroadSlowness = true;

    private MMConfig() {
    }

    static void load(ModConfigEvent event) {
        if (event.getConfig().getSpec() == SPEC) {
            offroadSlowness = OFFROAD_SLOWNESS.get();
        }
    }
}
