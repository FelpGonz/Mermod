package io.github.thatpreston.mermod.mixin;

import io.github.thatpreston.mermod.Mermod;
import io.github.thatpreston.mermod.config.MermodConfig;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @ModifyArg(method = "travelInWater(Lnet/minecraft/world/phys/Vec3;DZD)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;moveRelative(FLnet/minecraft/world/phys/Vec3;)V", ordinal = 0))
    private float modifySwimAcceleration(float original) {
        if ((Object)this instanceof Player player && !Mermod.getNecklace(player).isEmpty()) {
            // 26.3 applies additional water drag after moveRelative. A stronger
            // acceleration multiplier restores the fast mermaid swimming feel.
            return original * (float)(MermodConfig.getSwimSpeedMultiplier() * 2.0D);
        }
        return original;
    }
}
