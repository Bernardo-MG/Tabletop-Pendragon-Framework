package com.wandrell.tabletop.pendragon.util.parser.yaml.chargen;

import java.io.Reader;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.google.common.base.Predicate;
import com.wandrell.pattern.parser.Parser;
import com.wandrell.pattern.repository.Repository;
import com.wandrell.tabletop.pendragon.model.chargen.region.HomelandTemplate;
import com.wandrell.tabletop.pendragon.model.chargen.region.RegionTemplate;
import com.wandrell.tabletop.pendragon.service.model.ModelConstructorService;
import com.wandrell.tabletop.valuebox.DefaultSkillBox;
import com.wandrell.tabletop.valuebox.SkillBox;

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
        final Collection<SkillBox> skills;
        final Collection<SkillBox> specialtySkills;
        final Collection<SkillBox> passions;
        final Collection<SkillBox> directedTraits;
        final RegionTemplate region;
        SkillBox skillData;
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

        skills = new LinkedList<SkillBox>();
        specialtySkills = new LinkedList<SkillBox>();
        directedTraits = new LinkedList<SkillBox>();
        passions = new LinkedList<SkillBox>();

        if (bonus != null) {
            // Skills
            if (bonus.containsKey("skills")) {
                for (final Map<String, Object> skill : bonus.get("skills")) {
                    descriptor = (String) skill.get("descriptor");
                    if (descriptor == null) {
                        descriptor = "";
                    }
                    skillData = new DefaultSkillBox((String) skill.get("name"),
                            descriptor, (Integer) skill.get("value"), 0,
                            Integer.MAX_VALUE);
                    skills.add(skillData);
                }
            }

            // Specialty skills
            if (bonus.containsKey("specialty_skills")) {
                for (final Map<String, Object> skill : bonus
                        .get("specialty_skills")) {
                    specialtySkills.add(new DefaultSkillBox((String) skill
                            .get("name"), (Integer) skill.get("value"), 0,
                            Integer.MAX_VALUE));
                }
            }

            // Directed traits
            if (bonus.containsKey("directed_traits")) {
                for (final Map<String, Object> trait : bonus
                        .get("directed_traits")) {
                    skillData = new DefaultSkillBox((String) trait.get("name"),
                            (String) trait.get("descriptor"), 0, 0,
                            Integer.MAX_VALUE);
                    directedTraits.add(skillData);
                }
            }

            // Passions
            if (bonus.containsKey("passions")) {
                for (final Map<String, Object> passion : bonus.get("passions")) {
                    skillData = new DefaultSkillBox(
                            (String) passion.get("name"),
                            (String) passion.get("descriptor"), 0, 0,
                            Integer.MAX_VALUE);
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
