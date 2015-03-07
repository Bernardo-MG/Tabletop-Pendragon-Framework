package com.wandrell.tabletop.pendragon.util.parser.yaml.chargen;

import java.io.Reader;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.google.common.base.Predicate;
import com.wandrell.pattern.parser.Parser;
import com.wandrell.pattern.repository.Repository;
import com.wandrell.tabletop.pendragon.model.chargen.region.HomelandTemplate;
import com.wandrell.tabletop.pendragon.model.chargen.region.RegionTemplate;
import com.wandrell.tabletop.pendragon.service.model.ModelConstructorService;
import com.wandrell.tabletop.skill.DefaultSkillName;
import com.wandrell.tabletop.skill.SkillName;

public final class HomelandTemplateYAMLParser implements
        Parser<Reader, HomelandTemplate> {

    private final ModelConstructorService    modelService;
    private final Repository<RegionTemplate> regionRepo;

    public HomelandTemplateYAMLParser(final ModelConstructorService service,
            final Repository<RegionTemplate> regionRepo) {
        super();

        modelService = service;
        this.regionRepo = regionRepo;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final HomelandTemplate parse(final Reader reader) {
        final Yaml yaml;
        final Map<String, Object> values;
        final Map<String, Collection<Map<String, Object>>> bonus;
        final String name;
        final Map<SkillName, Integer> skills;
        final Map<String, Integer> specialtySkills;
        final Collection<SkillName> passions;
        final Collection<SkillName> directedTraits;
        final RegionTemplate region;
        SkillName skillData;
        String descriptor;

        yaml = new Yaml();

        values = (Map<String, Object>) yaml.load(reader);

        // Name
        name = (String) values.get("name");

        if (values.containsKey("region")) {
            region = getRegionRepository()
                    .getCollection(new Predicate<RegionTemplate>() {

                        @Override
                        public final boolean apply(final RegionTemplate input) {
                            return input.getName().equals(values.get("region"));
                        }

                    }).iterator().next();
        } else {
            region = null;
        }

        bonus = (Map<String, Collection<Map<String, Object>>>) values
                .get("bonus");

        skills = new LinkedHashMap<SkillName, Integer>();
        specialtySkills = new LinkedHashMap<String, Integer>();
        directedTraits = new LinkedList<SkillName>();
        passions = new LinkedList<SkillName>();

        if (bonus != null) {
            // Skills
            if (bonus.containsKey("skills")) {
                for (final Map<String, Object> skill : bonus.get("skills")) {
                    descriptor = (String) skill.get("descriptor");
                    if (descriptor == null) {
                        descriptor = "";
                    }
                    skillData = new DefaultSkillName(
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

            // Directed traits
            if (bonus.containsKey("directed_traits")) {
                for (final Map<String, Object> trait : bonus
                        .get("directed_traits")) {
                    skillData = new DefaultSkillName(
                            (String) trait.get("name"),
                            (String) trait.get("descriptor"));
                    directedTraits.add(skillData);
                }
            }

            // Passions
            if (bonus.containsKey("passions")) {
                for (final Map<String, Object> passion : bonus.get("passions")) {
                    skillData = new DefaultSkillName(
                            (String) passion.get("name"),
                            (String) passion.get("descriptor"));
                    passions.add(skillData);
                }
            }
        }

        return getModelService().getHomelandTemplate(name, region, skills,
                specialtySkills, directedTraits, passions);
    }

    private final ModelConstructorService getModelService() {
        return modelService;
    }

    private final Repository<RegionTemplate> getRegionRepository() {
        return regionRepo;
    }

}
