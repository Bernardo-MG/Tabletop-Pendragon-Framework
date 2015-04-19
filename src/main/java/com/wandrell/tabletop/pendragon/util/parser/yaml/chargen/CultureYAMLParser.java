package com.wandrell.tabletop.pendragon.util.parser.yaml.chargen;

import java.io.Reader;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.google.common.base.Predicate;
import com.wandrell.pattern.parser.Parser;
import com.wandrell.pattern.repository.QueryableRepository;
import com.wandrell.tabletop.dice.DefaultDice;
import com.wandrell.tabletop.dice.Dice;
import com.wandrell.tabletop.dice.StringDiceParser;
import com.wandrell.tabletop.pendragon.model.character.stats.AttributesHolder;
import com.wandrell.tabletop.pendragon.model.character.stats.AttributesRandom;
import com.wandrell.tabletop.pendragon.model.character.stats.DefaultAttributesHolder;
import com.wandrell.tabletop.pendragon.model.character.stats.DefaultAttributesRandom;
import com.wandrell.tabletop.pendragon.model.character.stats.DefaultRandomSkill;
import com.wandrell.tabletop.pendragon.model.character.stats.DefaultTraitsHolder;
import com.wandrell.tabletop.pendragon.model.character.stats.RandomSkill;
import com.wandrell.tabletop.pendragon.model.character.stats.TraitsHolder;
import com.wandrell.tabletop.pendragon.model.chargen.background.CultureCharacterTemplate;
import com.wandrell.tabletop.pendragon.model.chargen.background.CultureTemplate;
import com.wandrell.tabletop.pendragon.model.chargen.background.FamilyCharacteristicTemplate;
import com.wandrell.tabletop.pendragon.model.chargen.inventory.AdditionalBelongingsTable;
import com.wandrell.tabletop.pendragon.service.model.ModelConstructorService;
import com.wandrell.tabletop.valuebox.DefaultSkillBox;
import com.wandrell.tabletop.valuebox.SkillBox;

public class CultureYAMLParser implements Parser<Reader, CultureTemplate> {

    private final QueryableRepository<AdditionalBelongingsTable, Predicate<AdditionalBelongingsTable>>       addBelongRepo;
    private final QueryableRepository<FamilyCharacteristicTemplate, Predicate<FamilyCharacteristicTemplate>> famCharRepo;
    private final ModelConstructorService                                                                    modelService;

    public CultureYAMLParser(
            final ModelConstructorService service,
            final QueryableRepository<FamilyCharacteristicTemplate, Predicate<FamilyCharacteristicTemplate>> fcRepo,
            final QueryableRepository<AdditionalBelongingsTable, Predicate<AdditionalBelongingsTable>> abRepo) {
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
        final AttributesHolder attributesBonus;
        final AttributesRandom attributesRandom;
        final Collection<SkillBox> skillsBonus;
        final Collection<SkillBox> specialtySkills;
        final Collection<SkillBox> passionsBonus;
        final Collection<RandomSkill> passionsRandom;
        final Collection<SkillBox> directedBonus;
        final TraitsHolder traitsBonus;
        final Parser<String, Dice> diceParser;
        String descriptor;
        RandomSkill skillRandom;
        Dice dice;

        diceParser = new StringDiceParser();

        attributesBonus = new DefaultAttributesHolder();
        skillsBonus = new LinkedList<>();
        specialtySkills = new LinkedList<>();
        passionsBonus = new LinkedList<>();
        passionsRandom = new LinkedList<>();
        directedBonus = new LinkedList<>();

        if (template != null) {
            if (template.containsKey("attributes_bonus")) {
                loadAttributesHolder(template.get("attributes_bonus"),
                        attributesBonus);
            }

            attributesRandom = loadAttributesRandom(template
                    .get("attributes_random"));

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

                    dice = diceParser.parse((String) child.get("value"));

                    skillRandom = new DefaultRandomSkill(
                            (String) child.get("name"), descriptor, dice);

                    passionsRandom.add(skillRandom);
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
            attributesRandom = new DefaultAttributesRandom(
                    new DefaultDice(0, 1), new DefaultDice(0, 1),
                    new DefaultDice(0, 1), new DefaultDice(0, 1),
                    new DefaultDice(0, 1));
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

    private final
            QueryableRepository<AdditionalBelongingsTable, Predicate<AdditionalBelongingsTable>>
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

    private final
            QueryableRepository<FamilyCharacteristicTemplate, Predicate<FamilyCharacteristicTemplate>>
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
            if (child.get("name").equals("arbitrary")) {
                traitsHolder.setArbitrary((Integer) child.get("value"));
            } else if (child.get("name").equals("chaste")) {
                traitsHolder.setChaste((Integer) child.get("value"));
            } else if (child.get("name").equals("cowardly")) {
                traitsHolder.setCowardly((Integer) child.get("value"));
            } else if (child.get("name").equals("cruel")) {
                traitsHolder.setCruel((Integer) child.get("value"));
            } else if (child.get("name").equals("deceitful")) {
                traitsHolder.setDeceitful((Integer) child.get("value"));
            } else if (child.get("name").equals("energetic")) {
                traitsHolder.setEnergetic((Integer) child.get("value"));
            } else if (child.get("name").equals("forgiving")) {
                traitsHolder.setForgiving((Integer) child.get("value"));
            } else if (child.get("name").equals("generous")) {
                traitsHolder.setGenerous((Integer) child.get("value"));
            } else if (child.get("name").equals("honest")) {
                traitsHolder.setHonest((Integer) child.get("value"));
            } else if (child.get("name").equals("indulgent")) {
                traitsHolder.setIndulgent((Integer) child.get("value"));
            } else if (child.get("name").equals("just")) {
                traitsHolder.setJust((Integer) child.get("value"));
            } else if (child.get("name").equals("lazy")) {
                traitsHolder.setLazy((Integer) child.get("value"));
            } else if (child.get("name").equals("lustful")) {
                traitsHolder.setLustful((Integer) child.get("value"));
            } else if (child.get("name").equals("merciful")) {
                traitsHolder.setMerciful((Integer) child.get("value"));
            } else if (child.get("name").equals("modest")) {
                traitsHolder.setModest((Integer) child.get("value"));
            } else if (child.get("name").equals("pious")) {
                traitsHolder.setPious((Integer) child.get("value"));
            } else if (child.get("name").equals("proud")) {
                traitsHolder.setProud((Integer) child.get("value"));
            } else if (child.get("name").equals("prudent")) {
                traitsHolder.setPrudent((Integer) child.get("value"));
            } else if (child.get("name").equals("reckless")) {
                traitsHolder.setReckless((Integer) child.get("value"));
            } else if (child.get("name").equals("selfish")) {
                traitsHolder.setSelfish((Integer) child.get("value"));
            } else if (child.get("name").equals("suspicious")) {
                traitsHolder.setSuspicious((Integer) child.get("value"));
            } else if (child.get("name").equals("temperate")) {
                traitsHolder.setTemperate((Integer) child.get("value"));
            } else if (child.get("name").equals("trusting")) {
                traitsHolder.setTrusting((Integer) child.get("value"));
            } else if (child.get("name").equals("valorous")) {
                traitsHolder.setValorous((Integer) child.get("value"));
            } else if (child.get("name").equals("vengeful")) {
                traitsHolder.setVengeful((Integer) child.get("value"));
            } else if (child.get("name").equals("wordly")) {
                traitsHolder.setWorldly((Integer) child.get("value"));
            }
        }

        return traitsHolder;
    }

    private final void loadAttributesHolder(
            final Collection<Map<String, Object>> attributes,
            final AttributesHolder holder) {
        for (final Map<String, Object> attribute : attributes) {
            if (attribute.get("name").toString().equals("appearance")) {
                holder.setAppearance((Integer) attribute.get("value"));
            } else if (attribute.get("name").toString().equals("constitution")) {
                holder.setConstitution((Integer) attribute.get("value"));
            } else if (attribute.get("name").toString().equals("dexterity")) {
                holder.setDexterity((Integer) attribute.get("value"));
            } else if (attribute.get("name").toString().equals("size")) {
                holder.setSize((Integer) attribute.get("value"));
            } else if (attribute.get("name").toString().equals("strength")) {
                holder.setStrength((Integer) attribute.get("value"));
            }
        }
    }

    private final AttributesRandom loadAttributesRandom(
            final Collection<Map<String, Object>> attributes) {
        final Parser<String, Dice> diceParser;
        Dice appearance;
        Dice constitution;
        Dice dexterity;
        Dice size;
        Dice strength;

        diceParser = new StringDiceParser();

        appearance = new DefaultDice(0, 1);
        constitution = new DefaultDice(0, 1);
        dexterity = new DefaultDice(0, 1);
        size = new DefaultDice(0, 1);
        strength = new DefaultDice(0, 1);

        if (attributes != null) {
            try {
                for (final Map<String, Object> attribute : attributes) {
                    if (attribute.get("name").toString().equals("appearance")) {
                        appearance = diceParser.parse(attribute.get("value")
                                .toString());
                    } else if (attribute.get("name").toString()
                            .equals("constitution")) {
                        constitution = diceParser.parse(attribute.get("value")
                                .toString());
                    } else if (attribute.get("name").toString()
                            .equals("dexterity")) {
                        dexterity = diceParser.parse(attribute.get("value")
                                .toString());
                    } else if (attribute.get("name").toString().equals("size")) {
                        size = diceParser.parse(attribute.get("value")
                                .toString());
                    } else if (attribute.get("name").toString()
                            .equals("strength")) {
                        strength = diceParser.parse(attribute.get("value")
                                .toString());
                    }
                }
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }

        return new DefaultAttributesRandom(appearance, constitution, dexterity,
                size, strength);
    }

}
