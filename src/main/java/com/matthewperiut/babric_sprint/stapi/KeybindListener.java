package com.matthewperiut.babric_sprint.stapi;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.client.option.KeyBinding;
import net.modificationstation.stationapi.api.client.event.option.KeyBindingRegisterEvent;
import org.lwjgl.input.Keyboard;

public class KeybindListener {
    public static KeyBinding sprintKeybind;

    @EventListener
    public void registerKeybinds(KeyBindingRegisterEvent event) {
        // Initializing the keybind, the first argument is the translation key and the second is the default keycode
        sprintKeybind = new KeyBinding("key.babric_sprint.sprint_key", Keyboard.KEY_LCONTROL);

        // Adding the keybind to the keybindings list
        event.keyBindings.add(sprintKeybind);
    }
}
