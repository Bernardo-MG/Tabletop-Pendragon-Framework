package com.wandrell.tabletop.pendragon.util.parser.dictionary.chargen;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.model.chargen.ReligionTemplate;

public final class ReligionTemplateMapParser implements
        Parser<ReligionTemplate, Map<String, Object>> {

    public ReligionTemplateMapParser() {
        super();
    }

    @Override
    public final Map<String, Object> parse(final ReligionTemplate religion) {
        final Map<String, Object> data;

        data = new LinkedHashMap<String, Object>();

        data.put("name", religion.getName());
        if (!religion.getReligiousTraits().isEmpty()) {
            data.put("traits", religion.getReligiousTraits());
        }
        data.put("bonus", getBonus(religion));

        return data;
    }

    private final Map<String, Object> getBonus(final ReligionTemplate religion) {
        final Map<String, Object> bonus;

        bonus = new LinkedHashMap<String, Object>();

        bonus.put("armor_bonus", religion.getArmorBonus());
        bonus.put("damage_bonus", religion.getDamageBonus());
        bonus.put("damage_dice_bonus", religion.getDamageDiceBonus());
        bonus.put("derived_attributes", getDerivedAttributes(religion));

        return bonus;
    }

    private final Collection<Map<String, Object>> getDerivedAttributes(
            final ReligionTemplate religion) {
        final Collection<Map<String, Object>> derived;
        Map<String, Object> value;

        derived = new LinkedList<>();

        for (final Entry<String, Integer> entry : religion
                .getDerivedAttributeBonus().entrySet()) {
            value = new LinkedHashMap<String, Object>();
            value.put("name", entry.getKey());
            value.put("value", entry.getValue());

            derived.add(value);
        }

        return derived;
    }

}
