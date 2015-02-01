package com.wandrell.tabletop.business.util.parser.xml.pendragon.output;

import org.jdom2.Document;

import com.wandrell.tabletop.business.model.pendragon.inventory.Shield;
import com.wandrell.util.parser.xml.output.JDOMDocumentEncoder;

public class ShieldDocumentOutputProcessor implements
        JDOMDocumentEncoder<Shield> {

    public ShieldDocumentOutputProcessor() {
        super();
    }

    @Override
    public final Document encode(final Shield holder) {
        return null;
    }

}
