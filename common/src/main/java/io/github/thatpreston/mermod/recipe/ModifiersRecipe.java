package io.github.thatpreston.mermod.recipe;
import com.mojang.serialization.MapCodec;
import io.github.thatpreston.mermod.item.SeaNecklaceItem;
import io.github.thatpreston.mermod.item.modifier.NecklaceModifierItem;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import java.util.ArrayList;
import java.util.List;
public class ModifiersRecipe extends CustomRecipe {
 public static final ModifiersRecipe INSTANCE=new ModifiersRecipe();
 public static final MapCodec<ModifiersRecipe> CODEC=MapCodec.unit(INSTANCE);
 public static final StreamCodec<RegistryFriendlyByteBuf,ModifiersRecipe> STREAM_CODEC=StreamCodec.unit(INSTANCE);
 public static final RecipeSerializer<ModifiersRecipe> SERIALIZER=new RecipeSerializer<>(CODEC,STREAM_CODEC);
 public ModifiersRecipe(){}
 public boolean matches(CraftingInput input,Level level){
  ItemStack necklace=null; List<ItemStack> modifiers=new ArrayList<>();
  for(int i=0;i<input.size();i++){ItemStack stack=input.getItem(i); if(!stack.isEmpty()){if(stack.getItem() instanceof SeaNecklaceItem){if(necklace!=null)return false; necklace=stack;}else{if(!(stack.getItem() instanceof NecklaceModifierItem))return false; modifiers.add(stack);}}}
  return necklace!=null&&!modifiers.isEmpty()&&SeaNecklaceItem.canAddModifiers(necklace,modifiers);
 }
 public ItemStack assemble(CraftingInput input){
  List<ItemStack> modifiers=new ArrayList<>(); ItemStack necklace=null;
  for(int i=0;i<input.size();i++){ItemStack stack=input.getItem(i); if(!stack.isEmpty()){if(stack.getItem() instanceof SeaNecklaceItem){if(necklace!=null)return ItemStack.EMPTY; necklace=stack.copy();}else{if(!(stack.getItem() instanceof NecklaceModifierItem))return ItemStack.EMPTY; modifiers.add(stack);}}}
  if(necklace!=null&&!modifiers.isEmpty()){SeaNecklaceItem.addModifiers(necklace,modifiers);return necklace;} return ItemStack.EMPTY;
 }
 public RecipeSerializer<? extends CustomRecipe> getSerializer(){return SERIALIZER;}
}
