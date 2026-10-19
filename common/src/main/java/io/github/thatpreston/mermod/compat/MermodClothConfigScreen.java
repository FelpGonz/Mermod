package io.github.thatpreston.mermod.compat;
import io.github.thatpreston.mermod.config.MermodConfig;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
public class MermodClothConfigScreen {
 public static Screen build(Screen parent) {
  ConfigBuilder builder=ConfigBuilder.create().setParentScreen(parent);
  var category=builder.getOrCreateCategory(Component.empty());
  var entry=builder.entryBuilder();
  category.addEntry(entry.startBooleanToggle(Component.translatable("option.mermod.disableNightVisionFlashing"),MermodConfig.shouldDisableNightVisionFlashing()).setDefaultValue(true).setSaveConsumer(v -> {}).build());
  category.addEntry(entry.startBooleanToggle(Component.translatable("option.mermod.replaceSwimAnimation"),MermodConfig.shouldReplaceSwimAnimation()).setDefaultValue(true).setSaveConsumer(v -> {}).build());
  return builder.build();
 }
}
