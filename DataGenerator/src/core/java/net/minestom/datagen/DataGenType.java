package net.minestom.datagen;

public enum DataGenType {
    ATTRIBUTES("attributes"),
    BIOMES("biomes"),
    BLOCK_ENTITIES("block_entities"),
    BLOCK_PROPERTIES("block_properties"),
    BLOCKS("blocks"),
    CUSTOM_STATISTICS("custom_statistics"),
    DIMENSION_TYPES("dimension_types"),
    ENCHANTMENTS("enchantments"),
    ENTITIES("entities"),
    FLUIDS("fluids"),
    MATERIALS("items"),
    MAP_COLORS("map_colors"),
    PARTICLES("particles"),
    MOB_EFFECTS("potion_effects"),
    POTIONS("potions"),
    SOUNDS("sounds"),
    VILLAGER_PROFESSIONS("villager_professions"),
    VILLAGER_TYPES("villager_types"),

    BLOCK_TAGS("tags/block_tags"),
    ENTITY_TYPE_TAGS("tags/entity_type_tags"),
    FLUID_TAGS("tags/fluid_tags"),
    ITEM_TAGS("tags/item_tags"),

    BLOCK_LOOT_TABLES("loot_tables/block_loot_tables"),
    CHEST_LOOT_TABLES("loot_tables/chest_loot_tables"),
    ENTITY_LOOT_TABLES("loot_tables/entity_loot_tables"),
    GAMEPLAY_LOOT_TABLES("loot_tables/gameplay_loot_tables");

    private final String fileName;

    DataGenType(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}