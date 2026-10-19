package io.github.thatpreston.mermod.item.modifier;
import net.minecraft.ChatFormatting;import net.minecraft.network.chat.Component;import net.minecraft.world.item.*;import net.minecraft.world.item.component.TooltipDisplay;import java.util.*;import java.util.function.Consumer;
public class NecklaceModifierItem extends Item{
 private static final Component MODIFIER_TOOLTIP=Component.translatable("tooltip.mermod.modifier").withStyle(ChatFormatting.GRAY);private static final Component DYEABLE_MODIFIER_TOOLTIP=Component.translatable("tooltip.mermod.dyeable_modifier").withStyle(ChatFormatting.GRAY);public static final List<NecklaceModifierItem> MODIFIERS=new ArrayList<>();private final String type;private final NecklaceModifier modifier;
 public NecklaceModifierItem(Item.Properties p,String t,NecklaceModifier m){super(p);type=t;modifier=m;MODIFIERS.add(this);}public String getType(){return type;}public NecklaceModifier getModifier(){return modifier;}
 public void appendHoverText(ItemStack s,TooltipContext c,TooltipDisplay d,Consumer<Component> out,TooltipFlag f){out.accept(s.has(net.minecraft.core.component.DataComponents.DYED_COLOR)?DYEABLE_MODIFIER_TOOLTIP:MODIFIER_TOOLTIP);}
}
