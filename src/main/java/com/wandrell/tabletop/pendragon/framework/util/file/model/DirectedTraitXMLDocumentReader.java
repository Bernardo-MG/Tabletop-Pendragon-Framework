package com.wandrell.tabletop.pendragon.framework.util.file.model;

import java.util.Collection;
import java.util.LinkedList;

import org.jdom2.Element;

import com.wandrell.tabletop.pendragon.framework.conf.FileToken;
import com.wandrell.tabletop.pendragon.framework.conf.factory.PendragonFactory;
import com.wandrell.tabletop.pendragon.valuehandler.DirectedTrait;
import com.wandrell.util.file.impl.xml.AbstractFilteredXMLDocumentReader;

public class DirectedTraitXMLDocumentReader extends
	AbstractFilteredXMLDocumentReader<Collection<DirectedTrait>> {

    public DirectedTraitXMLDocumentReader() {
	super(FileToken.DIRECTED_TRAIT);
    }

    @Override
    protected Collection<DirectedTrait> readNodes(
	    final Collection<Element> nodes) {
	final Collection<DirectedTrait> traits;
	final PendragonFactory factory;
	DirectedTrait trait;
	String name;

	factory = PendragonFactory.getInstance();
	traits = new LinkedList<>();
	for (final Element node : nodes) {
	    name = node.getChildText(FileToken.NAME);

	    trait = factory.getDirectedTrait(name);

	    traits.add(trait);
	}

	return traits;
    }

}
