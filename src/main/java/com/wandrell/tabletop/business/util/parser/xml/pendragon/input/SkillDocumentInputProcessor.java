package com.wandrell.tabletop.business.util.parser.xml.pendragon.input;

import java.util.Collection;
import java.util.LinkedList;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.business.conf.pendragon.FileToken;
import com.wandrell.tabletop.business.conf.pendragon.factory.PendragonFactory;
import com.wandrell.tabletop.business.model.pendragon.stats.Skill;
import com.wandrell.util.parser.xml.input.JDOMDocumentInputProcessor;

public final class SkillDocumentInputProcessor implements
        JDOMDocumentInputProcessor<Collection<Skill>> {

    public SkillDocumentInputProcessor() {
        super();
    }

    @Override
    public final Collection<Skill> process(final Document doc) {
        final Collection<Skill> skills;
        final PendragonFactory factory;
        Skill skill;
        String name;
        Boolean combat;
        Boolean court;
        Boolean knight;
        Boolean knowledge;
        Boolean repeat;

        factory = PendragonFactory.getInstance();
        skills = new LinkedList<>();
        for (final Element node : doc.getRootElement().getChildren()) {
            name = node.getChildText(FileToken.NAME);

            combat = new Boolean(node.getAttributeValue(FileToken.COMBAT));
            court = new Boolean(node.getAttributeValue(FileToken.COURT));
            knight = new Boolean(node.getAttributeValue(FileToken.KNIGHT));
            knowledge = new Boolean(node.getAttributeValue(FileToken.KNOWLEDGE));
            repeat = new Boolean(node.getAttributeValue(FileToken.REPEAT));

            skill = factory.getSkill(name, combat, court, knight, knowledge,
                    repeat);

            skills.add(skill);
        }

        return skills;
    }

}
