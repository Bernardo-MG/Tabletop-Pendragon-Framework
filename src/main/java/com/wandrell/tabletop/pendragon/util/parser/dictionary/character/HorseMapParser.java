package com.wandrell.tabletop.pendragon.util.parser.dictionary.character;

import java.util.LinkedHashMap;
import java.util.Map;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.model.character.Horse;

public final class HorseMapParser implements Parser<Horse, Map<String, Object>> {

    public HorseMapParser() {
        super();
    }

    @Override
    public final Map<String, Object> parse(final Horse horse) {
        final Map<String, Object> data;
        final Map<String, Boolean> flags;

        data = new LinkedHashMap<String, Object>();

        // Horse specific info
        data.put("type", horse.getHorseType());
        data.put("natural_armor", horse.getNaturalArmor());

        // Attributes
        data.put("attributes", getAttributes(horse));

        // Derived attributes
        data.put("derived_attributes", getDerivedAttributes(horse));

        // Flags
        flags = getFlags(horse);
        if (!flags.isEmpty()) {
            data.put("flags", flags);
        }

        return data;
    }

    private final Map<String, Integer> getAttributes(final Horse horse) {
        final Map<String, Integer> attributes;

        attributes = new LinkedHashMap<String, Integer>();

        attributes.put("constitution", horse.getConstitution());
        attributes.put("dexterity", horse.getDexterity());
        attributes.put("size", horse.getSize());
        attributes.put("strength", horse.getStrength());

        return attributes;
    }

    private final Map<String, Integer> getDerivedAttributes(final Horse horse) {
        final Map<String, Integer> derived;

        derived = new LinkedHashMap<String, Integer>();

        derived.put("damage", horse.getDamage());
        derived.put("movement_rate", horse.getMovementRate());

        return derived;
    }

    private final Map<String, Boolean> getFlags(final Horse horse) {
        final Map<String, Boolean> flags;

        flags = new LinkedHashMap<String, Boolean>();

        if (horse.isArmored()) {
            flags.put("armored", horse.isArmored());
        }
        if (horse.isCombatHorse()) {
            flags.put("combat", horse.isCombatHorse());
        }
        if (horse.isHuntingHorse()) {
            flags.put("hunting", horse.isHuntingHorse());
        }

        return flags;
    }

}
