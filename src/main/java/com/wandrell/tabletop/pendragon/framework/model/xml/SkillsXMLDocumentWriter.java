package com.wandrell.tabletop.pendragon.framework.model.xml;

import java.util.Collection;

import org.jdom2.Document;

import com.wandrell.tabletop.pendragon.valuehandler.PendragonSkill;
import com.wandrell.util.file.api.xml.XMLDocumentWriter;

public class SkillsXMLDocumentWriter implements
	XMLDocumentWriter<Collection<PendragonSkill>> {

    public SkillsXMLDocumentWriter() {
	super();
    }

    @Override
    public Document getDocument(final Collection<PendragonSkill> value) {
	return new Document();
    }

}
