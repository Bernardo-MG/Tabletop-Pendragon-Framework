package com.wandrell.tabletop.business.util.parser.xml.pendragon.input;

import org.jdom2.Document;

import com.wandrell.tabletop.business.model.pendragon.inventory.Armor;

public class ArmorDocumentInputProcessor extends
        AbstractEquipableItemDocumentInputProcessor<Armor> {

    public ArmorDocumentInputProcessor() {
        super();
    }

    @Override
    public final Armor process(final Document doc) {
        return super.process(doc);
    }

}
