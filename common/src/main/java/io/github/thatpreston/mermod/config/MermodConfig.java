package io.github.thatpreston.mermod.config;

public final class MermodConfig {
    private MermodConfig() {}

    public static double getSwimSpeedMultiplier() { return 1.5D; }
    public static boolean isWaterBreathingEnabled() { return true; }
    public static boolean isNightVisionEnabled() { return true; }
    public static boolean isAquaAffinityEnabled() { return true; }
    public static boolean shouldDisableNightVisionFlashing() { return true; }
    public static boolean shouldReplaceSwimAnimation() { return true; }
}
