package com.wandrell.tabletop.pendragon.framework.util.file.persistence;

import org.jdom2.Document;

import com.wandrell.tabletop.pendragon.inventory.Shield;

public final class ShieldXMLDocumentReader extends
        AbstractEquipableItemXMLDocumentReader<Shield> {

    public ShieldXMLDocumentReader() {
        super();
    }

    @Override
    public final Shield getValue(final Document doc) {
        return super.getValue(doc);
    }

}
