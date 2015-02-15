package com.wandrell.tabletop.business.util.parser.xml.pendragon.output;

import org.jdom2.Document;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.business.model.pendragon.inventory.Item;

public class EquipableItemDocumentOutputProcessor implements
        Parser<Item, Document> {

    public EquipableItemDocumentOutputProcessor() {
        super();
    }

    @Override
    public final Document parse(final Item holder) {
        return null;
    }

}
