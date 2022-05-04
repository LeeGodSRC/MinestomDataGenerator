package net.minestom.generators;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.SoundType;
import net.minestom.datagen.DataGenerator;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class SoundTypeGenerator extends DataGenerator {

    private static final BiMap<String, SoundType> INDEXES = HashBiMap.create();

    public static String getNameBySoundType(SoundType soundType) {
        return INDEXES.inverse().getOrDefault(soundType, "STONE");
    }

    static {
        for (Field field : SoundType.class.getDeclaredFields()) {
            if (field.getType() == SoundType.class && Modifier.isStatic(field.getModifiers())) {
                field.setAccessible(true);
                try {
                    final SoundType soundType = (SoundType) field.get(null);
                    INDEXES.put(field.getName(), soundType);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public JsonElement generate() throws Exception {
        JsonObject global = new JsonObject();
        INDEXES.forEach((name, soundType) -> {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("volume", soundType.getVolume());
            jsonObject.addProperty("pitch", soundType.getPitch());
            jsonObject.addProperty("breakSound", Registry.SOUND_EVENT.getKey(soundType.getBreakSound()).toString());
            jsonObject.addProperty("stepSound", Registry.SOUND_EVENT.getKey(soundType.getStepSound()).toString());
            jsonObject.addProperty("placeSound", Registry.SOUND_EVENT.getKey(soundType.getPlaceSound()).toString());
            jsonObject.addProperty("hitSound", Registry.SOUND_EVENT.getKey(soundType.getHitSound()).toString());
            jsonObject.addProperty("fallSound", Registry.SOUND_EVENT.getKey(soundType.getFallSound()).toString());
            global.add(name, jsonObject);
        });
        return global;
    }
}
