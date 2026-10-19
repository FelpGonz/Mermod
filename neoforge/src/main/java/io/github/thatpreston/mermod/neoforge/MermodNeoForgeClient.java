package io.github.thatpreston.mermod.neoforge;
import io.github.thatpreston.mermod.Mermod;
import io.github.thatpreston.mermod.MermodClient;
import io.github.thatpreston.mermod.compat.MermodClothConfigScreen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import dev.architectury.platform.Platform;
@Mod(value=Mermod.MOD_ID,dist=Dist.CLIENT)
public class MermodNeoForgeClient{
 public MermodNeoForgeClient(ModContainer c,IEventBus b){MermodClient.init();if(Platform.isModLoaded("cloth_config"))c.registerExtensionPoint(IConfigScreenFactory.class,(container,parent)->MermodClothConfigScreen.build(parent));}
}
