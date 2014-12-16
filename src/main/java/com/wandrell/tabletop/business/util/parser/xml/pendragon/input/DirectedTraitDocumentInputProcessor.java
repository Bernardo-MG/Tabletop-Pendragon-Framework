package com.wandrell.tabletop.business.util.parser.xml.pendragon.input;

import java.util.Collection;
import java.util.LinkedList;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.business.conf.pendragon.FileToken;
import com.wandrell.tabletop.business.conf.pendragon.factory.PendragonFactory;
import com.wandrell.tabletop.business.model.pendragon.stats.DirectedTrait;
import com.wandrell.util.parser.xml.input.JDOMDocumentInputProcessor;

public final class DirectedTraitDocumentInputProcessor implements
        JDOMDocumentInputProcessor<Collection<DirectedTrait>> {

    public DirectedTraitDocumentInputProcessor() {
        super();
    }

    @Override
    public final Collection<DirectedTrait> process(final Document doc) {
        final Collection<DirectedTrait> traits;
        final PendragonFactory factory;
        DirectedTrait trait;
        String name;

        factory = PendragonFactory.getInstance();
        traits = new LinkedList<>();
        for (final Element node : doc.getRootElement().getChildren()) {
            name = node.getChildText(FileToken.NAME);

            trait = factory.getDirectedTrait(name);

            traits.add(trait);
        }

        return traits;
    }

}
