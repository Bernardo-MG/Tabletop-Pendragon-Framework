package com.wandrell.tabletop.pendragon.util.outputter.chargen;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import com.wandrell.tabletop.pendragon.model.chargen.HomelandTemplate;
import com.wandrell.tabletop.pendragon.util.outputter.YAMLOutputter;
import com.wandrell.tabletop.skill.NameAndDescriptor;

public final class HomelandTemplateYAMLOutputter extends
        YAMLOutputter<HomelandTemplate> {

    public HomelandTemplateYAMLOutputter() {
        super();
    }

    @Override
    protected final Map<String, Object> buildMap(final HomelandTemplate value) {
        final Map<String, Object> data;
        final Map<String, Object> bonus;
        Collection<Map<String, Object>> values;
        Map<String, Object> valuesMap;

        data = new LinkedHashMap<String, Object>();
        bonus = new LinkedHashMap<String, Object>();

        values = new LinkedList<>();
        for (final Entry<String, Integer> entry : value.getTraits().entrySet()) {
            valuesMap = new LinkedHashMap<String, Object>();
            valuesMap.put("name", entry.getKey());
            valuesMap.put("value", entry.getValue());

            values.add(valuesMap);
        }
        if (!values.isEmpty()) {
            bonus.put("traits", values);
        }

        values = new LinkedList<>();
        for (final Entry<NameAndDescriptor, Integer> entry : value.getSkills()
                .entrySet()) {
            valuesMap = new LinkedHashMap<String, Object>();
            valuesMap.put("name", entry.getKey().getName());
            valuesMap.put("descriptor", entry.getKey().getDescriptor());
            valuesMap.put("value", entry.getValue());

            values.add(valuesMap);
        }
        if (!values.isEmpty()) {
            bonus.put("skills", values);
        }

        values = new LinkedList<>();
        for (final Entry<String, Integer> entry : value.getSpecialtySkills()
                .entrySet()) {
            valuesMap = new LinkedHashMap<String, Object>();
            valuesMap.put("name", entry.getKey());
            valuesMap.put("value", entry.getValue());

            values.add(valuesMap);
        }
        if (!values.isEmpty()) {
            bonus.put("specialty_skills", values);
        }

        values = new LinkedList<>();
        for (final NameAndDescriptor passion : value.getPassions()) {
            valuesMap = new LinkedHashMap<String, Object>();
            valuesMap.put("name", passion.getName());
            valuesMap.put("descriptor", passion.getDescriptor());

            values.add(valuesMap);
        }
        if (!values.isEmpty()) {
            bonus.put("passions", values);
        }

        values = new LinkedList<>();
        for (final NameAndDescriptor passion : value.getDirectedTraits()) {
            valuesMap = new LinkedHashMap<String, Object>();
            valuesMap.put("name", passion.getName());
            valuesMap.put("descriptor", passion.getDescriptor());

            values.add(valuesMap);
        }
        if (!values.isEmpty()) {
            bonus.put("directed_traits", values);
        }

        data.put("name", value.getName());
        if (!bonus.isEmpty()) {
            data.put("bonus", bonus);
        }

        return data;
    }

}
