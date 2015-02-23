package com.wandrell.tabletop.pendragon.util.outputter.stats;

import java.util.LinkedHashMap;
import java.util.Map;

import com.wandrell.tabletop.pendragon.model.stats.SpecialtySkill;
import com.wandrell.tabletop.pendragon.util.outputter.YAMLOutputter;

public final class SpecialtySkillYAMLOutputter extends
        YAMLOutputter<SpecialtySkill> {

    public SpecialtySkillYAMLOutputter() {
        super();
    }

    @Override
    protected final Map<String, Object> buildMap(final SpecialtySkill value) {
        final Map<String, Object> data;

        data = new LinkedHashMap<String, Object>();

        data.put("name", value.getName());
        data.put("skills", value.getSurrogatedSkills());

        return data;
    }

}
