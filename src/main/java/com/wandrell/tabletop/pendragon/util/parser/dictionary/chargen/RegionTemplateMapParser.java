package com.wandrell.tabletop.pendragon.util.parser.dictionary.chargen;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.model.chargen.region.RegionTemplate;

public final class RegionTemplateMapParser implements
        Parser<RegionTemplate, Map<String, Object>> {

    public RegionTemplateMapParser() {
        super();
    }

    @Override
    public final Map<String, Object> parse(final RegionTemplate region) {
        final Map<String, Object> data;

        data = new LinkedHashMap<String, Object>();

        data.put("name", region.getName());
        data.put("bonus", getBonus(region));

        return data;
    }

    private final Map<String, Object> getBonus(final RegionTemplate region) {
        final Map<String, Object> bonus;
        Collection<Map<String, Object>> values;
        Map<String, Object> valuesMap;
        bonus = new LinkedHashMap<String, Object>();

        // Traits
        if (!region.getTraits().isEmpty()) {
            values = new LinkedList<>();
            for (final Entry<String, Integer> entry : region.getTraits()
                    .entrySet()) {
                valuesMap = new LinkedHashMap<String, Object>();
                valuesMap.put("name", entry.getKey());
                valuesMap.put("value", entry.getValue());

                values.add(valuesMap);
            }
            bonus.put("traits", values);
        }

        return bonus;
    }

}
