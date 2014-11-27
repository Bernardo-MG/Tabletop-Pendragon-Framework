package com.wandrell.tabletop.business.util.parser.xml.pendragon.input;

import org.jdom2.Document;

import com.wandrell.tabletop.business.model.pendragon.inventory.Shield;

public final class ShieldDocumentInputProcessor extends
        AbstractEquipableItemDocumentInputProcessor<Shield> {

    public ShieldDocumentInputProcessor() {
        super();
    }

    @Override
    public final Shield process(final Document doc) {
        return super.process(doc);
    }

}
