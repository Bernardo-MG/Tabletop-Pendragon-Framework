package com.wandrell.tabletop.pendragon.framework.util.file.persistence;

import org.jdom2.Document;

import com.wandrell.tabletop.pendragon.inventory.ArmorEquipment;

public class ArmorXMLDocumentReader extends
	AbstractEquipableItemXMLDocumentReader<ArmorEquipment> {

    public ArmorXMLDocumentReader() {
	super();
    }

    @Override
    public final ArmorEquipment getValue(final Document doc) {
	return super.getValue(doc);
    }

}
