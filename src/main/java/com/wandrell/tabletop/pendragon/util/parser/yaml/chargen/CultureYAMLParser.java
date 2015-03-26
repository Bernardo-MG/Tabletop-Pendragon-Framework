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
import com.wandrell.tabletop.dice.Dice;
import com.wandrell.tabletop.dice.StringDiceParser;
import com.wandrell.tabletop.pendragon.model.character.stats.DefaultTraitsHolder;
import com.wandrell.tabletop.pendragon.model.character.stats.TraitsHolder;
import com.wandrell.tabletop.pendragon.model.chargen.background.CultureCharacterTemplate;
import com.wandrell.tabletop.pendragon.model.chargen.background.CultureTemplate;
import com.wandrell.tabletop.pendragon.model.chargen.background.FamilyCharacteristicTemplate;
import com.wandrell.tabletop.pendragon.model.chargen.inventory.AdditionalBelongingsTable;
import com.wandrell.tabletop.pendragon.service.model.ModelConstructorService;
import com.wandrell.tabletop.valuebox.DefaultSkillBox;
import com.wandrell.tabletop.valuebox.SkillBox;

public class CultureYAMLParser implements Parser<Reader, CultureTemplate> {

    private final Repository<AdditionalBelongingsTable>    addBelongRepo;
    private final Repository<FamilyCharacteristicTemplate> famCharRepo;
    private final ModelConstructorService                  modelService;

    public CultureYAMLParser(final ModelConstructorService service,
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
        final Collection<SkillBox> attributesBonus;
        final Map<String, Dice> attributesRandom;
        final Collection<SkillBox> skillsBonus;
        final Collection<SkillBox> specialtySkills;
        final Collection<SkillBox> passionsBonus;
        final Map<SkillBox, Dice> passionsRandom;
        final Collection<SkillBox> directedBonus;
        final TraitsHolder traitsBonus;
        final Parser<String, Dice> diceParser;
        String descriptor;
        SkillBox skill;

        diceParser = new StringDiceParser();

        attributesBonus = new LinkedList<>();
        attributesRandom = new LinkedHashMap<String, Dice>();
        skillsBonus = new LinkedList<>();
        specialtySkills = new LinkedList<>();
        passionsBonus = new LinkedList<>();
        passionsRandom = new LinkedHashMap<SkillBox, Dice>();
        directedBonus = new LinkedList<>();

        if (template != null) {
            if (template.containsKey("attributes_bonus")) {
                for (final Map<String, Object> child : template
                        .get("attributes_bonus")) {
                    attributesBonus.add(new DefaultSkillBox(child.get("name")
                            .toString(), (Integer) child.get("value")));
                }
            }

            if (template.containsKey("attributes_random")) {
                for (final Map<String, Object> child : template
                        .get("attributes_random")) {
                    attributesRandom.put((String) child.get("name"),
                            diceParser.parse((String) child.get("value")));
                }
            }

            if (template.containsKey("skills_bonus")) {
                for (final Map<String, Object> child : template
                        .get("skills_bonus")) {
                    descriptor = (String) child.get("descriptor");
                    if (descriptor == null) {
                        descriptor = "";
                    }
                    skillsBonus.add(new DefaultSkillBox(child.get("name")
                            .toString(), descriptor, (Integer) child
                            .get("value")));
                }
            }

            if (template.containsKey("specialty_skills")) {
                for (final Map<String, Object> child : template
                        .get("specialty_skills")) {
                    specialtySkills.add(new DefaultSkillBox(child.get("name")
                            .toString(), (Integer) child.get("value")));
                }
            }

            if (template.containsKey("passions_bonus")) {
                for (final Map<String, Object> child : template
                        .get("passions_bonus")) {
                    descriptor = (String) child.get("descriptor");
                    if (descriptor == null) {
                        descriptor = "";
                    }
                    passionsBonus.add(new DefaultSkillBox(child.get("name")
                            .toString(), descriptor, (Integer) child
                            .get("value")));
                }
            }

            if (template.containsKey("passions_random")) {
                for (final Map<String, Object> child : template
                        .get("passions_random")) {
                    descriptor = (String) child.get("descriptor");
                    if (descriptor == null) {
                        descriptor = "";
                    }
                    skill = new DefaultSkillBox((String) child.get("name"),
                            descriptor, 0);

                    passionsRandom.put(skill,
                            diceParser.parse((String) child.get("value")));
                }
            }

            if (template.containsKey("directed_traits_bonus")) {
                for (final Map<String, Object> child : template
                        .get("directed_traits_bonus")) {
                    descriptor = (String) child.get("descriptor");
                    if (descriptor == null) {
                        descriptor = "";
                    }
                    directedBonus.add(new DefaultSkillBox(child.get("name")
                            .toString(), descriptor, (Integer) child
                            .get("value")));
                }
            }

            if (template.containsKey("traits_bonus")) {
                traitsBonus = getTraits(template.get("traits_bonus"));
            } else {
                traitsBonus = new DefaultTraitsHolder();
            }
        } else {
            traitsBonus = new DefaultTraitsHolder();
        }

        return getModelService().getCultureCharacterTemplate(attributesBonus,
                attributesRandom, skillsBonus, specialtySkills, passionsBonus,
                passionsRandom, directedBonus, traitsBonus);
    }

    private final AdditionalBelongingsTable getAdditionalBelongingsTableByName(
            final String name) {
        return getAdditionalBelongingsTableRepository()
                .getCollection(new Predicate<AdditionalBelongingsTable>() {

                    @Override
                    public final boolean apply(
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
                .getCollection(new Predicate<FamilyCharacteristicTemplate>() {

                    @Override
                    public final boolean apply(
                            final FamilyCharacteristicTemplate entity) {
                        return entity.getName().equals(name);
                    }

                }).iterator().next();
    }

    private final Repository<FamilyCharacteristicTemplate>
            getFamilyCharacteristicTemplateRepository() {
        return famCharRepo;
    }

    private final ModelConstructorService getModelService() {
        return modelService;
    }

    private final TraitsHolder getTraits(
            final Collection<Map<String, Object>> traits) {
        final TraitsHolder traitsHolder;

        traitsHolder = new DefaultTraitsHolder();

        for (final Map<String, Object> child : traits) {
            if (child.get("text").equals("arbitrary")) {
                traitsHolder.setArbitrary((Integer) child.get("value"));
            } else if (child.get("text").equals("chaste")) {
                traitsHolder.setChaste((Integer) child.get("value"));
            } else if (child.get("text").equals("cowardly")) {
                traitsHolder.setCowardly((Integer) child.get("value"));
            } else if (child.get("text").equals("cruel")) {
                traitsHolder.setCruel((Integer) child.get("value"));
            } else if (child.get("text").equals("deceitful")) {
                traitsHolder.setDeceitful((Integer) child.get("value"));
            } else if (child.get("text").equals("energetic")) {
                traitsHolder.setEnergetic((Integer) child.get("value"));
            } else if (child.get("text").equals("forgiving")) {
                traitsHolder.setForgiving((Integer) child.get("value"));
            } else if (child.get("text").equals("generous")) {
                traitsHolder.setGenerous((Integer) child.get("value"));
            } else if (child.get("text").equals("honest")) {
                traitsHolder.setHonest((Integer) child.get("value"));
            } else if (child.get("text").equals("indulgent")) {
                traitsHolder.setIndulgent((Integer) child.get("value"));
            } else if (child.get("text").equals("just")) {
                traitsHolder.setJust((Integer) child.get("value"));
            } else if (child.get("text").equals("lazy")) {
                traitsHolder.setLazy((Integer) child.get("value"));
            } else if (child.get("text").equals("lustful")) {
                traitsHolder.setLustful((Integer) child.get("value"));
            } else if (child.get("text").equals("merciful")) {
                traitsHolder.setMerciful((Integer) child.get("value"));
            } else if (child.get("text").equals("modest")) {
                traitsHolder.setModest((Integer) child.get("value"));
            } else if (child.get("text").equals("pious")) {
                traitsHolder.setPious((Integer) child.get("value"));
            } else if (child.get("text").equals("proud")) {
                traitsHolder.setProud((Integer) child.get("value"));
            } else if (child.get("text").equals("prudent")) {
                traitsHolder.setPrudent((Integer) child.get("value"));
            } else if (child.get("text").equals("reckless")) {
                traitsHolder.setReckless((Integer) child.get("value"));
            } else if (child.get("text").equals("selfish")) {
                traitsHolder.setSelfish((Integer) child.get("value"));
            } else if (child.get("text").equals("suspicious")) {
                traitsHolder.setSuspicious((Integer) child.get("value"));
            } else if (child.get("text").equals("temperate")) {
                traitsHolder.setTemperate((Integer) child.get("value"));
            } else if (child.get("text").equals("trusting")) {
                traitsHolder.setTrusting((Integer) child.get("value"));
            } else if (child.get("text").equals("valorous")) {
                traitsHolder.setValorous((Integer) child.get("value"));
            } else if (child.get("text").equals("vengeful")) {
                traitsHolder.setVengeful((Integer) child.get("value"));
            } else if (child.get("text").equals("wordly")) {
                traitsHolder.setWorldly((Integer) child.get("value"));
            }
        }

        return traitsHolder;
    }

}
