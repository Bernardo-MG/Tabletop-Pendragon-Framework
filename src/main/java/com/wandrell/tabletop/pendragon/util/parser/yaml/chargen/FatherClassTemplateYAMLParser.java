package com.wandrell.tabletop.pendragon.util.parser.yaml.chargen;

import java.io.Reader;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.dice.notation.DefaultDiceFormula;
import com.wandrell.tabletop.dice.notation.DiceFormula;
import com.wandrell.tabletop.dice.notation.IntegerComponent;
import com.wandrell.tabletop.dice.parser.DiceFormulaParser;
import com.wandrell.tabletop.pendragon.model.chargen.background.FatherClassTemplate;
import com.wandrell.tabletop.pendragon.service.model.ModelConstructorService;
import com.wandrell.tabletop.stats.valuebox.DefaultSkillBox;
import com.wandrell.tabletop.stats.valuebox.SkillBox;

public class FatherClassTemplateYAMLParser implements
        Parser<Reader, FatherClassTemplate> {

    private final ModelConstructorService modelService;

    public FatherClassTemplateYAMLParser(final ModelConstructorService service) {
        super();

        modelService = service;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final FatherClassTemplate parse(final Reader reader)
            throws Exception {
        final Yaml yaml;
        final Map<String, Object> values;
        final Collection<Map<String, Object>> skillsGroupMap;
        final Collection<Map<String, Object>> specialtySkillsMap;
        final Collection<Map<String, Object>> directedTraitsMap;
        final Collection<Map<String, Object>> directedTraitsBaseMap;
        final Map<String, Collection<Map<String, Object>>> baseMap;
        final Map<String, Collection<Map<String, Object>>> bonusMap;
        final String name;
        final Integer skillsGroupPoints;
        final Integer skillsGroupPointsDivide;
        final Integer skillsPoints;
        final Integer skillsNonCombatPoints;
        final DiceFormula money;
        final Collection<SkillBox> skillsGroup;
        final Collection<SkillBox> specialtySkills;
        final Collection<SkillBox> directedTraits;
        final Collection<SkillBox> directedTraitsBase;
        final Parser<String, DiceFormula> diceParser;
        String descriptor;

        diceParser = new DiceFormulaParser();

        yaml = new Yaml();

        values = (Map<String, Object>) yaml.load(reader);

        // Name
        name = (String) values.get("name");

        baseMap = (Map<String, Collection<Map<String, Object>>>) values
                .get("base");
        bonusMap = (Map<String, Collection<Map<String, Object>>>) values
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
            money = new DefaultDiceFormula(new IntegerComponent(0));
        }

        // Skills group
        skillsGroup = new LinkedList<SkillBox>();
        skillsGroupMap = (Collection<Map<String, Object>>) values
                .get("skills_group");
        if (skillsGroupMap != null) {
            for (final Map<String, Object> skill : skillsGroupMap) {
                descriptor = (String) skill.get("descriptor");
                if (descriptor == null) {
                    skillsGroup.add(new DefaultSkillBox((String) skill
                            .get("name"), "", 0));
                } else {
                    skillsGroup.add(new DefaultSkillBox((String) skill
                            .get("name"), descriptor, 0));
                }
            }
        }

        specialtySkills = new LinkedList<SkillBox>();
        directedTraits = new LinkedList<SkillBox>();
        directedTraitsBase = new LinkedList<SkillBox>();

        if (bonusMap != null) {
            // Specialty skills
            if (bonusMap.containsKey("specialty_skills")) {
                specialtySkillsMap = bonusMap.get("specialty_skills");
                if (specialtySkillsMap != null) {
                    for (final Map<String, Object> skill : specialtySkillsMap) {
                        specialtySkills.add(new DefaultSkillBox((String) skill
                                .get("name"), (Integer) skill.get("value")));
                    }
                }
            }

            // Directed traits bonus
            if (bonusMap.containsKey("directed_traits")) {
                directedTraitsMap = bonusMap.get("directed_traits");
                if (directedTraitsMap != null) {
                    for (final Map<String, Object> trait : directedTraitsMap) {
                        directedTraits.add(new DefaultSkillBox((String) trait
                                .get("name"), (String) trait.get("descriptor"),
                                (Integer) trait.get("value")));
                    }
                }
            }

            // Directed traits base
            if (bonusMap.containsKey("directed_traits")) {
                directedTraitsBaseMap = baseMap.get("directed_traits");
                if (directedTraitsBaseMap != null) {
                    for (final Map<String, Object> trait : directedTraitsBaseMap) {
                        directedTraitsBase.add(new DefaultSkillBox(
                                (String) trait.get("name"), (String) trait
                                        .get("descriptor"), (Integer) trait
                                        .get("value")));
                    }
                }
            }
        }

        return getModelService().getFatherClassTemplate(name,
                skillsGroupPoints, skillsGroupPointsDivide, skillsPoints,
                skillsNonCombatPoints, money, skillsGroup, specialtySkills,
                directedTraits, directedTraitsBase);
    }

    private final ModelConstructorService getModelService() {
        return modelService;
    }

}
