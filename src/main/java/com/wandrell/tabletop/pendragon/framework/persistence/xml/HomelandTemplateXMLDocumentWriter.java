package com.wandrell.tabletop.pendragon.framework.persistence.xml;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.conf.FileStreamerTags;
import com.wandrell.tabletop.pendragon.character.background.Homeland;
import com.wandrell.tabletop.pendragon.framework.conf.FileLabels;
import com.wandrell.util.file.api.xml.XMLDocumentWriter;

public class HomelandTemplateXMLDocumentWriter implements
	XMLDocumentWriter<Homeland> {

    public HomelandTemplateXMLDocumentWriter() {
	super();
    }

    @Override
    public final Document getDocument(final Homeland holder) {
	final Document doc;
	Element node;

	// Main body and name of the template
	node = new Element(FileLabels.HOMELAND);
	node.setAttribute(FileStreamerTags.NAME, holder.getName());
	doc = new Document(node);

	// TODO
	// PersistenceFactory.getTemplatesContainerService().addToXMLDocument(doc,
	// holder);

	return doc;
    }

}
