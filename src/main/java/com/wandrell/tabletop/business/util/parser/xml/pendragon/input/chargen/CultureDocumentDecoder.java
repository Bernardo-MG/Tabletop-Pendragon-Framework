package com.wandrell.tabletop.business.util.parser.xml.pendragon.input.chargen;

import java.util.LinkedHashMap;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.business.conf.pendragon.ModelXMLConf;
import com.wandrell.tabletop.business.model.dice.Dice;
import com.wandrell.tabletop.business.model.pendragon.chargen.AdditionalBelongingsTable;
import com.wandrell.tabletop.business.model.pendragon.chargen.CultureCharacterTemplate;
import com.wandrell.tabletop.business.model.pendragon.chargen.CultureTemplate;
import com.wandrell.tabletop.business.model.pendragon.chargen.FamilyCharacteristicTemplate;
import com.wandrell.tabletop.business.model.skill.DefaultNameAndDescriptor;
import com.wandrell.tabletop.business.model.skill.NameAndDescriptor;
import com.wandrell.tabletop.business.service.pendragon.ModelService;
import com.wandrell.tabletop.business.util.DiceUtils;
import com.wandrell.util.parser.xml.input.JDOMDocumentDecoder;
import com.wandrell.util.repository.Repository;

public class CultureDocumentDecoder implements
        JDOMDocumentDecoder<CultureTemplate> {

    private final Repository<AdditionalBelongingsTable>    addBelongRepo;
    private final Repository<FamilyCharacteristicTemplate> famCharRepo;
    private final ModelService                             modelService;

    public CultureDocumentDecoder(final ModelService service,
            final Repository<FamilyCharacteristicTemplate> fcRepo,
            final Repository<AdditionalBelongingsTable> abRepo) {
        super();

        modelService = service;
        famCharRepo = fcRepo;
        addBelongRepo = abRepo;
    }

    @Override
    public final CultureTemplate decode(final Document doc) {
        final Element root;
        final Element characteristics;
        final Element initialLuck;
        final Element template;
        final String name;
        final FamilyCharacteristicTemplate charMale;
        final FamilyCharacteristicTemplate charFemale;
        final AdditionalBelongingsTable belonginsMale;
        final AdditionalBelongingsTable belonginsFemale;
        final CultureCharacterTemplate templateMale;
        final CultureCharacterTemplate templateFemale;

        root = doc.getRootElement();

        // Name
        name = root.getChildText(ModelXMLConf.NAME);

        characteristics = root.getChild("family_characteristic");
        charMale = getFamilyCharacteristicTemplateByName(characteristics
                .getChildText("male"));
        charFemale = getFamilyCharacteristicTemplateByName(characteristics
                .getChildText("female"));

        initialLuck = root.getChild("initial_luck");
        belonginsMale = getAdditionalBelongingsTableByName(initialLuck
                .getChildText("male"));
        belonginsFemale = getAdditionalBelongingsTableByName(initialLuck
                .getChildText("female"));

        template = root.getChild("template");
        templateMale = buildCultureCharacterTemplate(template.getChild("male"));
        templateFemale = buildCultureCharacterTemplate(template
                .getChild("female"));

        return getModelService().getCultureTemplate(name, charMale, charFemale,
                belonginsMale, belonginsFemale, templateMale, templateFemale);
    }

    private final CultureCharacterTemplate buildCultureCharacterTemplate(
            final Element template) {
        final Map<String, Integer> attributesBonus;
        final Map<String, Dice> attributesRandom;
        final Map<NameAndDescriptor, Integer> skillsBonus;
        final Map<String, Integer> specialtySkills;
        final Map<NameAndDescriptor, Integer> passionsBonus;
        final Map<NameAndDescriptor, Dice> passionsRandom;
        final Map<NameAndDescriptor, Integer> directedBonus;
        final Map<String, Integer> traitsBonus;
        Element descriptorNode;
        String descriptor;
        NameAndDescriptor skill;

        attributesBonus = new LinkedHashMap<>();
        for (final Element child : template.getChild("attributes_bonus")
                .getChildren()) {
            attributesBonus.put(child.getChildText("name"),
                    Integer.parseInt(child.getChildText("value")));
        }

        attributesRandom = new LinkedHashMap<>();
        for (final Element child : template.getChild("attributes_random")
                .getChildren()) {
            attributesRandom.put(child.getChildText("name"),
                    DiceUtils.parseDice(child.getChildText("value")));
        }

        skillsBonus = new LinkedHashMap<>();
        for (final Element child : template.getChild("skills_bonus")
                .getChildren()) {
            descriptorNode = child.getChild("descriptor");
            if (descriptorNode != null) {
                descriptor = descriptorNode.getText();
            } else {
                descriptor = "";
            }

            skill = new DefaultNameAndDescriptor(child.getChildText("name"),
                    descriptor);

            skillsBonus.put(skill,
                    Integer.parseInt(child.getChildText("value")));
        }

        specialtySkills = new LinkedHashMap<>();
        for (final Element child : template.getChild("specialty_skills")
                .getChildren()) {
            specialtySkills.put(child.getChildText("name"),
                    Integer.parseInt(child.getChildText("value")));
        }

        passionsBonus = new LinkedHashMap<>();
        for (final Element child : template.getChild("passions_bonus")
                .getChildren()) {
            descriptorNode = child.getChild("descriptor");
            if (descriptorNode != null) {
                descriptor = descriptorNode.getText();
            } else {
                descriptor = "";
            }

            skill = new DefaultNameAndDescriptor(child.getChildText("name"),
                    descriptor);

            passionsBonus.put(skill,
                    Integer.parseInt(child.getChildText("value")));
        }

        passionsRandom = new LinkedHashMap<>();
        for (final Element child : template.getChild("passions_random")
                .getChildren()) {
            descriptorNode = child.getChild("descriptor");
            if (descriptorNode != null) {
                descriptor = descriptorNode.getText();
            } else {
                descriptor = "";
            }

            skill = new DefaultNameAndDescriptor(child.getChildText("name"),
                    descriptor);

            passionsRandom.put(skill,
                    DiceUtils.parseDice(child.getChildText("value")));
        }

        directedBonus = new LinkedHashMap<>();
        for (final Element child : template.getChild("directed_traits_bonus")
                .getChildren()) {
            descriptorNode = child.getChild("descriptor");
            if (descriptorNode != null) {
                descriptor = descriptorNode.getText();
            } else {
                descriptor = "";
            }

            skill = new DefaultNameAndDescriptor(child.getChildText("name"),
                    descriptor);

            directedBonus.put(skill,
                    Integer.parseInt(child.getChildText("value")));
        }

        traitsBonus = new LinkedHashMap<>();
        for (final Element child : template.getChild("traits_bonus")
                .getChildren()) {
            traitsBonus.put(child.getChildText("name"),
                    Integer.parseInt(child.getChildText("value")));
        }

        return getModelService().getCultureCharacterTemplate(attributesBonus,
                attributesRandom, skillsBonus, specialtySkills, passionsBonus,
                passionsRandom, directedBonus, traitsBonus);
    }

    private final AdditionalBelongingsTable getAdditionalBelongingsTableByName(
            final String name) {
        return getAdditionalBelongingsTableRepository()
                .getCollection(c -> c.getName().equals(name)).iterator().next();
    }

    private final Repository<AdditionalBelongingsTable>
            getAdditionalBelongingsTableRepository() {
        return addBelongRepo;
    }

    private final FamilyCharacteristicTemplate
            getFamilyCharacteristicTemplateByName(final String name) {
        return getFamilyCharacteristicTemplateRepository()
                .getCollection(c -> c.getName().equals(name)).iterator().next();
    }

    private final Repository<FamilyCharacteristicTemplate>
            getFamilyCharacteristicTemplateRepository() {
        return famCharRepo;
    }

    private final ModelService getModelService() {
        return modelService;
    }

}
