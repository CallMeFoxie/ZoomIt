package cz.ondraster.zoomit;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

@Mod(modid = ZoomIt.MODID, version = ZoomIt.VERSION)
public class ZoomIt {
   public static final String MODID = "zoomit";
   public static final String VERSION = "1.1";

   public static double zoomMultiplier = 2.0d;

   public static KeyBinding keyBinding;

   @EventHandler
   public void preinit(FMLPreInitializationEvent event) {
      keyBinding = new KeyBinding("key.zoom", Keyboard.KEY_F, "key.categories.gameplay");
   }

   @EventHandler
   public void init(FMLInitializationEvent event) {
      FMLCommonHandler.instance().bus().register(new KeyInputHandler());
   }

   @EventHandler
   public void postInit(FMLPostInitializationEvent event) {
      KeyBinding[] bindings = new KeyBinding[Minecraft.getMinecraft().gameSettings.keyBindings.length];
      System.arraycopy(Minecraft.getMinecraft().gameSettings.keyBindings, 0, bindings, 0, Minecraft.getMinecraft().gameSettings.keyBindings.length);
      bindings[Minecraft.getMinecraft().gameSettings.keyBindings.length - 1] = keyBinding;
      Minecraft.getMinecraft().gameSettings.keyBindings = bindings;
   }
}
