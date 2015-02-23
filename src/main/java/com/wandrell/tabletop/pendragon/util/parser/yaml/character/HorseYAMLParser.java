package com.wandrell.tabletop.pendragon.util.parser.yaml.character;

import java.io.Reader;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.model.character.Horse;
import com.wandrell.tabletop.pendragon.service.ModelService;

public class HorseYAMLParser implements Parser<Reader, Horse> {

    private final ModelService modelService;

    public HorseYAMLParser(final ModelService service) {
        super();

        modelService = service;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final Horse parse(final Reader reader) {
        final Yaml yaml;
        final Map<String, Object> values;
        final String type;
        final Integer constitution;
        final Integer dexterity;
        final Integer size;
        final Integer strength;
        final Integer damage;
        final Integer movement;
        final Integer armor;
        final Boolean armored;
        final Boolean combat;
        final Boolean hunting;
        Map<String, Integer> mapValues;
        Map<String, Boolean> mapFlags;

        yaml = new Yaml();

        values = (Map<String, Object>) yaml.load(reader);

        type = (String) values.get("type");
        armor = (Integer) values.get("natural_armor");

        mapValues = (Map<String, Integer>) values.get("attributes");
        constitution = mapValues.get("constitution");
        dexterity = mapValues.get("dexterity");
        size = mapValues.get("size");
        strength = mapValues.get("strength");

        mapValues = (Map<String, Integer>) values.get("derived_attributes");
        damage = mapValues.get("damage");
        movement = mapValues.get("movement_rate");

        mapFlags = (Map<String, Boolean>) values.get("flags");
        armored = mapFlags.get("armored");
        combat = mapFlags.get("combat");
        hunting = mapFlags.get("hunting");

        return getModelService().getHorse(type, constitution, dexterity, size,
                strength, damage, movement, armor, armored, combat, hunting);
    }

    private final ModelService getModelService() {
        return modelService;
    }

}
