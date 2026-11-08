package com.sysnote8.moremobility.client;

import com.sysnote8.moremobility.MoreMobility;
import io.github.foundationgames.automobility.Automobility;
import io.github.foundationgames.automobility.automobile.model.ModelDefinition;
import io.github.foundationgames.automobility.automobile.model.ModelType;
import io.github.foundationgames.automobility.automobile.render.AutomobileModels;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraft.client.model.geom.ModelLayerLocation;

@Mod(value = MoreMobility.MODID, dist = Dist.CLIENT)
public class MoreMobilityClient {
    public MoreMobilityClient(IEventBus modEventBus) {
        modEventBus.addListener(MoreMobilityClient::onClientSetup);
    }

    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ModelType advancedHarvester = ModelType.REGISTRY.register(new ModelType(
                    MoreMobility.id("harvester_front_attachment"),
                    AdvancedHarvesterFrontModel::new
            ));

            AutomobileModels.register(
                    MoreMobility.id("frontatt_harvester_advanced"),
                    ModelDefinition.of(
                            advancedHarvester,
                            ModelDefinition.RenderMaterial.CUTOUT,
                            AdvancedHarvesterFrontModel.MODEL_LAYER
                    )
            );
            AutomobileModels.register(
                    MoreMobility.id("engine_netherite"),
                    ModelDefinition.of(
                            ModelType.BASIC,
                            ModelDefinition.RenderMaterial.CUTOUT,
                            new ModelLayerLocation(Automobility.rl("automobile/engine/netherite"), "main")
                    )
            );
        });
    }
}
