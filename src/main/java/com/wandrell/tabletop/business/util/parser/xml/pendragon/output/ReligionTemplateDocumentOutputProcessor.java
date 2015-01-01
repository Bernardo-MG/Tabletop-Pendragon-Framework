package com.wandrell.tabletop.business.util.parser.xml.pendragon.output;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.business.conf.pendragon.FileToken;
import com.wandrell.tabletop.business.model.pendragon.chargen.ReligionTemplate;
import com.wandrell.util.parser.xml.output.JDOMDocumentOutputProcessor;

public class ReligionTemplateDocumentOutputProcessor implements
        JDOMDocumentOutputProcessor<ReligionTemplate> {

    public ReligionTemplateDocumentOutputProcessor() {
        super();
    }

    @Override
    public final Document process(final ReligionTemplate holder) {
        final Document doc;
        Element node;

        // Root and religion name
        node = new Element(FileToken.RELIGION);
        // node.setAttribute(FileStreamerTags.NAME, holder.getName());
        doc = new Document(node);

        // Religious traits
        // TODO
        // doc.getRootElement().addContent(
        // XMLUtils.buildStringListXMLTree(new Element(FileLabels.TRAITS),
        // holder.getReligiousTraits(), FileLabels.TRAIT));

        // Religious bonus
        // TODO
        // node = PersistenceFactory.getPendragonTemplateService()
        // .buildPendragonTemplateXMLNode(holder.getBonusTemplate(),
        // new Element(FileLabels.TEMPLATE_BONUS));
        if (node.getChildren().size() > 0) {
            doc.getRootElement().addContent(node);
        }

        return doc;
    }

}
