package com.wandrell.tabletop.pendragon.util.outputter;

import org.jdom2.Document;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.model.inventory.Armor;

public class ArmorDocumentOutputProcessor implements Parser<Armor, Document> {

    public ArmorDocumentOutputProcessor() {
        super();
    }

    @Override
    public final Document parse(final Armor holder) {
        return null;
    }

}
