package com.wandrell.tabletop.business.util.parser.xml.pendragon.output;

import org.jdom2.Document;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.business.model.pendragon.inventory.Weapon;

public class WeaponDocumentOutputProcessor implements Parser<Weapon, Document> {

    public WeaponDocumentOutputProcessor() {
        super();
    }

    @Override
    public final Document parse(final Weapon holder) {
        return null;
    }

}
