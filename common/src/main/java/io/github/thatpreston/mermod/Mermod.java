package io.github.thatpreston.mermod;
import io.github.thatpreston.mermod.config.MermodConfig;
import io.github.thatpreston.mermod.registry.RegistryHandler;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
public class Mermod {
 public static final String MOD_ID="mermod";
 public static void init(){RegistryHandler.register();}
 public static void registerCauldronInteractions(){}
 public static ItemStack getNecklaceFromAccessorySlot(Player p){return MermodPlatform.getNecklaceFromAccessorySlot(p);}
 public static ItemStack getNecklace(Player p){ItemStack s=getNecklaceFromAccessorySlot(p);if(!s.isEmpty())return s;s=p.getItemBySlot(EquipmentSlot.CHEST);return s.is(RegistryHandler.SEA_NECKLACE.get())?s:ItemStack.EMPTY;}
 public static void addEffects(LivingEntity e){if(e.isInWater()&&e instanceof ServerPlayer p){if(MermodConfig.isWaterBreathingEnabled())p.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING,250,0,true,false));if(MermodConfig.isNightVisionEnabled())p.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION,250,0,true,false));}}
}
