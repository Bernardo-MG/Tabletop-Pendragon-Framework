package com.wandrell.tabletop.pendragon.util.parser.dictionary.stats;

import java.util.LinkedHashMap;
import java.util.Map;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.model.stats.SpecialtySkillBox;

public final class SpecialtySkillMapParser implements
        Parser<SpecialtySkillBox, Map<String, Object>> {

    public SpecialtySkillMapParser() {
        super();
    }

    @Override
    public final Map<String, Object> parse(final SpecialtySkillBox value) {
        final Map<String, Object> data;

        data = new LinkedHashMap<String, Object>();

        data.put("name", value.getName());
        data.put("skills", value.getSurrogatedSkills());

        return data;
    }

}
