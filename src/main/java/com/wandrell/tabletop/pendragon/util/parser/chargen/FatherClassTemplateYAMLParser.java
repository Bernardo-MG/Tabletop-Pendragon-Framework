package com.wandrell.tabletop.pendragon.util.parser.chargen;

import java.io.Reader;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.dice.DefaultDice;
import com.wandrell.tabletop.dice.Dice;
import com.wandrell.tabletop.dice.StringDiceParser;
import com.wandrell.tabletop.pendragon.model.chargen.FatherClassTemplate;
import com.wandrell.tabletop.pendragon.service.ModelService;
import com.wandrell.tabletop.skill.DefaultNameAndDescriptor;
import com.wandrell.tabletop.skill.NameAndDescriptor;

public class FatherClassTemplateYAMLParser implements
        Parser<Reader, FatherClassTemplate> {

    private final ModelService modelService;

    public FatherClassTemplateYAMLParser(final ModelService service) {
        super();

        modelService = service;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final FatherClassTemplate parse(final Reader reader)
            throws Exception {
        final Yaml yaml;
        final Map<String, Object> values;
        final Collection<Map<String, Object>> skillsGroupMAp;
        final Collection<Map<String, Object>> specialtySkillsMAp;
        final Collection<Map<String, Object>> directedTraitsMAp;
        final Collection<Map<String, Object>> directedTraitsBaseMAp;
        final Map<String, Collection<Map<String, Object>>> baseMAp;
        final Map<String, Collection<Map<String, Object>>> bonusMAp;
        final String name;
        final Integer skillsGroupPoints;
        final Integer skillsGroupPointsDivide;
        final Integer skillsPoints;
        final Integer skillsNonCombatPoints;
        final Dice money;
        final Collection<NameAndDescriptor> skillsGroup;
        final Map<String, Integer> specialtySkills;
        final Map<NameAndDescriptor, Integer> directedTraits;
        final Map<NameAndDescriptor, Integer> directedTraitsBase;
        final Parser<String, Dice> diceParser;
        String descriptor;

        diceParser = new StringDiceParser();

        yaml = new Yaml();

        values = (Map<String, Object>) yaml.load(reader);

        // Name
        name = (String) values.get("name");

        baseMAp = (Map<String, Collection<Map<String, Object>>>) values
                .get("base");
        bonusMAp = (Map<String, Collection<Map<String, Object>>>) values
                .get("bonus");

        // Skills group bonus
        if (values.containsKey("skills_points_group_bonus")) {
            skillsGroupPoints = (Integer) values
                    .get("skills_points_group_bonus");
        } else {
            skillsGroupPoints = 0;
        }

        // Skills group points to expend
        if (values.containsKey("skills_points_group_divide")) {
            skillsGroupPointsDivide = (Integer) values
                    .get("skills_points_group_divide");
        } else {
            skillsGroupPointsDivide = 0;
        }

        // Skills points to expend
        if (values.containsKey("skills_points")) {
            skillsPoints = (Integer) values.get("skills_points");
        } else {
            skillsPoints = 0;
        }

        // Non combat skills points to expend
        if (values.containsKey("skills_points_non_combat")) {
            skillsNonCombatPoints = (Integer) values
                    .get("skills_points_non_combat");
        } else {
            skillsNonCombatPoints = 0;
        }

        // Money
        if (values.containsKey("money")) {
            money = diceParser.parse((String) values.get("money"));
        } else {
            money = new DefaultDice(0, 1);
        }

        // Skills group
        skillsGroup = new LinkedList<NameAndDescriptor>();
        skillsGroupMAp = (Collection<Map<String, Object>>) values
                .get("skills_group");
        if (skillsGroupMAp != null) {
            for (final Map<String, Object> skill : skillsGroupMAp) {
                descriptor = (String) skill.get("descriptor");
                if (descriptor == null) {
                    skillsGroup.add(new DefaultNameAndDescriptor((String) skill
                            .get("name"), ""));
                } else {
                    skillsGroup.add(new DefaultNameAndDescriptor((String) skill
                            .get("name"), descriptor));
                }
            }
        }

        // Specialty skills
        specialtySkills = new LinkedHashMap<String, Integer>();
        specialtySkillsMAp = bonusMAp.get("specialty_skills");
        if (specialtySkillsMAp != null) {
            for (final Map<String, Object> skill : specialtySkillsMAp) {
                specialtySkills.put((String) skill.get("name"),
                        (Integer) skill.get("value"));
            }
        }

        // Directed traits bonus
        directedTraits = new LinkedHashMap<NameAndDescriptor, Integer>();
        directedTraitsMAp = bonusMAp.get("directed_traits");
        if (directedTraitsMAp != null) {
            for (final Map<String, Object> trait : directedTraitsMAp) {
                directedTraits.put(
                        new DefaultNameAndDescriptor(
                                (String) trait.get("name"), (String) trait
                                        .get("descriptor")), (Integer) trait
                                .get("value"));
            }
        }

        // Directed traits base
        directedTraitsBase = new LinkedHashMap<NameAndDescriptor, Integer>();
        directedTraitsBaseMAp = baseMAp.get("directed_traits");
        if (directedTraitsMAp != null) {
            for (final Map<String, Object> trait : directedTraitsBaseMAp) {
                directedTraitsBase.put(
                        new DefaultNameAndDescriptor(
                                (String) trait.get("name"), (String) trait
                                        .get("descriptor")), (Integer) trait
                                .get("value"));
            }
        }

        return getModelService().getFatherClassTemplate(name,
                skillsGroupPoints, skillsGroupPointsDivide, skillsPoints,
                skillsNonCombatPoints, money, skillsGroup, specialtySkills,
                directedTraits, directedTraitsBase);
    }

    private final ModelService getModelService() {
        return modelService;
    }

}
