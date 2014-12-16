package com.wandrell.tabletop.business.util.parser.xml.pendragon.output;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.business.conf.pendragon.FileToken;
import com.wandrell.tabletop.business.model.pendragon.chargen.HomelandBonus;
import com.wandrell.util.parser.xml.output.JDOMDocumentOutputProcessor;

public class HomelandTemplateDocumentOutputProcessor implements
        JDOMDocumentOutputProcessor<HomelandBonus> {

    public HomelandTemplateDocumentOutputProcessor() {
        super();
    }

    @Override
    public final Document process(final HomelandBonus holder) {
        final Document doc;
        Element node;

        // Main body and name of the template
        node = new Element(FileToken.HOMELAND);
        // node.setAttribute(FileStreamerTags.NAME, holder.getName());
        doc = new Document(node);

        // TODO
        // PersistenceFactory.getTemplatesContainerService().addToXMLDocument(doc,
        // holder);

        return doc;
    }

}
