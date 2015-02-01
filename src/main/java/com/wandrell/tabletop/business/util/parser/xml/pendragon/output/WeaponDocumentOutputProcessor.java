package com.wandrell.tabletop.business.util.parser.xml.pendragon.output;

import org.jdom2.Document;

import com.wandrell.tabletop.business.model.pendragon.inventory.Weapon;
import com.wandrell.util.parser.xml.output.JDOMDocumentEncoder;

public class WeaponDocumentOutputProcessor implements
        JDOMDocumentEncoder<Weapon> {

    public WeaponDocumentOutputProcessor() {
        super();
    }

    @Override
    public final Document encode(final Weapon holder) {
        return null;
    }

}
