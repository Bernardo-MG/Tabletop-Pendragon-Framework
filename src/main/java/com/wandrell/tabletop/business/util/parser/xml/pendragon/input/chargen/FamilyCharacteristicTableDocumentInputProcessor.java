package com.wandrell.tabletop.business.util.parser.xml.pendragon.input.chargen;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.business.conf.pendragon.ModelXMLConf;
import com.wandrell.tabletop.business.model.interval.DefaultInterval;
import com.wandrell.tabletop.business.model.interval.Interval;
import com.wandrell.tabletop.business.model.pendragon.chargen.FamilyCharacteristicTable;
import com.wandrell.tabletop.business.model.pendragon.chargen.FamilyCharacteristicTemplate;
import com.wandrell.tabletop.business.model.skill.DefaultNameAndDescriptor;
import com.wandrell.tabletop.business.model.skill.NameAndDescriptor;
import com.wandrell.tabletop.business.service.pendragon.ModelService;
import com.wandrell.util.parser.xml.input.JDOMDocumentInputProcessor;

public class FamilyCharacteristicTableDocumentInputProcessor implements
        JDOMDocumentInputProcessor<FamilyCharacteristicTable> {

    private final ModelService modelService;

    public FamilyCharacteristicTableDocumentInputProcessor(
            final ModelService service) {
        super();

        modelService = service;
    }

    @Override
    public final FamilyCharacteristicTable process(final Document doc) {
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
        limits = new LinkedList<>();
        for (final Element intervalNode : intervals.getChildren()) {
            limits.add(Integer.parseInt(intervalNode
                    .getChildText(ModelXMLConf.LOWER_LIMIT)));
        }

        pos = 0;
        intervalsMap = new LinkedHashMap<>();
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
        attributes = new LinkedHashMap<>();
        if (attributesNode != null) {
            for (final Element attribute : attributesNode.getChildren()) {
                value = Integer.parseInt(attribute
                        .getChildText(ModelXMLConf.VALUE));

                attributes
                        .put(attribute.getChildText(ModelXMLConf.NAME), value);
            }
        }

        skillsNode = bonus.getChild(ModelXMLConf.SKILLS);
        skills = new LinkedHashMap<>();
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
