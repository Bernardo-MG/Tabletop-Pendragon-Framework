package com.wandrell.tabletop.pendragon.framework.model.xml;

import java.util.Collection;
import java.util.LinkedList;

import org.jdom2.Element;

import com.wandrell.tabletop.pendragon.conf.factory.PendragonFactory;
import com.wandrell.tabletop.pendragon.framework.conf.FileToken;
import com.wandrell.tabletop.pendragon.valuehandler.PendragonPassion;
import com.wandrell.util.file.impl.xml.AbstractFilteredXMLDocumentReader;

public class PassionXMLDocumentReader extends
	AbstractFilteredXMLDocumentReader<Collection<PendragonPassion>> {

    public PassionXMLDocumentReader() {
	super(FileToken.PASSION);
    }

    @Override
    protected Collection<PendragonPassion> readNodes(
	    final Collection<Element> nodes) {
	final Collection<PendragonPassion> passions;
	final PendragonFactory factory;
	PendragonPassion passion;
	String name;
	String descriptor;

	factory = PendragonFactory.getInstance();
	passions = new LinkedList<>();
	for (final Element node : nodes) {
	    name = node.getChildText(FileToken.NAME);
	    descriptor = node.getChildText(FileToken.DESCRIPTOR);

	    if (descriptor == null) {
		descriptor = "";
	    }

	    passion = factory.getPassion(name, descriptor);

	    passions.add(passion);
	}

	return passions;
    }

}
