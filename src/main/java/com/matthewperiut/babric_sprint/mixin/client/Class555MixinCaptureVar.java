package com.matthewperiut.babric_sprint.mixin.client;

import com.matthewperiut.babric_sprint.api.EntitySprinting;
import net.minecraft.class_555;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.matthewperiut.babric_sprint.BabricSprint.lastMovementFovMultiplier;
import static com.matthewperiut.babric_sprint.BabricSprint.movementFovMultiplier;

@Mixin(class_555.class)
public class Class555MixinCaptureVar {
    @Shadow
    private Minecraft field_2349;
    private float field_1831; // SOMETHING MOVEMENT IDK

    @Inject(method = "method_1837", at = @At("HEAD"))
    void tick(CallbackInfo ci) {
        updateMovementFovMultiplier();
    }

    public float method_1305(PlayerEntity p) {
        if (((EntitySprinting) (Object) p).isSprinting()) {
            return 1.15f;
        }
        return 1.f;
    }

    @Unique
    private void updateMovementFovMultiplier() {
        ClientPlayerEntity class_5182 = (ClientPlayerEntity) field_2349.field_2807;
        this.field_1831 = method_1305(class_5182);
        lastMovementFovMultiplier = movementFovMultiplier;
        movementFovMultiplier += (field_1831 - movementFovMultiplier) * 0.5f;
    }
}
