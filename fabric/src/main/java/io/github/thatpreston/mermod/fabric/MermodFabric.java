package io.github.thatpreston.mermod.fabric;
import io.github.thatpreston.mermod.Mermod;
import net.fabricmc.api.ModInitializer;
public class MermodFabric implements ModInitializer{public void onInitialize(){Mermod.init();Mermod.registerCauldronInteractions();}}
