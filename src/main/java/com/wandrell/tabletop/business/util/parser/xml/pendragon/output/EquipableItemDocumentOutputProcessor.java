package com.wandrell.tabletop.business.util.parser.xml.pendragon.output;

import org.jdom2.Document;

import com.wandrell.tabletop.business.model.pendragon.inventory.Item;
import com.wandrell.util.parser.xml.output.JDOMDocumentEncoder;

public class EquipableItemDocumentOutputProcessor implements
        JDOMDocumentEncoder<Item> {

    public EquipableItemDocumentOutputProcessor() {
        super();
    }

    @Override
    public final Document encode(final Item holder) {
        return null;
    }

}
