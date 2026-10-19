package io.github.thatpreston.mermod.neoforge;

import io.github.thatpreston.mermod.Mermod;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(Mermod.MOD_ID)
public class MermodNeoForge {
    public MermodNeoForge(IEventBus eventBus) {
        Mermod.init();
        eventBus.addListener(this::commonSetup);
    }
    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(Mermod::registerCauldronInteractions);
    }
}
