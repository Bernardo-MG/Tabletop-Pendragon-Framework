package com.wandrell.tabletop.business.util.parser.xml.pendragon.input.stats;

import java.util.Collection;
import java.util.LinkedList;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.business.conf.pendragon.ModelXMLConf;
import com.wandrell.tabletop.business.model.pendragon.stats.Skill;
import com.wandrell.tabletop.business.service.pendragon.ModelService;
import com.wandrell.util.parser.xml.input.JDOMDocumentDecoder;

public final class SkillDocumentDecoder implements
        JDOMDocumentDecoder<Collection<Skill>> {

    private final ModelService modelService;

    public SkillDocumentDecoder(final ModelService service) {
        super();

        modelService = service;
    }

    @Override
    public final Collection<Skill> decode(final Document doc) {
        final Collection<Skill> skills;
        Skill skill;
        String name;
        String descriptor;
        Boolean combat;
        Boolean court;
        Boolean knight;
        Boolean knowledge;
        Boolean repeatable;

        skills = new LinkedList<>();
        for (final Element node : doc.getRootElement().getChildren()) {
            name = node.getChildText(ModelXMLConf.NAME);
            descriptor = node.getChildText(ModelXMLConf.DESCRIPTOR);

            combat = new Boolean(node.getAttributeValue(ModelXMLConf.COMBAT));
            court = new Boolean(node.getAttributeValue(ModelXMLConf.COURTLY));
            knight = new Boolean(node.getAttributeValue(ModelXMLConf.KNIGHTLY));
            knowledge = new Boolean(
                    node.getAttributeValue(ModelXMLConf.KNOWLEDGE));
            repeatable = new Boolean(
                    node.getAttributeValue(ModelXMLConf.REPEATEABLE));

            skill = getModelService().getSkill(name, descriptor, combat, court,
                    knight, knowledge, repeatable);

            skills.add(skill);
        }

        return skills;
    }

    private final ModelService getModelService() {
        return modelService;
    }

}
