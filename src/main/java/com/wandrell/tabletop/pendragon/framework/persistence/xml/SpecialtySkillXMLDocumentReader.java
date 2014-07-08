package com.wandrell.tabletop.pendragon.framework.persistence.xml;

import java.util.Iterator;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.conf.FileStreamerTags;
import com.wandrell.tabletop.pendragon.framework.conf.FileLabels;
import com.wandrell.tabletop.pendragon.valuehandler.PendragonSpecialtySkill;
import com.wandrell.tabletop.util.XMLUtils;
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
	final Iterator<String> itrSkills;
	final String name;
	final Element root;

	root = doc.getRootElement();

	// Acquires the different sections
	skills = root.getChild(FileLabels.SKILLS);

	// Skill's name
	name = root.getAttributeValue(FileStreamerTags.NAME);

	// Acquires the different sections
	itrSkills = XMLUtils.readStringsListXMLTree(skills);

	// TODO
	// return new DefaultPendragonAggregatedSkill(name, itrSkills);
	return null;
    }

}
