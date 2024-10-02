package com.matthewperiut.babric_sprint.mixin.client;

import com.matthewperiut.babric_sprint.BabricSprint;
import com.matthewperiut.babric_sprint.stapi.KeybindHelperStapi;
import net.minecraft.class_41;
import net.minecraft.class_413;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.matthewperiut.babric_sprint.BabricSprint.stapi;

@Mixin(class_413.class)
public class Class413Mixin extends class_41 {
    @Shadow
    private boolean[] field_1661;

    @Inject(method = "method_1941", at = @At("TAIL"))
    void extraInput(int i, boolean bl, CallbackInfo ci) {
        if (stapi) {
            if (i == KeybindHelperStapi.getKeyCode()) {
                field_1661[6] = bl;

            }
        } else {
            if (i == BabricSprint.runKeyCode) {
                field_1661[6] = bl;
            }
        }
    }

    @Inject(method = "method_1942", at = @At("TAIL"))
    public void addRunKey(PlayerEntity par1, CallbackInfo ci) {
        field_2534 = field_1661[6];
    }
}
