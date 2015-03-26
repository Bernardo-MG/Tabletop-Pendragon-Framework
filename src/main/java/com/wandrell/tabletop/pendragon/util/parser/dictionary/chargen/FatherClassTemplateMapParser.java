package com.wandrell.tabletop.pendragon.util.parser.dictionary.chargen;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.model.chargen.background.FatherClassTemplate;
import com.wandrell.tabletop.valuebox.SkillBox;

public final class FatherClassTemplateMapParser implements
        Parser<FatherClassTemplate, Map<String, Object>> {

    public FatherClassTemplateMapParser() {
        super();
    }

    @Override
    public final Map<String, Object> parse(final FatherClassTemplate father) {
        final Map<String, Object> data;
        final Collection<Map<String, Object>> skills;
        final String money;
        Map<String, Object> map;

        data = new LinkedHashMap<String, Object>();
        skills = new LinkedList<>();

        for (final SkillBox skill : father.getSkillsGroup()) {
            map = new LinkedHashMap<String, Object>();
            map.put("name", skill.getName());
            map.put("descriptor", skill.getDescriptor());

            skills.add(map);
        }

        data.put("name", father.getName());

        data.put("skills_points", father.getSkillsPoints());
        data.put("skills_points_non_combat", father.getNonCombatSkillBonus());
        data.put("skills_points_group_bonus",
                father.getSkillsGroupBonusPoints());
        data.put("skills_points_group_divide",
                father.getSkillsGroupDividePoints());

        money = father.getMoney().getTextValue();
        if (!money.isEmpty()) {
            data.put("money", father.getMoney().getTextValue());
        }

        data.put("skills_group", skills);
        data.put("bonus", getBonus(father));
        data.put("base", getBaseValues(father));

        return data;
    }

    private final Map<String, Object> getBaseValues(
            final FatherClassTemplate father) {
        final Map<String, Object> base;
        Collection<Map<String, Object>> values;
        Map<String, Object> value;

        base = new LinkedHashMap<String, Object>();

        if (!father.getDirectedTraitsBase().isEmpty()) {
            values = new LinkedList<>();
            for (final SkillBox box : father.getDirectedTraitsBase()) {
                value = new LinkedHashMap<String, Object>();

                value.put("name", box.getName());
                value.put("descriptor", box.getDescriptor());
                value.put("value", box.getValue());

                values.add(value);
            }
            base.put("directed_traits", values);
        }

        return base;
    }

    private final Map<String, Object>
            getBonus(final FatherClassTemplate father) {
        final Map<String, Object> bonus;
        Collection<Map<String, Object>> values;
        Map<String, Object> value;

        bonus = new LinkedHashMap<String, Object>();

        if (!father.getDirectedTraits().isEmpty()) {
            values = new LinkedList<>();
            for (final SkillBox box : father.getDirectedTraits()) {
                value = new LinkedHashMap<String, Object>();

                value.put("name", box.getName());
                value.put("descriptor", box.getDescriptor());
                value.put("value", box.getValue());

                values.add(value);

            }
            bonus.put("directed_traits", values);
        }

        if (!father.getSpecialtySkills().isEmpty()) {
            values = new LinkedList<>();
            for (final SkillBox box : father.getSpecialtySkills()) {
                value = new LinkedHashMap<String, Object>();

                value.put("name", box.getName());
                value.put("value", box.getValue());

                values.add(value);
            }
            bonus.put("specialty_skills", values);
        }

        return bonus;
    }

}
