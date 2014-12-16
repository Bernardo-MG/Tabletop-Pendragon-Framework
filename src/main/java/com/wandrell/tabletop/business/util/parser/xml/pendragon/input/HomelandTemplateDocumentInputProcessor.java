package com.wandrell.tabletop.business.util.parser.xml.pendragon.input;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.business.model.pendragon.chargen.HomelandBonus;
import com.wandrell.util.parser.xml.input.JDOMDocumentInputProcessor;

public class HomelandTemplateDocumentInputProcessor implements
        JDOMDocumentInputProcessor<HomelandBonus> {

    public HomelandTemplateDocumentInputProcessor() {
        super();
    }

    @Override
    public final HomelandBonus process(final Document doc) {
        final HomelandBonus holder;
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
