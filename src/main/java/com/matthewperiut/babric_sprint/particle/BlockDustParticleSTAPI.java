package com.matthewperiut.babric_sprint.particle;

import net.minecraft.block.Block;
import net.minecraft.client.render.Tessellator;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.client.StationRenderAPI;
import net.modificationstation.stationapi.api.client.texture.Sprite;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlases;
import net.modificationstation.stationapi.mixin.arsenic.client.ParticleAccessor;

public class BlockDustParticleSTAPI extends BlockDustParticle {
    private Sprite texture;

    public BlockDustParticleSTAPI(World arg, double d, double e, double f, double g, double h, double i, Block arg2, int j, int k) {
        super(arg, d, e, f, g, h, i, arg2, j, k);

        texture = StationRenderAPI.getBakedModelManager().getAtlas(Atlases.GAME_ATLAS_TEXTURE).getSprite(arg2.getAtlas().getTexture(field_2635).getId());
    }

    @Override
    public void method_2002(Tessellator tessellator, float delta, float yawX, float pitchX, float yawY, float pitchY1, float pitchY2) {
        float
                startU = texture.getMinU() + (field_2636 / 4) * (texture.getMaxU() - texture.getMinU()),
                endU = startU + 0.24975F * (texture.getMaxU() - texture.getMinU()),
                startV = texture.getMinV() + (field_2637 / 4) * (texture.getMaxV() - texture.getMinV()),
                endV = startV + 0.24975F * (texture.getMaxV() - texture.getMinV()),
                randomMultiplier = 0.1F * ((ParticleAccessor) this).getScale(),
                renderX = (float) (prevX + (x - prevX) * (double) delta - field_2645),
                renderY = (float) (prevY + (y - prevY) * (double) delta - field_2646),
                renderZ = (float) (prevZ + (z - prevZ) * (double) delta - field_2647),
                brightness = getBrightnessAtEyes(delta);
        tessellator.color(brightness * ((ParticleAccessor) this).getRed(), brightness * ((ParticleAccessor) this).getGreen(), brightness * ((ParticleAccessor) this).getBlue());
        tessellator.vertex(renderX - yawX * randomMultiplier - pitchY1 * randomMultiplier, renderY - pitchX * randomMultiplier, renderZ - yawY * randomMultiplier - pitchY2 * randomMultiplier, startU, endV);
        tessellator.vertex(renderX - yawX * randomMultiplier + pitchY1 * randomMultiplier, renderY + pitchX * randomMultiplier, renderZ - yawY * randomMultiplier + pitchY2 * randomMultiplier, startU, startV);
        tessellator.vertex(renderX + yawX * randomMultiplier + pitchY1 * randomMultiplier, renderY + pitchX * randomMultiplier, renderZ + yawY * randomMultiplier + pitchY2 * randomMultiplier, endU, startV);
        tessellator.vertex(renderX + yawX * randomMultiplier - pitchY1 * randomMultiplier, renderY - pitchX * randomMultiplier, renderZ + yawY * randomMultiplier - pitchY2 * randomMultiplier, endU, endV);
    }
}
