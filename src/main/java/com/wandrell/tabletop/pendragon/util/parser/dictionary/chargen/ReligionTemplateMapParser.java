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
    public final Map<String, Object> parse(final ReligionTemplate value) {
        final Map<String, Object> data;
        final Map<String, Object> bonus;
        final Collection<Map<String, Object>> derived;
        Map<String, Object> dataMap;

        data = new LinkedHashMap<String, Object>();
        bonus = new LinkedHashMap<String, Object>();
        derived = new LinkedList<>();

        for (final Entry<String, Integer> entry : value
                .getDerivedAttributeBonus().entrySet()) {
            dataMap = new LinkedHashMap<String, Object>();
            dataMap.put("name", entry.getKey());
            dataMap.put("value", entry.getValue());

            derived.add(dataMap);
        }

        bonus.put("armor_bonus", value.getArmorBonus());
        bonus.put("damage_bonus", value.getDamageBonus());
        bonus.put("damage_dice_bonus", value.getDamageDiceBonus());
        bonus.put("derived_attributes", derived);

        data.put("name", value.getName());
        if (!value.getReligiousTraits().isEmpty()) {
            data.put("traits", value.getReligiousTraits());
        }
        if (!bonus.isEmpty()) {
            data.put("bonus", bonus);
        }

        return data;
    }

}
