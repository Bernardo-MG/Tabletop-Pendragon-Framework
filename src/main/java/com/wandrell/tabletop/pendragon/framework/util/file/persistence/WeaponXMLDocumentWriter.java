package com.wandrell.tabletop.pendragon.framework.util.file.persistence;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.pendragon.framework.conf.FileToken;
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

	node = new Element(FileToken.SKILL);
	node.setText(holder.getSkill());

	doc.getRootElement().setName(FileToken.WEAPON);

	return doc;
    }

}
