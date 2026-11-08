package com.sysnote8.moremobility.frame;

import com.sysnote8.moremobility.MoreMobility;
import io.github.foundationgames.automobility.automobile.AutomobileFrame;
import net.minecraft.resources.ResourceKey;

/** Resource keys for the data-driven frames supplied by this addon. */
public final class MMFrames {
    public static final ResourceKey<AutomobileFrame> SILVER = ResourceKey.create(
            AutomobileFrame.REGISTRY,
            MoreMobility.id("silver")
    );
    public static final ResourceKey<AutomobileFrame> RACE_SHOPPING_CART = ResourceKey.create(
            AutomobileFrame.REGISTRY,
            MoreMobility.id("race_shopping_cart")
    );

    private MMFrames() {
    }
}
