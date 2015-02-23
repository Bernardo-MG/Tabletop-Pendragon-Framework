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
import com.wandrell.tabletop.skill.NameAndDescriptor;

public final class FamilyCharacteristicTableMapParser implements
        Parser<FamilyCharacteristicTable, Map<String, Object>> {

    public FamilyCharacteristicTableMapParser() {
        super();
    }

    @Override
    public final Map<String, Object>
            parse(final FamilyCharacteristicTable value) {
        final Map<String, Object> data;
        final Collection<Map<String, Object>> intervals;
        Map<String, Object> interval;

        data = new LinkedHashMap<String, Object>();
        intervals = new LinkedList<>();

        data.put("name", value.getName());
        data.put("intervals", intervals);

        for (final Entry<Interval, FamilyCharacteristicTemplate> entry : value
                .getIntervals().entrySet()) {
            interval = new LinkedHashMap<String, Object>();

            interval.put("lower_limit", entry.getKey().getLowerLimit());
            interval.put("family_characteristic",
                    getFamilyCharacteristicMap(entry.getValue()));

            intervals.add(interval);
        }

        return data;
    }

    private final Map<String, Object> getFamilyCharacteristicMap(
            final FamilyCharacteristicTemplate characteristic) {
        final Map<String, Object> data;
        final Map<String, Object> bonus;
        final Collection<Map<String, Object>> skills;
        final Collection<Map<String, Object>> attributes;
        Map<String, Object> value;

        data = new LinkedHashMap<String, Object>();
        bonus = new LinkedHashMap<String, Object>();
        skills = new LinkedList<Map<String, Object>>();
        attributes = new LinkedList<Map<String, Object>>();

        data.put("name", characteristic.getName());

        for (final Entry<NameAndDescriptor, Integer> entry : characteristic
                .getSkills().entrySet()) {
            value = new LinkedHashMap<String, Object>();

            value.put("name", entry.getKey().getName());
            value.put("descriptor", entry.getKey().getDescriptor());
            value.put("value", entry.getValue());

            skills.add(value);
        }

        for (final Entry<String, Integer> entry : characteristic
                .getAttributes().entrySet()) {
            value = new LinkedHashMap<String, Object>();

            value.put("name", entry.getKey());
            value.put("value", entry.getValue());

            attributes.add(value);
        }

        if (!skills.isEmpty()) {
            bonus.put("skills", skills);
        }

        if (!attributes.isEmpty()) {
            bonus.put("attributes", attributes);
        }

        if (!bonus.isEmpty()) {
            data.put("bonus", bonus);
        }

        return data;
    }

}
