package com.wandrell.tabletop.pendragon.framework.util.file.persistence;

import org.jdom2.Document;

import com.wandrell.tabletop.pendragon.inventory.Armor;

public class ArmorXMLDocumentReader extends
	AbstractEquipableItemXMLDocumentReader<Armor> {

    public ArmorXMLDocumentReader() {
	super();
    }

    @Override
    public final Armor getValue(final Document doc) {
	return super.getValue(doc);
    }

}
