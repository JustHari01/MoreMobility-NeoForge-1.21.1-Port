package com.sysnote8.moremobility.engine;

import com.sysnote8.moremobility.MoreMobility;
import io.github.foundationgames.automobility.automobile.AutomobileEngine;
import net.minecraft.resources.ResourceKey;

/** Resource keys for the data-driven engine supplied by this addon. */
public final class MMEngines {
    public static final ResourceKey<AutomobileEngine> NETHERITE = ResourceKey.create(
            AutomobileEngine.REGISTRY,
            MoreMobility.id("netherite")
    );

    private MMEngines() {
    }
}
