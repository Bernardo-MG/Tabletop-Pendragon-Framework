package com.wandrell.tabletop.business.util.parser.xml.pendragon.input.chargen;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.pattern.parser.xml.input.JDOMDocumentDecoder;
import com.wandrell.tabletop.business.conf.pendragon.ModelXMLConf;
import com.wandrell.tabletop.business.model.dice.DefaultDice;
import com.wandrell.tabletop.business.model.dice.Dice;
import com.wandrell.tabletop.business.model.pendragon.chargen.FatherClassTemplate;
import com.wandrell.tabletop.business.model.skill.DefaultNameAndDescriptor;
import com.wandrell.tabletop.business.model.skill.NameAndDescriptor;
import com.wandrell.tabletop.business.service.pendragon.ModelService;
import com.wandrell.tabletop.business.util.DiceUtils;

public class FatherClassTemplateDocumentDecoder implements
        JDOMDocumentDecoder<FatherClassTemplate> {

    private final ModelService modelService;

    public FatherClassTemplateDocumentDecoder(final ModelService service) {
        super();

        modelService = service;
    }

    @Override
    public final FatherClassTemplate decode(final Document doc) {
        final Element root;
        final Element skillsGroupPointsNode;
        final Element skillsGroupPointsDivideNode;
        final Element skillsPointsNode;
        final Element skillsNonCombatPointsNode;
        final Element skillsGroupNode;
        final Element specialtySkillsNode;
        final Element directedTraitsNode;
        final Element directedTraitsBaseNode;
        final Element baseNode;
        final Element bonusNode;
        final Element moneyNode;
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
        Element descriptorNode;

        root = doc.getRootElement();

        // Name
        name = root.getChildText(ModelXMLConf.NAME);

        baseNode = root.getChild(ModelXMLConf.BASE);
        bonusNode = root.getChild(ModelXMLConf.BONUS);

        // Skills group bonus
        skillsGroupPointsNode = root
                .getChild(ModelXMLConf.SKILLS_GROUP_POINTS_BONUS);
        if (skillsGroupPointsNode == null) {
            skillsGroupPoints = 0;
        } else {
            skillsGroupPoints = Integer.parseInt(skillsGroupPointsNode
                    .getText());
        }

        // Skills group points to expend
        skillsGroupPointsDivideNode = root
                .getChild(ModelXMLConf.SKILLS_GROUP_POINTS_DIVIDE);
        if (skillsGroupPointsDivideNode == null) {
            skillsGroupPointsDivide = 0;
        } else {
            skillsGroupPointsDivide = Integer
                    .parseInt(skillsGroupPointsDivideNode.getText());
        }

        // Skills points to expend
        skillsPointsNode = root.getChild(ModelXMLConf.SKILLS_POINTS);
        if (skillsPointsNode == null) {
            skillsPoints = 0;
        } else {
            skillsPoints = Integer.parseInt(skillsPointsNode.getText());
        }

        // Non combat skills points to expend
        skillsNonCombatPointsNode = root
                .getChild(ModelXMLConf.SKILLS_NON_COMBAT_POINTS);
        if (skillsNonCombatPointsNode == null) {
            skillsNonCombatPoints = 0;
        } else {
            skillsNonCombatPoints = Integer.parseInt(skillsNonCombatPointsNode
                    .getText());
        }

        // Money
        moneyNode = root.getChild(ModelXMLConf.MONEY);
        if (moneyNode == null) {
            money = new DefaultDice(0, 1);
        } else {
            money = DiceUtils.parseDice(moneyNode.getText());
        }

        // Skills group
        skillsGroup = new LinkedList<>();
        skillsGroupNode = root.getChild(ModelXMLConf.SKILLS_GROUP);
        if (moneyNode != null) {
            for (final Element skill : skillsGroupNode.getChildren()) {
                descriptorNode = skill.getChild(ModelXMLConf.DESCRIPTOR);
                if (descriptorNode == null) {
                    skillsGroup.add(new DefaultNameAndDescriptor(skill
                            .getChildText(ModelXMLConf.NAME), ""));
                } else {
                    skillsGroup.add(new DefaultNameAndDescriptor(skill
                            .getChildText(ModelXMLConf.NAME), descriptorNode
                            .getText()));
                }
            }
        }

        // Specialty skills
        specialtySkills = new LinkedHashMap<>();
        specialtySkillsNode = bonusNode.getChild(ModelXMLConf.SPECIALTY_SKILLS);
        if (specialtySkillsNode != null) {
            for (final Element skill : specialtySkillsNode.getChildren()) {
                specialtySkills
                        .put(skill.getChildText(ModelXMLConf.NAME), Integer
                                .parseInt(skill
                                        .getChildText(ModelXMLConf.VALUE)));
            }
        }

        // Directed traits bonus
        directedTraits = new LinkedHashMap<>();
        directedTraitsNode = bonusNode.getChild(ModelXMLConf.DIRECTED_TRAITS);
        if (directedTraitsNode != null) {
            for (final Element trait : directedTraitsNode.getChildren()) {
                directedTraits
                        .put(new DefaultNameAndDescriptor(trait
                                .getChildText(ModelXMLConf.NAME), trait
                                .getChildText(ModelXMLConf.DESCRIPTOR)),
                                Integer.parseInt(trait
                                        .getChildText(ModelXMLConf.VALUE)));
            }
        }

        // Directed traits base
        directedTraitsBase = new LinkedHashMap<>();
        directedTraitsBaseNode = baseNode
                .getChild(ModelXMLConf.DIRECTED_TRAITS);
        if (directedTraitsNode != null) {
            for (final Element trait : directedTraitsBaseNode.getChildren()) {
                directedTraitsBase
                        .put(new DefaultNameAndDescriptor(trait
                                .getChildText(ModelXMLConf.NAME), trait
                                .getChildText(ModelXMLConf.DESCRIPTOR)),
                                Integer.parseInt(trait
                                        .getChildText(ModelXMLConf.VALUE)));
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
