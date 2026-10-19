package io.github.thatpreston.mermod.client.render.model;
import com.mojang.blaze3d.vertex.PoseStack;
import io.github.thatpreston.mermod.client.render.*;
import io.github.thatpreston.mermod.mixin.ModelPartAccessor;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.OrderedSubmitNodeCollector;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.util.ARGB;
import net.minecraft.util.Mth;
import java.util.*;
import java.util.function.BiConsumer;
public class TailModel extends EntityModel<AvatarRenderState>{
 private static final BiConsumer<ModelPart,Float> SET_Z=(p,v)->p.zRot=v;
 private static final BiConsumer<ModelPart,Float> ADD_X=(p,v)->p.xRot+=v;
 public final ModelPart body; private final ModelPart bra,waist,tail1,tail2,tail3,tail4,tail5,tail6,tail7,fin; private final ModelPart[] parts,tailParts;
 public TailModel(ModelPart r){super(r);body=r.getChild("body");bra=body.getChild("bra");waist=body.getChild("waist");tail1=waist.getChild("tail1");tail2=tail1.getChild("tail2");tail3=tail2.getChild("tail3");tail4=tail3.getChild("tail4");tail5=tail4.getChild("tail5");tail6=tail5.getChild("tail6");tail7=tail6.getChild("tail7");fin=tail7.getChild("fin");parts=new ModelPart[]{waist,tail1,tail2,tail3,tail4,tail5,tail6,tail7,fin};tailParts=new ModelPart[]{tail1,tail2,tail3,tail4,tail5,tail6,tail7};}
 public static float getWaveHeight(float p,float s,float sc){return Mth.sin(Mth.TWO_PI*p*s)*sc;}
 public static float getWaveHeight(float p,float o,float s,float sc){return Mth.sin(Mth.TWO_PI*(p*s+o))*sc;}
 private void animateWave(float p,float s,float sc,BiConsumer<ModelPart,Float> c){for(int i=0;i<tailParts.length;i++)c.accept(tailParts[i],getWaveHeight(p,-.1F*i,s,sc));}
 public void setupAnim(AvatarRenderState st){super.setupAnim(st);AvatarRenderStateExtension ex=(AvatarRenderStateExtension)st;float sw=st.swimAmount;if(st.isPassenger){tail1.xRot=-Mth.PI/2;tail2.xRot=tail3.xRot=tail4.xRot=tail5.xRot=tail6.xRot=tail7.xRot=fin.xRot=Mth.PI/90;animateWave(st.ageInTicks,.01F,Mth.PI/60,ADD_X);fin.xRot=Mth.PI/60;}else if(ex.mermod$isOnGround()){float w=Mth.PI/24*st.walkAnimationSpeed*(1-sw);tail1.xRot=tail2.xRot=tail3.xRot=tail4.xRot=tail5.xRot=tail6.xRot=tail7.xRot=fin.xRot=st.isCrouching?Mth.PI/16:Mth.PI/14;animateWave(st.walkAnimationPos,.06F,w,SET_Z);}else{float p=st.ageInTicks*.2F+st.walkAnimationPos*.6F;if(!st.isFallFlying){tail1.xRot=tail2.xRot=tail3.xRot=tail4.xRot=tail5.xRot=Mth.PI/16;tail6.xRot=Mth.PI/32;}animateWave(p,.07F,Mth.PI/20,ADD_X);fin.xRot=tail7.xRot*2;}if(sw>0){if(st.isInWater){float p=st.ageInTicks*.2F+st.walkAnimationPos*.8F;animateWave(p,.035F,Mth.PI/12,(part,v)->part.xRot=Mth.lerp(sw,part.xRot,v));fin.xRot=Mth.lerp(sw,fin.xRot,tail7.xRot*2);}else{for(ModelPart p:tailParts)p.xRot=Mth.lerp(sw,p.xRot,0);fin.xRot=Mth.lerp(sw,fin.xRot,0);animateWave(st.walkAnimationPos,.06F,Mth.PI/32*st.walkAnimationSpeed,SET_Z);}}}
 public void submit(PoseStack s,OrderedSubmitNodeCollector c,int light,int overlay,TailStyle style,RenderType type,int outline){if(style.hasBra())c.submitModelPart(bra,s,type,light,overlay,null,style.braColor(),outline);renderParts(parts,s,c,light,overlay,type,style);}
 private void renderParts(ModelPart[] ps,PoseStack s,OrderedSubmitNodeCollector c,int light,int overlay,RenderType type,TailStyle st){for(int i=0;i<ps.length;i++){s.pushPose();int a=st.hasGradient()?ARGB.srgbLerp((float)i/(ps.length-1),st.tailColor(),st.gradientColor()):st.tailColor();int b=st.hasGradient()?st.gradientColor():st.tailColor();renderPart(ps[i],s,c,light,overlay,type,a,b);}for(int i=0;i<ps.length;i++)s.popPose();}
 private void renderPart(ModelPart p,PoseStack s,OrderedSubmitNodeCollector c,int light,int overlay,RenderType type,int a,int b){p.translateAndRotate(s);List<ModelPart.Cube> cs=((ModelPartAccessor)(Object)p).getCubes();for(int i=0;i<cs.size();i++){int col=i==0?a:b;ModelPart.Cube cube=cs.get(i);c.submitCustomGeometry(s,type,(pose,consumer)->cube.compile(pose,consumer,light,overlay,col));}}
}
