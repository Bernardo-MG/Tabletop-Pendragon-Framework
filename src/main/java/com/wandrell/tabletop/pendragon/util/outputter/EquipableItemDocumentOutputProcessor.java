package com.wandrell.tabletop.pendragon.util.outputter;

import org.jdom2.Document;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.model.inventory.Item;

public final class EquipableItemDocumentOutputProcessor implements
        Parser<Item, Document> {

    public EquipableItemDocumentOutputProcessor() {
        super();
    }

    @Override
    public final Document parse(final Item holder) {
        return null;
    }

}
