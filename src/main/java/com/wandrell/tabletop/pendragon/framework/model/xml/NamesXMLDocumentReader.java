package com.wandrell.tabletop.pendragon.framework.model.xml;

import java.util.Collection;
import java.util.LinkedList;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.pendragon.framework.conf.FileToken;
import com.wandrell.util.file.api.xml.XMLDocumentReader;

public class NamesXMLDocumentReader implements
	XMLDocumentReader<Collection<String>> {

    public NamesXMLDocumentReader() {
	super();
    }

    @Override
    public final Collection<String> getValue(final Document doc) {
	final Element root;
	final Collection<String> names;

	root = doc.getRootElement();

	names = new LinkedList<>();
	for (final Element node : root.getChildren()) {
	    names.add(node.getChildText(FileToken.NAME));
	}

	return names;
    }

}
