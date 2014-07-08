package com.wandrell.tabletop.pendragon.framework.persistence.xml;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.pendragon.framework.conf.FileLabels;
import com.wandrell.tabletop.pendragon.inventory.PendragonEquipment;
import com.wandrell.tabletop.pendragon.inventory.WeaponEquipment;
import com.wandrell.util.file.api.xml.XMLDocumentWriter;

public class WeaponXMLDocumentWriter implements
	XMLDocumentWriter<WeaponEquipment> {

    private final XMLDocumentWriter<PendragonEquipment> builder = new EquipableItemXMLDocumenWriter();

    public WeaponXMLDocumentWriter() {
	super();
    }

    @Override
    public final Document getDocument(final WeaponEquipment holder) {
	final Document doc;
	Element node;

	doc = builder.getDocument(holder);

	node = new Element(FileLabels.SKILL);
	node.setText(holder.getSkill());

	doc.getRootElement().setName(FileLabels.WEAPON);

	return doc;
    }

}
