package com.wandrell.tabletop.pendragon.util.parser.yaml.chargen;

import java.io.Reader;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.model.chargen.HomelandTemplate;
import com.wandrell.tabletop.pendragon.service.model.ModelService;
import com.wandrell.tabletop.skill.DefaultNameAndDescriptor;
import com.wandrell.tabletop.skill.NameAndDescriptor;

public final class HomelandTemplateYAMLParser implements
        Parser<Reader, HomelandTemplate> {

    private final ModelService modelService;

    public HomelandTemplateYAMLParser(final ModelService service) {
        super();

        modelService = service;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final HomelandTemplate parse(final Reader reader) {
        final Yaml yaml;
        final Map<String, Object> values;
        final Map<String, Collection<Map<String, Object>>> bonus;
        final String name;
        final Map<NameAndDescriptor, Integer> skills;
        final Map<String, Integer> specialtySkills;
        final Map<String, Integer> traits;
        final Collection<NameAndDescriptor> passions;
        final Collection<NameAndDescriptor> directedTraits;
        NameAndDescriptor skillData;
        String descriptor;

        yaml = new Yaml();

        values = (Map<String, Object>) yaml.load(reader);

        // Name
        name = (String) values.get("name");

        bonus = (Map<String, Collection<Map<String, Object>>>) values
                .get("bonus");

        skills = new LinkedHashMap<NameAndDescriptor, Integer>();
        specialtySkills = new LinkedHashMap<String, Integer>();
        traits = new LinkedHashMap<String, Integer>();
        directedTraits = new LinkedList<NameAndDescriptor>();
        passions = new LinkedList<NameAndDescriptor>();

        if (bonus != null) {
            // Skills
            if (bonus.containsKey("skills")) {
                for (final Map<String, Object> skill : bonus.get("skills")) {
                    descriptor = (String) skill.get("descriptor");
                    if (descriptor == null) {
                        descriptor = "";
                    }
                    skillData = new DefaultNameAndDescriptor(
                            (String) skill.get("name"), descriptor);
                    skills.put(skillData, (Integer) skill.get("value"));
                }
            }

            // Specialty skills
            if (bonus.containsKey("specialty_skills")) {
                for (final Map<String, Object> skill : bonus
                        .get("specialty_skills")) {
                    specialtySkills.put((String) skill.get("name"),
                            (Integer) skill.get("value"));
                }
            }

            // Traits
            if (bonus.containsKey("traits")) {
                for (final Map<String, Object> trait : bonus.get("traits")) {
                    traits.put((String) trait.get("name"),
                            (Integer) trait.get("value"));
                }
            }

            // Directed traits
            if (bonus.containsKey("directed_traits")) {
                for (final Map<String, Object> trait : bonus
                        .get("directed_traits")) {
                    skillData = new DefaultNameAndDescriptor(
                            (String) trait.get("name"),
                            (String) trait.get("descriptor"));
                    directedTraits.add(skillData);
                }
            }

            // Passions
            if (bonus.containsKey("passions")) {
                for (final Map<String, Object> passion : bonus.get("passions")) {
                    skillData = new DefaultNameAndDescriptor(
                            (String) passion.get("name"),
                            (String) passion.get("descriptor"));
                    passions.add(skillData);
                }
            }
        }

        return getModelService().getHomelandTemplate(name, skills,
                specialtySkills, traits, directedTraits, passions);
    }

    private final ModelService getModelService() {
        return modelService;
    }

}
