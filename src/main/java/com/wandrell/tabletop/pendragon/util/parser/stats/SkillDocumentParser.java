package com.wandrell.tabletop.pendragon.util.parser.stats;

import java.util.Collection;
import java.util.LinkedList;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.conf.ModelXMLConf;
import com.wandrell.tabletop.pendragon.model.stats.Skill;
import com.wandrell.tabletop.pendragon.service.ModelService;

public final class SkillDocumentParser implements
        Parser<Document, Collection<Skill>> {

    private final ModelService modelService;

    public SkillDocumentParser(final ModelService service) {
        super();

        modelService = service;
    }

    @Override
    public final Collection<Skill> parse(final Document doc) {
        final Collection<Skill> skills;
        Skill skill;
        String name;
        String descriptor;
        Boolean combat;
        Boolean court;
        Boolean knight;
        Boolean knowledge;
        Boolean repeatable;

        skills = new LinkedList<Skill>();
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
