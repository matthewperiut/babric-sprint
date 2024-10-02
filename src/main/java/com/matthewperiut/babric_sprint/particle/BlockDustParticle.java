package com.matthewperiut.babric_sprint.particle;

import net.minecraft.block.Block;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.render.Tessellator;
import net.minecraft.world.World;

public class BlockDustParticle
        extends Particle {
    private Block field_1752;

    public BlockDustParticle(World arg, double d, double e, double f, double g, double h, double i, Block arg2, int j, int k) {
        super(arg, d, e, f, g, h, i);
        this.field_1752 = arg2;
        //this.setMiscTexture(arg2.method_396(0, k));
        // set misc texture as 0
        // textureId
        this.field_2635 = arg2.getTexture(0, k);
        // note: should reference a variable in block for particle's gravity, but in newer versions not used.
        // gravityStrength
        this.field_2641 = 1.0F;
        this.blue = 0.6f;
        this.green = 0.6f;
        this.red = 0.6f;
        this.scale /= 2.0f;
    }


    public BlockDustParticle method_1301(int i, int j, int k) {
        if (this.field_1752 == Block.GRASS_BLOCK) {
            return this;
        }
        int n = this.field_1752.getColorMultiplier(this.world, i, j, k);
        this.red *= (float) (n >> 16 & 0xFF) / 255.0f;
        this.green *= (float) (n >> 8 & 0xFF) / 255.0f;
        this.blue *= (float) (n & 0xFF) / 255.0f;
        return this;
    }

    // getLayer
    @Override
    public int method_2003() {
        return 1;
    }

    @Override
    public void method_2002(Tessellator arg, float f, float g, float h, float i, float j, float k) {
        float f2 = ((float) (this.field_2635 % 16) + this.field_2636 / 4.0f) / 16.0f;
        float f3 = f2 + 0.015609375f;
        float f4 = ((float) (this.field_2635 / 16) + this.field_2637 / 4.0f) / 16.0f;
        float f5 = f4 + 0.015609375f;
        float f6 = 0.1f * this.scale;
        float f7 = (float) (this.prevX + (this.x - this.prevX) * (double) f - field_2645);
        float f8 = (float) (this.prevY + (this.y - this.prevY) * (double) f - field_2646);
        float f9 = (float) (this.prevZ + (this.z - this.prevZ) * (double) f - field_2647);
        float f10 = 1.0f;
        arg.color(f10 * this.red, f10 * this.green, f10 * this.blue);
        arg.vertex(f7 - g * f6 - j * f6, f8 - h * f6, f9 - i * f6 - k * f6, f2, f5);
        arg.vertex(f7 - g * f6 + j * f6, f8 + h * f6, f9 - i * f6 + k * f6, f2, f4);
        arg.vertex(f7 + g * f6 + j * f6, f8 + h * f6, f9 + i * f6 + k * f6, f3, f4);
        arg.vertex(f7 + g * f6 - j * f6, f8 - h * f6, f9 + i * f6 - k * f6, f3, f5);
    }
}