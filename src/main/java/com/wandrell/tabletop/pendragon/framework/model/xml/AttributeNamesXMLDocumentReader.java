package com.wandrell.tabletop.pendragon.framework.model.xml;

import java.util.Collection;
import java.util.LinkedList;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.pendragon.framework.conf.FileToken;
import com.wandrell.util.file.api.xml.XMLDocumentReader;

public class AttributeNamesXMLDocumentReader implements
	XMLDocumentReader<Collection<String>> {

    public AttributeNamesXMLDocumentReader() {
	super();
    }

    @Override
    public final Collection<String> getValue(final Document doc) {
	final Element root;
	final Collection<String> attributes;

	root = doc.getRootElement();

	attributes = new LinkedList<>();
	for (final Element node : root.getChildren()) {
	    attributes.add(node.getChildText(FileToken.NAME));
	}

	return attributes;
    }

}
