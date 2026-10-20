package io.github.thatpreston.mermod.client.render;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
public final class MermodRenderTypes{
 private MermodRenderTypes(){}
 public static RenderType armorTranslucentCull(Identifier id){return RenderTypes.entityTranslucentCull(id);}
}
