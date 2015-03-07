package com.wandrell.tabletop.pendragon.util.parser.dictionary.chargen;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.interval.Interval;
import com.wandrell.tabletop.pendragon.model.chargen.FamilyCharacteristicTable;
import com.wandrell.tabletop.pendragon.model.chargen.FamilyCharacteristicTemplate;
import com.wandrell.tabletop.skill.SkillName;

public final class FamilyCharacteristicTableMapParser implements
        Parser<FamilyCharacteristicTable, Map<String, Object>> {

    public FamilyCharacteristicTableMapParser() {
        super();
    }

    @Override
    public final Map<String, Object>
            parse(final FamilyCharacteristicTable table) {
        final Map<String, Object> data;

        data = new LinkedHashMap<String, Object>();

        data.put("name", table.getName());
        data.put("intervals", getIntervals(table));

        return data;
    }

    private final Map<String, Object> getFamilyCharacteristicMap(
            final FamilyCharacteristicTemplate characteristic) {
        final Map<String, Object> data;
        final Map<String, Object> bonus;
        Collection<Map<String, Object>> values;
        Map<String, Object> value;

        data = new LinkedHashMap<String, Object>();
        bonus = new LinkedHashMap<String, Object>();

        // Name
        data.put("name", characteristic.getName());

        // Skills
        if (!characteristic.getSkills().isEmpty()) {
            values = new LinkedList<>();

            for (final Entry<SkillName, Integer> entry : characteristic
                    .getSkills().entrySet()) {
                value = new LinkedHashMap<String, Object>();

                value.put("name", entry.getKey().getName());
                value.put("descriptor", entry.getKey().getDescriptor());
                value.put("value", entry.getValue());

                values.add(value);
            }

            bonus.put("skills", values);
        }

        // Attributes
        if (!characteristic.getAttributes().isEmpty()) {
            values = new LinkedList<>();

            for (final Entry<String, Integer> entry : characteristic
                    .getAttributes().entrySet()) {
                value = new LinkedHashMap<String, Object>();

                value.put("name", entry.getKey());
                value.put("value", entry.getValue());

                values.add(value);
            }

            bonus.put("attributes", values);
        }

        // Bonus map
        if (!bonus.isEmpty()) {
            data.put("bonus", bonus);
        }

        return data;
    }

    private final Collection<Map<String, Object>> getIntervals(
            final FamilyCharacteristicTable table) {
        final Collection<Map<String, Object>> intervals;
        Map<String, Object> interval;

        intervals = new LinkedList<>();

        for (final Entry<Interval, FamilyCharacteristicTemplate> entry : table
                .getIntervals().entrySet()) {
            interval = new LinkedHashMap<String, Object>();

            interval.put("lower_limit", entry.getKey().getLowerLimit());
            interval.put("family_characteristic",
                    getFamilyCharacteristicMap(entry.getValue()));

            intervals.add(interval);
        }

        return intervals;
    }

}
