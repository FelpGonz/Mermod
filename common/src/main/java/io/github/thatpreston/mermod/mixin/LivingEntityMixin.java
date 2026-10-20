package io.github.thatpreston.mermod.mixin;

import io.github.thatpreston.mermod.Mermod;
import io.github.thatpreston.mermod.config.MermodConfig;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Inject(method = "travelInWater(Lnet/minecraft/world/phys/Vec3;DZD)V", at = @At("TAIL"))
    private void mermod$boostSurvivalSwimming(Vec3 input, double gravity, boolean falling, double friction, CallbackInfo ci) {
        LivingEntity self = (LivingEntity)(Object)this;
        if (self instanceof Player player && !player.getAbilities().flying && !Mermod.getNecklace(player).isEmpty() && player.isInWater()) {
            Vec3 velocity = self.getDeltaMovement();
            double horizontal = MermodConfig.getSwimSpeedMultiplier();
            self.setDeltaMovement(velocity.x * horizontal, velocity.y, velocity.z * horizontal);
        }
    }
}
