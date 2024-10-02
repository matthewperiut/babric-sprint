package com.matthewperiut.babric_sprint.mixin;

import com.matthewperiut.babric_sprint.api.EntitySprinting;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

import static com.matthewperiut.babric_sprint.SprintingConstants.SPRINT_FLAG;

@Mixin(Entity.class)
public abstract class EntityMixin implements EntitySprinting {
    @Shadow
    protected abstract void setFlag(int index, boolean value);

    @Shadow
    protected abstract boolean getFlag(int index);

    @Shadow
    public World world;

    @Shadow
    @Final
    public Box boundingBox;

    @Shadow
    public double x;

    @Shadow
    public double y;


    @Shadow
    public float standingEyeHeight;

    @Shadow
    public double z;

    @Shadow
    public float width;

    @Shadow
    protected Random random;

    @Shadow
    public double velocityX;

    @Shadow
    public double velocityZ;

    @Override
    public void setSprinting(boolean sprinting) {
        setFlag(SPRINT_FLAG, sprinting);
    }

    @Override
    public boolean isSprinting() {
        return getFlag(SPRINT_FLAG);
    }

    @Inject(method = "baseTick", at = @At("HEAD"))
    public void addParticles(CallbackInfo ci) {
        int n4;
        int n3;
        int n2;
        int n;
        if (this.isSprinting() && !isTouchingWater() && (n4 = this.world.getBlockId(n3 = MathHelper.floor(this.x), n2 = MathHelper.floor(this.y - (double) 0.2f - (double) this.standingEyeHeight), n = MathHelper.floor(this.z))) > 0) {
            this.world.addParticle("tilecrack_" + n4, this.x + ((double) this.random.nextFloat() - 0.5) * (double) this.width, this.boundingBox.minY + 0.1, this.z + ((double) this.random.nextFloat() - 0.5) * (double) this.width, -this.velocityX * 4.0, 1.5, -this.velocityZ * 4.0);
        }

    }

    @Unique
    public boolean isTouchingWater() {
        return world.isMaterialInBox(this.boundingBox.expand(-0.10000000149011612, -0.4000000059604645, -0.10000000149011612), Material.WATER);
    }
}
