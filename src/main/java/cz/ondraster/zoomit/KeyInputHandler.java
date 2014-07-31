package cz.ondraster.zoomit;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class KeyInputHandler {
   @SubscribeEvent
   public void onPreRender(TickEvent.RenderTickEvent event) {
      int key = ReflectionHelper.getPrivateValue(KeyBinding.class, ZoomIt.keyBinding, "keyCode");
      if (Keyboard.isKeyDown(key)) {
         ReflectionHelper.setPrivateValue(EntityRenderer.class, Minecraft.getMinecraft().entityRenderer, ZoomIt.zoomMultiplier, "cameraZoom");
         Minecraft.getMinecraft().gameSettings.smoothCamera = true;
      } else {
         ReflectionHelper.setPrivateValue(EntityRenderer.class, Minecraft.getMinecraft().entityRenderer, 1.0d, "cameraZoom");
         Minecraft.getMinecraft().gameSettings.smoothCamera = false;
      }
   }
}
