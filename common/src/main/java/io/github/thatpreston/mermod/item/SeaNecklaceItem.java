package io.github.thatpreston.mermod.item;
import io.github.thatpreston.mermod.Mermod;
import io.github.thatpreston.mermod.item.modifier.*;
import io.github.thatpreston.mermod.registry.RegistryHandler;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.*;
import net.minecraft.tags.Tags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.*;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import org.jetbrains.annotations.Nullable;
import java.util.*;
import java.util.function.Consumer;
public class SeaNecklaceItem extends Item{
 private static final Component MODIFIERS_TOOLTIP=Component.translatable("tooltip.mermod.modifiers").withStyle(ChatFormatting.GRAY);
 private static final Component REMOVE_MODIFIERS_TOOLTIP=Component.translatable("tooltip.mermod.remove_modifiers").withStyle(ChatFormatting.GRAY);
 public SeaNecklaceItem(Item.Properties p){super(p);}
 public InteractionResult use(Level l,Player p,InteractionHand h){if(p.isCrouching()){ItemStack s=removeModifier(p.getItemInHand(h));if(!s.isEmpty()){p.addItem(s);return InteractionResult.SUCCESS;}}return super.use(l,p,h);}
 public void inventoryTick(ItemStack s,ServerLevel l,Entity e,@Nullable EquipmentSlot slot){if(e instanceof LivingEntity x&&slot==EquipmentSlot.CHEST)Mermod.addEffects(x);}
 public static void addModifiers(ItemStack n,List<ItemStack> list){NecklaceModifiers m=n.getOrDefault(RegistryHandler.NECKLACE_MODIFIERS_COMPONENT_TYPE.get(),NecklaceModifiers.EMPTY).copy();for(ItemStack s:list){NecklaceModifierItem i=(NecklaceModifierItem)s.getItem();NecklaceModifier mod=i.getModifier();if(s.is(Tags.Items.DYEABLE)){DyedItemColor c=s.get(DataComponents.DYED_COLOR);m.add(i.getType(),mod.withColor(c!=null?c.rgb():16777215));}else m.add(i.getType(),mod);}n.set(RegistryHandler.NECKLACE_MODIFIERS_COMPONENT_TYPE.get(),m);}
 public static boolean canAddModifiers(ItemStack n,List<ItemStack> list){NecklaceModifiers m=n.getOrDefault(RegistryHandler.NECKLACE_MODIFIERS_COMPONENT_TYPE.get(),NecklaceModifiers.EMPTY);ArrayList<String> a=new ArrayList<>();for(ItemStack s:list){NecklaceModifierItem i=(NecklaceModifierItem)s.getItem();String t=i.getType();if(a.contains(t)||m.contains(t))return false;a.add(t);}return true;}
 public static ItemStack removeModifier(ItemStack n){NecklaceModifiers m=n.get(RegistryHandler.NECKLACE_MODIFIERS_COMPONENT_TYPE.get());if(m!=null)for(NecklaceModifierItem i:NecklaceModifierItem.MODIFIERS)if(m.canRemove(i)){NecklaceModifiers c=m.copy();ItemStack s=c.remove(i);n.set(RegistryHandler.NECKLACE_MODIFIERS_COMPONENT_TYPE.get(),c);if(!s.isEmpty())return s;}return ItemStack.EMPTY;}
 public void appendHoverText(ItemStack s,TooltipContext c,TooltipDisplay d,Consumer<Component> out,TooltipFlag f){NecklaceModifiers m=s.get(RegistryHandler.NECKLACE_MODIFIERS_COMPONENT_TYPE.get());if(m!=null&&!m.modifiers().isEmpty()){out.accept(CommonComponents.EMPTY);out.accept(MODIFIERS_TOOLTIP);for(NecklaceModifier x:m.modifiers().values())out.accept(Component.literal(" ").append(Component.translatable("item.mermod."+x.id()+"_modifier").withStyle(Style.EMPTY.withColor(x.color()))));out.accept(REMOVE_MODIFIERS_TOOLTIP);}}
}
