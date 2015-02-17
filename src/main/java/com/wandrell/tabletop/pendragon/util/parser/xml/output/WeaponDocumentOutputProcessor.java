package com.wandrell.tabletop.pendragon.util.parser.xml.output;

import org.jdom2.Document;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.model.inventory.Weapon;

public class WeaponDocumentOutputProcessor implements Parser<Weapon, Document> {

    public WeaponDocumentOutputProcessor() {
        super();
    }

    @Override
    public final Document parse(final Weapon holder) {
        return null;
    }

}
