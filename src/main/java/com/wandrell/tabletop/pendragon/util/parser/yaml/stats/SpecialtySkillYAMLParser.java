package com.wandrell.tabletop.pendragon.util.parser.yaml.stats;

import java.io.Reader;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.model.stats.SpecialtySkill;
import com.wandrell.tabletop.pendragon.service.model.ModelService;

public final class SpecialtySkillYAMLParser implements
        Parser<Reader, SpecialtySkill> {

    private final ModelService modelService;

    public SpecialtySkillYAMLParser(final ModelService service) {
        super();

        modelService = service;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final SpecialtySkill parse(final Reader reader) {
        final Yaml yaml;
        final Map<String, Object> values;
        final String name;
        final Collection<String> skills;

        yaml = new Yaml();

        values = (Map<String, Object>) yaml.load(reader);

        // Name
        name = (String) values.get("name");

        // Skills
        if ((values.containsKey("skills")) && (values.get("skills") != null)) {
            skills = (Collection<String>) values.get("skills");
        } else {
            skills = new LinkedList<>();
        }

        return getModelService().getSpecialtySkill(name, skills);
    }

    private final ModelService getModelService() {
        return modelService;
    }

}
