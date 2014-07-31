package cz.ondraster.zoomit;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class KeyInputHandler {
   boolean previousDoZoom = false;

   @SubscribeEvent
   public void onPreRender(TickEvent.RenderTickEvent event) {
      int key = ReflectionHelper.getPrivateValue(KeyBinding.class, ZoomIt.keyBinding, "keyCode", "field_74512_d");
      boolean doZoom = false;

      if (key < 0) {
         if (Mouse.isButtonDown(key + 100))
            doZoom = true;
      } else {
         if (Keyboard.isKeyDown(key))
            doZoom = true;
      }

      if (doZoom && !previousDoZoom) {
         ReflectionHelper.setPrivateValue(EntityRenderer.class, Minecraft.getMinecraft().entityRenderer, ZoomIt.zoomMultiplier, "cameraZoom", "field_78503_V");
         Minecraft.getMinecraft().gameSettings.smoothCamera = true;
      } else if (!doZoom && previousDoZoom) {
         ReflectionHelper.setPrivateValue(EntityRenderer.class, Minecraft.getMinecraft().entityRenderer, 1.0d, "cameraZoom", "field_78503_V");
         Minecraft.getMinecraft().gameSettings.smoothCamera = false;
      }

      previousDoZoom = doZoom;
   }
}
