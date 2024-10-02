package com.matthewperiut.babric_sprint.mixin.client;

import net.minecraft.block.material.Material;
import net.minecraft.class_555;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.matthewperiut.babric_sprint.BabricSprint.lastMovementFovMultiplier;
import static com.matthewperiut.babric_sprint.BabricSprint.movementFovMultiplier;

@Mixin(value = class_555.class, priority = 900)
public abstract class Class555Mixin {
    @Shadow
    protected abstract float method_1848(float f);

    @Shadow
    private Minecraft field_2349;

    @Shadow
    private float field_2350;


    @Redirect(method = "method_1840", at = @At(value = "INVOKE", target = "Lnet/minecraft/class_555;method_1848(F)F"), require = 0)
    public float redirectToCustomFov(class_555 instance, float value) {
        return getFovMultiplier(value, false);
    }

    @Unique
    public float getFovMultiplier(float f, boolean isHand) {
        LivingEntity entity = this.field_2349.field_2807;
        float fov = 70F;

        if (isHand) {
            fov = 70F;
        }

        if (entity.isInFluid(Material.WATER)) {
            fov *= 60.0F / 70.0F;
        }

        if (entity.health <= 0) {
            float deathTimeFov = (float) entity.deathTime + f;
            fov /= (1.0F - 500F / (deathTimeFov + 500F)) * 2.0F + 1.0F;
        }

        if (!isHand) {
            fov *= lastMovementFovMultiplier + (movementFovMultiplier - lastMovementFovMultiplier) * f;
        }

        return fov;
    }

    @Inject(method = "method_1845", at = @At(value = "HEAD"))
    public void adjustHandFov(float f, int i, CallbackInfo ci) {
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GLU.gluPerspective(getFovMultiplier(f, true), (float) field_2349.displayWidth / (float) field_2349.displayHeight, 0.05F, field_2350 * 2.0F);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
    }
}
