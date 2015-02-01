package com.wandrell.tabletop.business.util.parser.xml.pendragon.output;

import org.jdom2.Document;

import com.wandrell.tabletop.business.model.pendragon.inventory.Armor;
import com.wandrell.util.parser.xml.output.JDOMDocumentEncoder;

public class ArmorDocumentOutputProcessor implements JDOMDocumentEncoder<Armor> {

    public ArmorDocumentOutputProcessor() {
        super();
    }

    @Override
    public final Document encode(final Armor holder) {
        return null;
    }

}
