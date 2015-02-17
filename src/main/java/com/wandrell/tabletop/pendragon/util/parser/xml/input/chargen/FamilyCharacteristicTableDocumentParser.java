package com.wandrell.tabletop.pendragon.util.parser.xml.input.chargen;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.interval.DefaultInterval;
import com.wandrell.tabletop.interval.Interval;
import com.wandrell.tabletop.pendragon.conf.ModelXMLConf;
import com.wandrell.tabletop.pendragon.model.chargen.FamilyCharacteristicTable;
import com.wandrell.tabletop.pendragon.model.chargen.FamilyCharacteristicTemplate;
import com.wandrell.tabletop.pendragon.service.ModelService;
import com.wandrell.tabletop.skill.DefaultNameAndDescriptor;
import com.wandrell.tabletop.skill.NameAndDescriptor;

public class FamilyCharacteristicTableDocumentParser implements
        Parser<Document, FamilyCharacteristicTable> {

    private final ModelService modelService;

    public FamilyCharacteristicTableDocumentParser(final ModelService service) {
        super();

        modelService = service;
    }

    @Override
    public final FamilyCharacteristicTable parse(final Document doc) {
        final Map<Interval, FamilyCharacteristicTemplate> intervalsMap;
        final Element root;
        final Element intervals;
        final String name;
        final List<Integer> limits;
        Interval interval;
        Integer pos;

        root = doc.getRootElement();

        // Name
        name = root.getChildText(ModelXMLConf.NAME);

        intervals = root.getChild(ModelXMLConf.INTERVALS);
        limits = new LinkedList<Integer>();
        for (final Element intervalNode : intervals.getChildren()) {
            limits.add(Integer.parseInt(intervalNode
                    .getChildText(ModelXMLConf.LOWER_LIMIT)));
        }

        pos = 0;
        intervalsMap = new LinkedHashMap<Interval, FamilyCharacteristicTemplate>();
        for (final Element intervalNode : intervals.getChildren()) {
            if (pos < (limits.size() - 1)) {
                interval = new DefaultInterval(limits.get(pos),
                        limits.get(pos + 1) - 1);
            } else {
                interval = new DefaultInterval(limits.get(pos), 20);
            }
            pos++;

            intervalsMap.put(interval, readFamilyCharacteristic(intervalNode
                    .getChild(ModelXMLConf.FAMILY_CHARACTERISTIC)));
        }

        return getModelService().getFamilyCharacteristicTable(name,
                intervalsMap);
    }

    private final ModelService getModelService() {
        return modelService;
    }

    private final FamilyCharacteristicTemplate readFamilyCharacteristic(
            final Element node) {
        final String name;
        final Map<String, Integer> attributes;
        final Map<NameAndDescriptor, Integer> skills;
        final Element attributesNode;
        final Element skillsNode;
        final Element bonus;
        Element descriptorNode;
        NameAndDescriptor skillData;
        String descriptor;
        Integer value;

        name = node.getChildText(ModelXMLConf.NAME);

        bonus = node.getChild(ModelXMLConf.BONUS);

        attributesNode = bonus.getChild(ModelXMLConf.ATTRIBUTES);
        attributes = new LinkedHashMap<String, Integer>();
        if (attributesNode != null) {
            for (final Element attribute : attributesNode.getChildren()) {
                value = Integer.parseInt(attribute
                        .getChildText(ModelXMLConf.VALUE));

                attributes
                        .put(attribute.getChildText(ModelXMLConf.NAME), value);
            }
        }

        skillsNode = bonus.getChild(ModelXMLConf.SKILLS);
        skills = new LinkedHashMap<NameAndDescriptor, Integer>();
        if (skillsNode != null) {
            for (final Element skill : skillsNode.getChildren()) {
                value = Integer
                        .parseInt(skill.getChildText(ModelXMLConf.VALUE));

                descriptorNode = skill.getChild(ModelXMLConf.DESCRIPTOR);

                if (descriptorNode == null) {
                    descriptor = "";
                } else {
                    descriptor = descriptorNode.getText();
                }

                skillData = new DefaultNameAndDescriptor(
                        skill.getChildText(ModelXMLConf.NAME), descriptor);

                skills.put(skillData, value);
            }
        }

        return getModelService().getFamilyCharacteristicTemplate(name,
                attributes, skills);
    }

}
