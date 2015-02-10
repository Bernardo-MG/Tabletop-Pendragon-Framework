package com.wandrell.tabletop.business.util.parser.xml.pendragon.output;

import org.jdom2.Document;

import com.wandrell.pattern.parser.xml.output.JDOMDocumentEncoder;
import com.wandrell.tabletop.business.model.pendragon.inventory.Shield;

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
