package com.wandrell.tabletop.pendragon.framework.model.xml;

import java.util.Collection;
import java.util.LinkedList;

import org.jdom2.Element;

import com.wandrell.tabletop.pendragon.conf.factory.PendragonFactory;
import com.wandrell.tabletop.pendragon.framework.conf.FileToken;
import com.wandrell.tabletop.pendragon.valuehandler.PendragonDirectedTrait;
import com.wandrell.util.file.impl.xml.AbstractFilteredXMLDocumentReader;

public class DirectedTraitXMLDocumentReader extends
	AbstractFilteredXMLDocumentReader<Collection<PendragonDirectedTrait>> {

    public DirectedTraitXMLDocumentReader() {
	super(FileToken.DIRECTED_TRAIT);
    }

    @Override
    protected Collection<PendragonDirectedTrait> readNodes(
	    final Collection<Element> nodes) {
	final Collection<PendragonDirectedTrait> traits;
	final PendragonFactory factory;
	PendragonDirectedTrait trait;
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
