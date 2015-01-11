package com.wandrell.tabletop.business.util.parser.xml.pendragon.output;

import org.jdom2.Document;

import com.wandrell.tabletop.business.conf.pendragon.FileToken;
import com.wandrell.tabletop.business.model.pendragon.inventory.Item;
import com.wandrell.tabletop.business.model.pendragon.inventory.Shield;
import com.wandrell.util.parser.xml.output.JDOMDocumentOutputProcessor;

public class ShieldDocumentOutputProcessor implements
        JDOMDocumentOutputProcessor<Shield> {

    private final JDOMDocumentOutputProcessor<Item> builder = new EquipableItemDocumentOutputProcessor();

    public ShieldDocumentOutputProcessor() {
        super();
    }

    @Override
    public final Document process(final Shield holder) {
        final Document doc;

        doc = builder.process(holder);

        doc.getRootElement().setName(FileToken.SHIELD);

        return doc;
    }

}
