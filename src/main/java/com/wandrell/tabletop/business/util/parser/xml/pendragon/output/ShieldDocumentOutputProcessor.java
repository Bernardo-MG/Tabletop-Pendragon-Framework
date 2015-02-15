package com.wandrell.tabletop.business.util.parser.xml.pendragon.output;

import org.jdom2.Document;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.business.model.pendragon.inventory.Shield;

public class ShieldDocumentOutputProcessor implements Parser<Shield, Document> {

    public ShieldDocumentOutputProcessor() {
        super();
    }

    @Override
    public final Document parse(final Shield holder) {
        return null;
    }

}
