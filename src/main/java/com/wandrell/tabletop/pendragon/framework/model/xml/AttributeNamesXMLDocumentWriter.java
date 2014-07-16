package com.wandrell.tabletop.pendragon.framework.model.xml;

import java.util.Collection;

import org.jdom2.Document;

import com.wandrell.util.file.api.xml.XMLDocumentWriter;

public class AttributeNamesXMLDocumentWriter implements
	XMLDocumentWriter<Collection<String>> {

    public AttributeNamesXMLDocumentWriter() {
	super();
    }

    @Override
    public Document getDocument(final Collection<String> value) {
	return new Document();
    }

}
