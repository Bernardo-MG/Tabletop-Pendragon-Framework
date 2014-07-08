package com.wandrell.tabletop.pendragon.framework.persistence.xml;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.conf.FileStreamerTags;
import com.wandrell.tabletop.pendragon.framework.conf.FileLabels;
import com.wandrell.tabletop.pendragon.valuehandler.PendragonSpecialtySkill;
import com.wandrell.util.file.api.xml.XMLDocumentWriter;

public class SpecialtySkillXMLDocumentWriter implements
	XMLDocumentWriter<PendragonSpecialtySkill> {

    public SpecialtySkillXMLDocumentWriter() {
	super();
    }

    @Override
    public final Document getDocument(final PendragonSpecialtySkill holder) {
	final Document doc;
	Element element;

	// Root and skill name
	element = new Element(FileLabels.ADVANCED_SKILL);
	element.setAttribute(FileStreamerTags.NAME, holder.getName());

	doc = new Document(element);

	// Skills list
	// TODO
	// doc.getRootElement().addContent(
	// XMLUtils.buildStringListXMLTree(new Element(FileLabels.SKILLS),
	// holder.getSkillsNames(), FileLabels.SKILL));

	return doc;
    }

}
