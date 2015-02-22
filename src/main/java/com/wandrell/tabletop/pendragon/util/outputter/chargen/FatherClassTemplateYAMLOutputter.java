package com.wandrell.tabletop.pendragon.util.outputter.chargen;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import com.wandrell.tabletop.pendragon.model.chargen.FatherClassTemplate;
import com.wandrell.tabletop.pendragon.util.outputter.YAMLOutputter;
import com.wandrell.tabletop.skill.NameAndDescriptor;

public final class FatherClassTemplateYAMLOutputter extends
        YAMLOutputter<FatherClassTemplate> {

    public FatherClassTemplateYAMLOutputter() {
        super();
    }

    @Override
    protected final Map<String, Object>
            buildMap(final FatherClassTemplate value) {
        final Map<String, Object> data;
        final Collection<Map<String, Object>> skills;
        final Collection<Map<String, Object>> directed;
        final Collection<Map<String, Object>> directedBase;
        final Collection<Map<String, Object>> specialty;
        final Map<String, Object> bonus;
        final Map<String, Object> base;
        Map<String, Object> map;

        data = new LinkedHashMap<String, Object>();
        bonus = new LinkedHashMap<String, Object>();
        base = new LinkedHashMap<String, Object>();
        skills = new LinkedList<>();
        specialty = new LinkedList<>();
        directed = new LinkedList<>();
        directedBase = new LinkedList<>();

        for (final NameAndDescriptor skill : value.getSkillsGroup()) {
            map = new LinkedHashMap<String, Object>();
            map.put("name", skill.getName());
            map.put("descriptor", skill.getDescriptor());

            skills.add(map);
        }

        for (final Entry<NameAndDescriptor, Integer> entry : value
                .getDirectedTraits().entrySet()) {
            map = new LinkedHashMap<String, Object>();

            map.put("name", entry.getKey().getName());
            map.put("descriptor", entry.getKey().getDescriptor());
            map.put("value", entry.getValue());

            directed.add(map);
        }
        bonus.put("directed_traits", directed);

        for (final Entry<String, Integer> entry : value.getSpecialtySkills()
                .entrySet()) {
            map = new LinkedHashMap<String, Object>();

            map.put("name", entry.getKey());
            map.put("value", entry.getValue());

            specialty.add(map);
        }
        bonus.put("specialty_skills", specialty);

        for (final Entry<NameAndDescriptor, Integer> entry : value
                .getDirectedTraitsBase().entrySet()) {
            map = new LinkedHashMap<String, Object>();

            map.put("name", entry.getKey().getName());
            map.put("descriptor", entry.getKey().getDescriptor());
            map.put("value", entry.getValue());

            directedBase.add(map);
        }
        base.put("directed_traits", directedBase);

        data.put("name", value.getName());

        data.put("skills_points", value.getSkillsPoints());
        data.put("skills_points_non_combat", value.getNonCombatSkillBonus());
        data.put("skills_points_group_bonus", value.getSkillsGroupBonusPoints());
        data.put("skills_points_group_divide",
                value.getSkillsGroupDividePoints());

        data.put("money", value.getMoney().getTextValue());

        data.put("skills_group", skills);
        data.put("bonus", bonus);
        data.put("base", base);

        return data;
    }

}
