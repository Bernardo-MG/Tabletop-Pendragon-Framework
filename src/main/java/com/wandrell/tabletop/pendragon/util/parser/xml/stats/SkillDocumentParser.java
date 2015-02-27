package com.wandrell.tabletop.pendragon.util.parser.xml.stats;

import java.util.Collection;
import java.util.LinkedList;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.pattern.parser.Parser;
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
            name = node.getChildText("name");
            descriptor = node.getChildText("descriptor");

            combat = new Boolean(node.getAttributeValue("combat"));
            court = new Boolean(node.getAttributeValue("courtly"));
            knight = new Boolean(node.getAttributeValue("knightly"));
            knowledge = new Boolean(node.getAttributeValue("knowledge"));
            repeatable = new Boolean(node.getAttributeValue("repeatable"));

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
