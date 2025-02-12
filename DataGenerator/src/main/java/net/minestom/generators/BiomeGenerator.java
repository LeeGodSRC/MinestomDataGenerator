package net.minestom.generators;

import com.google.gson.JsonObject;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.world.level.biome.Biome;
import net.minestom.datagen.DataGenerator;

import java.lang.reflect.Field;

public final class BiomeGenerator extends DataGenerator {

    @Override
    public JsonObject generate() throws Exception {
        Field biomeCategory = Biome.class.getDeclaredField("biomeCategory");
        biomeCategory.setAccessible(true);

        JsonObject biomes = new JsonObject();
        for (var biome : BuiltinRegistries.BIOME) {
            final var location = BuiltinRegistries.BIOME.getKey(biome);
            JsonObject biomeJson = new JsonObject();
            biomeJson.addProperty("humid", biome.isHumid());
//            biomeJson.addProperty("scale", biome.dep);
//            biomeJson.addProperty("depth", biome.getDepth());
            biomeJson.addProperty("temperature", biome.getBaseTemperature());
            biomeJson.addProperty("downfall", biome.getDownfall());
            biomeJson.addProperty("precipitation", biome.getPrecipitation().getSerializedName());
            biomeJson.addProperty("category", ((Biome.BiomeCategory) biomeCategory.get(biome)).getSerializedName());
            // Colors
            biomeJson.addProperty("fogColor", biome.getFogColor());
            biomeJson.addProperty("waterColor", biome.getWaterColor());
            biomeJson.addProperty("waterFogColor", biome.getWaterFogColor());
            biomeJson.addProperty("skyColor", biome.getSkyColor());
            biomeJson.addProperty("foliageColor", biome.getFoliageColor());
            biomeJson.addProperty("foliageColorOverride", biome.getSpecialEffects().getFoliageColorOverride().orElse(null));
            biomeJson.addProperty("grassColorOverride", biome.getSpecialEffects().getGrassColorOverride().orElse(null));
            biomeJson.addProperty("grassColorModifier", biome.getSpecialEffects().getGrassColorModifier().getSerializedName());
            biomes.add(location.toString(), biomeJson);
        }
        return biomes;
    }
}
