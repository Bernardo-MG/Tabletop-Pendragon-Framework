package com.wandrell.tabletop.business.util.parser.xml.pendragon.output;

import org.jdom2.Document;

import com.wandrell.pattern.parser.xml.output.JDOMDocumentEncoder;
import com.wandrell.tabletop.business.model.pendragon.inventory.Item;

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
