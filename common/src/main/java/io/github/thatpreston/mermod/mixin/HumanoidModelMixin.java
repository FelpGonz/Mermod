package io.github.thatpreston.mermod.mixin;
import io.github.thatpreston.mermod.client.render.AvatarRenderStateExtension;
import io.github.thatpreston.mermod.client.render.model.TailModel;
import io.github.thatpreston.mermod.config.MermodConfig;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.util.Mth;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Mixin(HumanoidModel.class)
public abstract class HumanoidModelMixin<T extends HumanoidRenderState> extends EntityModel<T> implements ArmedModel,HeadedModel{
 @Shadow @Final public ModelPart rightArm; @Shadow @Final public ModelPart leftArm; @Shadow @Final public ModelPart head;
 public HumanoidModelMixin(ModelPart root){super(root);}
 @Inject(method="setupAnim(Lnet/minecraft/client/renderer/entity/state/HumanoidRenderState;)V",at=@At(value="CONSTANT",args="floatValue=26.0F",ordinal=0),cancellable=true)
 private void onSetupAnim(T state,CallbackInfo info){if(MermodConfig.shouldReplaceSwimAnimation()&&state instanceof AvatarRenderStateExtension ex&&ex.mermod$getTailStyle()!=null&&state.isInWater){float swim=state.swimAmount;if(!state.isUsingItem){float pos=state.ageInTicks*.2F+state.walkAnimationPos*.8F;float x=-Mth.PI*.9F+TailModel.getWaveHeight(pos,.7F,.035F,Mth.PI/10);float z=Mth.PI/10;this.rightArm.xRot=Mth.lerp(swim,this.rightArm.xRot,x);this.leftArm.xRot=Mth.lerp(swim,this.leftArm.xRot,x);this.rightArm.zRot=Mth.lerp(swim,this.rightArm.zRot,z);this.leftArm.zRot=Mth.lerp(swim,this.leftArm.zRot,-z);}this.head.xRot=Mth.rotLerpRad(swim,state.xRot*.017453292F,-Mth.PI/3);this.head.yRot=state.yRot*.017453292F*Mth.lerp(swim,1,.5F);info.cancel();}}
}
