package io.github.thatpreston.mermod.neoforge;

import io.github.thatpreston.mermod.Mermod;
import io.github.thatpreston.mermod.MermodClient;
import io.github.thatpreston.mermod.client.render.TailRenderLayer;
import io.github.thatpreston.mermod.client.render.model.TailLayerDefinitions;
import io.github.thatpreston.mermod.compat.MermodClothConfigScreen;
import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import dev.architectury.platform.Platform;

@Mod(value = Mermod.MOD_ID, dist = Dist.CLIENT)
public class MermodNeoForgeClient {
    public MermodNeoForgeClient(ModContainer container, IEventBus bus) {
        bus.addListener(this::registerLayerDefinitions);
        bus.addListener(this::addPlayerLayers);
        if (Platform.isModLoaded("cloth_config")) {
            container.registerExtensionPoint(IConfigScreenFactory.class,
                (c, parent) -> MermodClothConfigScreen.build(parent));
        }
    }

    private void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(MermodClient.DEFAULT_TAIL_LAYER, TailLayerDefinitions::getDefault);
        event.registerLayerDefinition(MermodClient.H2O_TAIL_LAYER, TailLayerDefinitions::getDefaultDorsalFins);
        event.registerLayerDefinition(MermodClient.SIREN_TAIL_LAYER, TailLayerDefinitions::getSiren);
    }

    private void addPlayerLayers(EntityRenderersEvent.AddLayers event) {
        for (var type : event.getSkins()) {
            AvatarRenderer<?> renderer = event.getPlayerRenderer(type);
            if (renderer != null) {
                renderer.addLayer(new TailRenderLayer(renderer, event.getEntityModels()));
            }
        }
    }
}
