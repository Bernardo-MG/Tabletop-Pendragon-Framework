package com.wandrell.tabletop.pendragon.framework.util.file.model;

import java.util.Collection;
import java.util.LinkedList;

import org.jdom2.Element;

import com.wandrell.tabletop.pendragon.conf.factory.PendragonFactory;
import com.wandrell.tabletop.pendragon.framework.conf.FileToken;
import com.wandrell.tabletop.pendragon.valuehandler.Skill;
import com.wandrell.util.file.impl.xml.AbstractFilteredXMLDocumentReader;

public class SkillXMLDocumentReader extends
	AbstractFilteredXMLDocumentReader<Collection<Skill>> {

    public SkillXMLDocumentReader() {
	super(FileToken.SKILL);
    }

    @Override
    protected Collection<Skill> readNodes(final Collection<Element> nodes) {
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
	for (final Element node : nodes) {
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
