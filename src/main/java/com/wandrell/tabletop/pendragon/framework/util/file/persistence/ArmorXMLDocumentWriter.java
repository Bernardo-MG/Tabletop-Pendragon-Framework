package com.wandrell.tabletop.pendragon.framework.util.file.persistence;

import org.jdom2.Document;

import com.wandrell.tabletop.pendragon.framework.conf.FileToken;
import com.wandrell.tabletop.pendragon.inventory.Armor;
import com.wandrell.tabletop.pendragon.inventory.Equipment;
import com.wandrell.util.file.api.xml.XMLDocumentWriter;

public class ArmorXMLDocumentWriter implements XMLDocumentWriter<Armor> {

    private final XMLDocumentWriter<Equipment> builder = new EquipableItemXMLDocumenWriter();

    public ArmorXMLDocumentWriter() {
        super();
    }

    @Override
    public final Document getDocument(final Armor holder) {
        final Document doc;

        doc = builder.getDocument(holder);

        doc.getRootElement().setName(FileToken.ARMOR);

        return doc;
    }

}
