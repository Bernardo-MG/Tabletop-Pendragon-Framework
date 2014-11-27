package com.wandrell.tabletop.business.util.parser.xml.pendragon.input;

import java.util.Collection;
import java.util.LinkedList;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.business.conf.pendragon.FileToken;
import com.wandrell.tabletop.business.conf.pendragon.factory.PendragonFactory;
import com.wandrell.tabletop.business.model.pendragon.valuehandler.Passion;
import com.wandrell.util.parser.xml.input.JDOMDocumentInputProcessor;

public final class PassionDocumentInputProcessor implements
        JDOMDocumentInputProcessor<Collection<Passion>> {

    public PassionDocumentInputProcessor() {
        super();
    }

    @Override
    public final Collection<Passion> process(final Document doc) {
        final Collection<Passion> passions;
        final PendragonFactory factory;
        Passion passion;
        String name;
        String descriptor;

        factory = PendragonFactory.getInstance();
        passions = new LinkedList<>();
        for (final Element node : doc.getRootElement().getChildren()) {
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
