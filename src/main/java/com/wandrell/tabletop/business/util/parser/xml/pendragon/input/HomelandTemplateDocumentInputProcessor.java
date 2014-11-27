package com.wandrell.tabletop.business.util.parser.xml.pendragon.input;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.business.model.pendragon.character.background.Homeland;
import com.wandrell.util.parser.xml.input.JDOMDocumentInputProcessor;

public class HomelandTemplateDocumentInputProcessor implements
        JDOMDocumentInputProcessor<Homeland> {

    public HomelandTemplateDocumentInputProcessor() {
        super();
    }

    @Override
    public final Homeland process(final Document doc) {
        final Homeland holder;
        final Element root;

        root = doc.getRootElement();

        // Homeland's name
        // holder = new DefaultHomeland(
        // root.getAttributeValue(FileStreamerTags.NAME));

        // TODO
        // PersistenceFactory.getTemplatesContainerService().readXMLTree(root,
        // holder);

        return null;
    }

}
