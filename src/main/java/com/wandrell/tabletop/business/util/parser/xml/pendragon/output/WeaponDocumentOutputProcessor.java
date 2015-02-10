package com.wandrell.tabletop.business.util.parser.xml.pendragon.output;

import org.jdom2.Document;

import com.wandrell.pattern.parser.xml.output.JDOMDocumentEncoder;
import com.wandrell.tabletop.business.model.pendragon.inventory.Weapon;

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
