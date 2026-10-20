package io.github.thatpreston.mermod.neoforge;

import io.github.thatpreston.mermod.Mermod;
import io.github.thatpreston.mermod.registry.RegistryHandler;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@Mod(Mermod.MOD_ID)
public class MermodNeoForge {
    public MermodNeoForge(IEventBus eventBus) {
        Mermod.init();
        eventBus.addListener(this::commonSetup);
        eventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(Mermod::registerCauldronInteractions);
    }

    private void addCreative(final BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(RegistryHandler.SEA_NECKLACE.get());
        }
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(RegistryHandler.SEA_CRYSTAL.get());
            event.accept(RegistryHandler.MERMAID_BRA_MODIFIER.get());
            event.accept(RegistryHandler.GLOWING_PEARL_MODIFIER.get());
            event.accept(RegistryHandler.TAIL_GRADIENT_MODIFIER.get());
            event.accept(RegistryHandler.TAIL_MOISTURIZER_MODIFIER.get());
            event.accept(RegistryHandler.MOON_ROCK_MODIFIER.get());
            event.accept(RegistryHandler.URSULA_SHELL_MODIFIER.get());
            event.accept(RegistryHandler.DEEP_SEA_CRYSTAL_MODIFIER.get());
        }
    }
}
