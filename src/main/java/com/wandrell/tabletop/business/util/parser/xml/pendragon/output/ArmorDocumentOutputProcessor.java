package com.wandrell.tabletop.business.util.parser.xml.pendragon.output;

import org.jdom2.Document;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.business.model.pendragon.inventory.Armor;

public class ArmorDocumentOutputProcessor implements Parser<Armor, Document> {

    public ArmorDocumentOutputProcessor() {
        super();
    }

    @Override
    public final Document parse(final Armor holder) {
        return null;
    }

}
