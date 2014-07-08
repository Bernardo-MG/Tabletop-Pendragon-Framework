package com.wandrell.tabletop.pendragon.framework.persistence.xml;

import org.jdom2.Document;

import com.wandrell.tabletop.pendragon.framework.conf.FileLabels;
import com.wandrell.tabletop.pendragon.inventory.PendragonEquipment;
import com.wandrell.tabletop.pendragon.inventory.ShieldEquipment;
import com.wandrell.util.file.api.xml.XMLDocumentWriter;

public class ShieldXMLDocumentWriter implements
	XMLDocumentWriter<ShieldEquipment> {

    private final XMLDocumentWriter<PendragonEquipment> builder = new EquipableItemXMLDocumenWriter();

    public ShieldXMLDocumentWriter() {
	super();
    }

    @Override
    public final Document getDocument(final ShieldEquipment holder) {
	final Document doc;

	doc = builder.getDocument(holder);

	doc.getRootElement().setName(FileLabels.SHIELD);

	return doc;
    }

}
