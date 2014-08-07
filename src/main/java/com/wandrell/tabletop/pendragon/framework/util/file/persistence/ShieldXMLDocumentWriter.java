package com.wandrell.tabletop.pendragon.framework.util.file.persistence;

import org.jdom2.Document;

import com.wandrell.tabletop.pendragon.framework.conf.FileToken;
import com.wandrell.tabletop.pendragon.inventory.Equipment;
import com.wandrell.tabletop.pendragon.inventory.Shield;
import com.wandrell.util.file.api.xml.XMLDocumentWriter;

public class ShieldXMLDocumentWriter implements XMLDocumentWriter<Shield> {

    private final XMLDocumentWriter<Equipment> builder = new EquipableItemXMLDocumenWriter();

    public ShieldXMLDocumentWriter() {
        super();
    }

    @Override
    public final Document getDocument(final Shield holder) {
        final Document doc;

        doc = builder.getDocument(holder);

        doc.getRootElement().setName(FileToken.SHIELD);

        return doc;
    }

}
