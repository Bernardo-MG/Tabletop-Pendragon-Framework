package com.wandrell.tabletop.pendragon.framework.persistence.xml;

import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.conf.FileStreamerTags;
import com.wandrell.tabletop.pendragon.conf.factory.PendragonFactory;
import com.wandrell.tabletop.pendragon.framework.conf.FileToken;
import com.wandrell.tabletop.pendragon.valuehandler.PendragonSpecialtySkill;
import com.wandrell.util.file.api.xml.XMLDocumentReader;

public class SpecialtySkillXMLDocumentReader implements
	XMLDocumentReader<PendragonSpecialtySkill> {

    public SpecialtySkillXMLDocumentReader() {
	super();
    }

    @SuppressWarnings("unused")
    @Override
    public final PendragonSpecialtySkill getValue(final Document doc) {
	final Element skills;
	final String name;
	final Element root;
	final PendragonFactory factory;
	final List<String> list;

	root = doc.getRootElement();

	// Acquires the different sections
	skills = root.getChild(FileToken.SKILLS);

	// Skill's name
	name = root.getAttributeValue(FileStreamerTags.NAME);

	// Acquires the different sections
	list = new ArrayList<String>(root.getChildren().size());
	for (final Element skill : skills.getChildren()) {
	    list.add(skill.getAttributeValue(FileStreamerTags.NAME));
	}

	factory = PendragonFactory.getInstance();
	return factory.getSpecialtySkill(name, list);
    }

}
