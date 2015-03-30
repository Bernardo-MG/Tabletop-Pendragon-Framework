package com.wandrell.tabletop.pendragon.util.parser.dictionary.chargen;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.interval.Interval;
import com.wandrell.tabletop.pendragon.model.chargen.background.FamilyCharacteristicTable;
import com.wandrell.tabletop.pendragon.model.chargen.background.FamilyCharacteristicTemplate;
import com.wandrell.tabletop.valuebox.SkillBox;

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

            for (final SkillBox box : characteristic.getSkills()) {
                value = new LinkedHashMap<String, Object>();

                value.put("name", box.getName());
                value.put("descriptor", box.getDescriptor());
                value.put("value", box.getValue());

                values.add(value);
            }

            bonus.put("skills", values);
        }

        // Attributes
        values = new LinkedList<>();
        if (characteristic.getAttributes().getAppearance() > 0) {
            value = new LinkedHashMap<String, Object>();

            value.put("name", "appearance");
            value.put("value", characteristic.getAttributes().getAppearance());

            values.add(value);
        }
        if (characteristic.getAttributes().getConstitution() > 0) {
            value = new LinkedHashMap<String, Object>();

            value.put("name", "constitution");
            value.put("value", characteristic.getAttributes().getConstitution());

            values.add(value);
        }
        if (characteristic.getAttributes().getDexterity() > 0) {
            value = new LinkedHashMap<String, Object>();

            value.put("name", "dexterity");
            value.put("value", characteristic.getAttributes().getDexterity());

            values.add(value);
        }
        if (characteristic.getAttributes().getSize() > 0) {
            value = new LinkedHashMap<String, Object>();

            value.put("name", "size");
            value.put("value", characteristic.getAttributes().getSize());

            values.add(value);
        }
        if (characteristic.getAttributes().getStrength() > 0) {
            value = new LinkedHashMap<String, Object>();

            value.put("name", "strength");
            value.put("value", characteristic.getAttributes().getStrength());

            values.add(value);
        }

        bonus.put("attributes", values);

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
