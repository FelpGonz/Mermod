package io.github.thatpreston.mermod.fabric;

import io.github.thatpreston.mermod.Mermod;
import io.github.thatpreston.mermod.fabric.compat.TrinketsCompat;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class MermodFabric implements ModInitializer {
    public static boolean trinketsLoaded;
    @Override
    public void onInitialize() {
        FabricLoader loader = FabricLoader.getInstance();
        trinketsLoaded = loader.isModLoaded("trinkets");
        Mermod.init();
        Mermod.registerCauldronInteractions();
        if(trinketsLoaded) {
            TrinketsCompat.registerTrinket();
        }
    }
}
