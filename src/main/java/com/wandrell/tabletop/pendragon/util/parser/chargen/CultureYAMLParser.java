package com.wandrell.tabletop.pendragon.util.parser.chargen;

import java.io.Reader;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.pattern.repository.Repository;
import com.wandrell.pattern.repository.Repository.Filter;
import com.wandrell.tabletop.dice.Dice;
import com.wandrell.tabletop.dice.StringDiceParser;
import com.wandrell.tabletop.pendragon.model.chargen.AdditionalBelongingsTable;
import com.wandrell.tabletop.pendragon.model.chargen.CultureCharacterTemplate;
import com.wandrell.tabletop.pendragon.model.chargen.CultureTemplate;
import com.wandrell.tabletop.pendragon.model.chargen.FamilyCharacteristicTemplate;
import com.wandrell.tabletop.pendragon.service.ModelService;
import com.wandrell.tabletop.skill.DefaultNameAndDescriptor;
import com.wandrell.tabletop.skill.NameAndDescriptor;

public class CultureYAMLParser implements Parser<Reader, CultureTemplate> {

    private final Repository<AdditionalBelongingsTable>    addBelongRepo;
    private final Repository<FamilyCharacteristicTemplate> famCharRepo;
    private final ModelService                             modelService;

    public CultureYAMLParser(final ModelService service,
            final Repository<FamilyCharacteristicTemplate> fcRepo,
            final Repository<AdditionalBelongingsTable> abRepo) {
        super();

        modelService = service;
        famCharRepo = fcRepo;
        addBelongRepo = abRepo;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final CultureTemplate parse(final Reader reader) throws Exception {
        final Yaml yaml;
        final Map<String, Object> values;
        final Map<String, String> characteristics;
        final Map<String, String> initialLuck;
        final Map<String, Map<String, Collection<Map<String, Object>>>> template;
        final String name;
        final FamilyCharacteristicTemplate charMale;
        final FamilyCharacteristicTemplate charFemale;
        final AdditionalBelongingsTable belonginsMale;
        final AdditionalBelongingsTable belonginsFemale;
        final CultureCharacterTemplate templateMale;
        final CultureCharacterTemplate templateFemale;

        yaml = new Yaml();

        values = (Map<String, Object>) yaml.load(reader);

        // Name
        name = (String) values.get("name");

        characteristics = (Map<String, String>) values
                .get("family_characteristic");
        charMale = getFamilyCharacteristicTemplateByName(characteristics
                .get("male"));
        charFemale = getFamilyCharacteristicTemplateByName(characteristics
                .get("female"));

        initialLuck = (Map<String, String>) values.get("initial_luck");
        belonginsMale = getAdditionalBelongingsTableByName(initialLuck
                .get("male"));
        belonginsFemale = getAdditionalBelongingsTableByName(initialLuck
                .get("female"));

        template = (Map<String, Map<String, Collection<Map<String, Object>>>>) values
                .get("template");
        templateMale = buildCultureCharacterTemplate(template.get("male"));
        templateFemale = buildCultureCharacterTemplate(template.get("female"));

        return getModelService().getCultureTemplate(name, charMale, charFemale,
                belonginsMale, belonginsFemale, templateMale, templateFemale);
    }

    private final CultureCharacterTemplate buildCultureCharacterTemplate(
            final Map<String, Collection<Map<String, Object>>> template)
            throws Exception {
        final Map<String, Integer> attributesBonus;
        final Map<String, Dice> attributesRandom;
        final Map<NameAndDescriptor, Integer> skillsBonus;
        final Map<String, Integer> specialtySkills;
        final Map<NameAndDescriptor, Integer> passionsBonus;
        final Map<NameAndDescriptor, Dice> passionsRandom;
        final Map<NameAndDescriptor, Integer> directedBonus;
        final Map<String, Integer> traitsBonus;
        final Parser<String, Dice> diceParser;
        String descriptor;
        NameAndDescriptor skill;

        diceParser = new StringDiceParser();

        attributesBonus = new LinkedHashMap<String, Integer>();
        for (final Map<String, Object> child : template.get("attributes_bonus")) {
            attributesBonus.put((String) child.get("name"),
                    (Integer) child.get("value"));
        }

        attributesRandom = new LinkedHashMap<String, Dice>();
        for (final Map<String, Object> child : template
                .get("attributes_random")) {
            attributesRandom.put((String) child.get("name"),
                    diceParser.parse((String) child.get("value")));
        }

        skillsBonus = new LinkedHashMap<NameAndDescriptor, Integer>();
        for (final Map<String, Object> child : template.get("skills_bonus")) {
            descriptor = (String) child.get("descriptor");
            if (descriptor == null) {
                descriptor = "";
            }
            skill = new DefaultNameAndDescriptor((String) child.get("name"),
                    descriptor);

            skillsBonus.put(skill, (Integer) child.get("value"));
        }

        specialtySkills = new LinkedHashMap<String, Integer>();
        for (final Map<String, Object> child : template.get("specialty_skills")) {
            specialtySkills.put((String) child.get("name"),
                    (Integer) child.get("value"));
        }

        passionsBonus = new LinkedHashMap<NameAndDescriptor, Integer>();
        for (final Map<String, Object> child : template.get("passions_bonus")) {
            descriptor = (String) child.get("descriptor");
            if (descriptor == null) {
                descriptor = "";
            }
            skill = new DefaultNameAndDescriptor((String) child.get("name"),
                    descriptor);

            passionsBonus.put(skill, (Integer) child.get("value"));
        }

        passionsRandom = new LinkedHashMap<NameAndDescriptor, Dice>();
        for (final Map<String, Object> child : template.get("passions_random")) {
            descriptor = (String) child.get("descriptor");
            if (descriptor == null) {
                descriptor = "";
            }
            skill = new DefaultNameAndDescriptor((String) child.get("name"),
                    descriptor);

            passionsRandom.put(skill,
                    diceParser.parse((String) child.get("value")));
        }

        directedBonus = new LinkedHashMap<NameAndDescriptor, Integer>();
        for (final Map<String, Object> child : template
                .get("directed_traits_bonus")) {
            descriptor = (String) child.get("descriptor");
            if (descriptor == null) {
                descriptor = "";
            }
            skill = new DefaultNameAndDescriptor((String) child.get("name"),
                    descriptor);

            directedBonus.put(skill, (Integer) child.get("value"));
        }

        traitsBonus = new LinkedHashMap<String, Integer>();
        for (final Map<String, Object> child : template.get("traits_bonus")) {
            traitsBonus.put((String) child.get("name"),
                    (Integer) child.get("value"));
        }

        return getModelService().getCultureCharacterTemplate(attributesBonus,
                attributesRandom, skillsBonus, specialtySkills, passionsBonus,
                passionsRandom, directedBonus, traitsBonus);
    }

    private final AdditionalBelongingsTable getAdditionalBelongingsTableByName(
            final String name) {
        return getAdditionalBelongingsTableRepository()
                .getCollection(new Filter<AdditionalBelongingsTable>() {

                    @Override
                    public final Boolean isValid(
                            final AdditionalBelongingsTable entity) {
                        return entity.getName().equals(name);
                    }

                }).iterator().next();
    }

    private final Repository<AdditionalBelongingsTable>
            getAdditionalBelongingsTableRepository() {
        return addBelongRepo;
    }

    private final FamilyCharacteristicTemplate
            getFamilyCharacteristicTemplateByName(final String name) {
        return getFamilyCharacteristicTemplateRepository()
                .getCollection(new Filter<FamilyCharacteristicTemplate>() {

                    @Override
                    public final Boolean isValid(
                            final FamilyCharacteristicTemplate entity) {
                        return entity.getName().equals(name);
                    }

                }).iterator().next();
    }

    private final Repository<FamilyCharacteristicTemplate>
            getFamilyCharacteristicTemplateRepository() {
        return famCharRepo;
    }

    private final ModelService getModelService() {
        return modelService;
    }

}
