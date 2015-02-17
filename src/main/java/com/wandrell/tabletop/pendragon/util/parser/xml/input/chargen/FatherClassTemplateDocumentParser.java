package com.wandrell.tabletop.pendragon.util.parser.xml.input.chargen;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.dice.DefaultDice;
import com.wandrell.tabletop.dice.Dice;
import com.wandrell.tabletop.dice.StringDiceParser;
import com.wandrell.tabletop.pendragon.conf.ModelXMLConf;
import com.wandrell.tabletop.pendragon.model.chargen.FatherClassTemplate;
import com.wandrell.tabletop.pendragon.service.ModelService;
import com.wandrell.tabletop.skill.DefaultNameAndDescriptor;
import com.wandrell.tabletop.skill.NameAndDescriptor;

public class FatherClassTemplateDocumentParser implements
        Parser<Document, FatherClassTemplate> {

    private final ModelService modelService;

    public FatherClassTemplateDocumentParser(final ModelService service) {
        super();

        modelService = service;
    }

    @Override
    public final FatherClassTemplate parse(final Document doc) throws Exception {
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
        final Parser<String, Dice> diceParser;
        Element descriptorNode;

        diceParser = new StringDiceParser();

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
            money = diceParser.parse(moneyNode.getText());
        }

        // Skills group
        skillsGroup = new LinkedList<NameAndDescriptor>();
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
        specialtySkills = new LinkedHashMap<String, Integer>();
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
        directedTraits = new LinkedHashMap<NameAndDescriptor, Integer>();
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
        directedTraitsBase = new LinkedHashMap<NameAndDescriptor, Integer>();
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
