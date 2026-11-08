package com.sysnote8.moremobility;

import com.sysnote8.moremobility.attachment.MMAttachments;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.config.ModConfigEvent;

@Mod(MoreMobility.MODID)
public class MoreMobility {

    public static final String MODID = "moremobility";
    public MoreMobility(IEventBus modEventBus, ModContainer modContainer) {
        MMAttachments.init();
        modContainer.registerConfig(ModConfig.Type.COMMON, MMConfig.SPEC);
        modEventBus.addListener(MoreMobility::onConfigLoading);
        modEventBus.addListener(MoreMobility::onConfigReloading);
    }

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }

    private static void onConfigLoading(ModConfigEvent.Loading event) {
        MMConfig.load(event);
    }

    private static void onConfigReloading(ModConfigEvent.Reloading event) {
        MMConfig.load(event);
    }
}
