package com.wandrell.tabletop.pendragon.util.parser.yaml.chargen;

import java.io.Reader;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.model.chargen.HomelandTemplate;
import com.wandrell.tabletop.pendragon.service.ModelService;
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
        final Collection<Map<String, Object>> skillsMap;
        final Collection<Map<String, Object>> specialtySkillsMap;
        final Collection<Map<String, Object>> traitsMap;
        final Collection<Map<String, Object>> directedTraitsMap;
        final Collection<Map<String, Object>> passionsMap;
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

        // Skills
        skills = new LinkedHashMap<NameAndDescriptor, Integer>();
        skillsMap = bonus.get("skills");
        if (skillsMap != null) {
            for (final Map<String, Object> skill : skillsMap) {
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
        specialtySkills = new LinkedHashMap<String, Integer>();
        specialtySkillsMap = bonus.get("specialty_skills");
        if (specialtySkillsMap != null) {
            for (final Map<String, Object> skill : specialtySkillsMap) {
                specialtySkills.put((String) skill.get("name"),
                        (Integer) skill.get("value"));
            }
        }

        // Traits
        traits = new LinkedHashMap<String, Integer>();
        traitsMap = bonus.get("traits");
        if (traitsMap != null) {
            for (final Map<String, Object> trait : traitsMap) {
                traits.put((String) trait.get("name"),
                        (Integer) trait.get("value"));
            }
        }

        // Directed traits
        directedTraits = new LinkedList<NameAndDescriptor>();
        directedTraitsMap = bonus.get("directed_traits");
        if (directedTraitsMap != null) {
            for (final Map<String, Object> trait : directedTraitsMap) {
                skillData = new DefaultNameAndDescriptor(
                        (String) trait.get("name"),
                        (String) trait.get("descriptor"));
                directedTraits.add(skillData);
            }
        }

        // Passions
        passions = new LinkedList<NameAndDescriptor>();
        passionsMap = bonus.get("passions");
        if (passionsMap != null) {
            for (final Map<String, Object> passion : passionsMap) {
                skillData = new DefaultNameAndDescriptor(
                        (String) passion.get("name"),
                        (String) passion.get("descriptor"));
                passions.add(skillData);
            }
        }

        return getModelService().getHomelandTemplate(name, skills,
                specialtySkills, traits, directedTraits, passions);
    }

    private final ModelService getModelService() {
        return modelService;
    }

}
