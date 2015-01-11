package com.wandrell.tabletop.business.util.parser.xml.pendragon.output;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.business.conf.pendragon.FileToken;
import com.wandrell.tabletop.business.model.pendragon.inventory.Item;
import com.wandrell.util.parser.xml.output.JDOMDocumentOutputProcessor;

public class EquipableItemDocumentOutputProcessor implements
        JDOMDocumentOutputProcessor<Item> {

    public EquipableItemDocumentOutputProcessor() {
        super();
    }

    @SuppressWarnings("null")
    @Override
    public final Document process(final Item holder) {
        final Document doc;
        Element node;

        // TODO
        doc = null;
        // doc = new Document(PersistenceFactory.getItemService()
        // .buildItemXMLNode(holder));

        // Removes quantity count
        doc.getRootElement().removeChild(FileToken.QUANTITY);

        // Removes money value count
        doc.getRootElement().removeChild(FileToken.MONEY);

        // Values
        node = null;
        // TODO
        // node = XMLUtils.buildIntegerValueHandlerXMLTree(new Element(
        // FileLabels.VALUE_HANDLERS), holder.getValuesIterator(),
        // FileLabels.VALUE_HANDLER);
        if (node.getChildren().size() > 0) {
            doc.getRootElement().addContent(node);
        }

        // Flags
        // TODO
        // node = XMLUtils.buildBooleansSetXMLTree(new
        // Element(FileLabels.FLAGS),
        // holder.getFlagsIterator());
        // if (node.getChildren().size() > 0) {
        // doc.getRootElement().addContent(node);
        // }

        // Bonus
        // TODO
        // node = PersistenceFactory.getPendragonTemplateService()
        // .buildPendragonTemplateXMLNode(holder.getBonusValuesTemplate(),
        // new Element(FileLabels.TEMPLATE_BONUS));
        if (node.getChildren().size() > 0) {
            doc.getRootElement().addContent(node);
        }

        return doc;
    }

}
