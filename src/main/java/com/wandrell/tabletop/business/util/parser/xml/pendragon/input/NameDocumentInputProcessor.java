package com.wandrell.tabletop.business.util.parser.xml.pendragon.input;

import java.util.Collection;
import java.util.LinkedList;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.business.conf.pendragon.FileToken;
import com.wandrell.util.parser.xml.input.JDOMDocumentInputProcessor;

public class NameDocumentInputProcessor implements
        JDOMDocumentInputProcessor<Collection<String>> {

    public NameDocumentInputProcessor() {
        super();
    }

    @Override
    public final Collection<String> process(final Document doc) {
        final Collection<String> names;

        names = new LinkedList<>();
        for (final Element node : doc.getRootElement().getChildren()) {
            names.add(node.getChildText(FileToken.NAME));
        }

        return names;
    }

}
