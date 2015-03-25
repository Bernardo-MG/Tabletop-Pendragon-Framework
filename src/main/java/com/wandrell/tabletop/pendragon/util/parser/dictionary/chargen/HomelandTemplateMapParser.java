package com.wandrell.tabletop.pendragon.util.parser.dictionary.chargen;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.model.chargen.region.HomelandTemplate;
import com.wandrell.tabletop.valuebox.SkillBox;

public final class HomelandTemplateMapParser implements
        Parser<HomelandTemplate, Map<String, Object>> {

    public HomelandTemplateMapParser() {
        super();
    }

    @Override
    public final Map<String, Object> parse(final HomelandTemplate homeland) {
        final Map<String, Object> data;

        data = new LinkedHashMap<String, Object>();

        data.put("name", homeland.getName());
        if (homeland.getRegion() != null) {
            data.put("region", homeland.getRegion().getName());
        }
        data.put("bonus", getBonus(homeland));

        return data;
    }

    private final Map<String, Object> getBonus(final HomelandTemplate homeland) {
        final Map<String, Object> bonus;
        Collection<Map<String, Object>> values;
        Map<String, Object> valuesMap;
        bonus = new LinkedHashMap<String, Object>();

        // Skills
        if (!homeland.getSkills().isEmpty()) {
            values = new LinkedList<>();
            for (final SkillBox box : homeland.getSkills()) {
                valuesMap = new LinkedHashMap<String, Object>();
                valuesMap.put("name", box.getName());
                valuesMap.put("descriptor", box.getDescriptor());
                valuesMap.put("value", box.getValue());

                values.add(valuesMap);
            }
            bonus.put("skills", values);
        }

        // Specialty Skills
        if (!homeland.getSpecialtySkills().isEmpty()) {
            values = new LinkedList<>();
            for (final SkillBox box : homeland.getSpecialtySkills()) {
                valuesMap = new LinkedHashMap<String, Object>();
                valuesMap.put("name", box.getName());
                valuesMap.put("value", box.getValue());

                values.add(valuesMap);
            }
            bonus.put("specialty_skills", values);
        }

        // Passions
        if (!homeland.getPassions().isEmpty()) {
            values = new LinkedList<>();
            for (final SkillBox passion : homeland.getPassions()) {
                valuesMap = new LinkedHashMap<String, Object>();
                valuesMap.put("name", passion.getName());
                valuesMap.put("descriptor", passion.getDescriptor());

                values.add(valuesMap);
            }
            bonus.put("passions", values);
        }

        // Directed Traits
        if (!homeland.getDirectedTraits().isEmpty()) {
            values = new LinkedList<>();
            for (final SkillBox passion : homeland.getDirectedTraits()) {
                valuesMap = new LinkedHashMap<String, Object>();
                valuesMap.put("name", passion.getName());
                valuesMap.put("descriptor", passion.getDescriptor());

                values.add(valuesMap);
            }
            bonus.put("directed_traits", values);
        }

        return bonus;
    }

}
