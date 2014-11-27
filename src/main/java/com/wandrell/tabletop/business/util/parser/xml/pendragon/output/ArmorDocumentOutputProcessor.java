package com.wandrell.tabletop.business.util.parser.xml.pendragon.output;

import org.jdom2.Document;

import com.wandrell.tabletop.business.conf.pendragon.FileToken;
import com.wandrell.tabletop.business.model.pendragon.inventory.Armor;
import com.wandrell.tabletop.business.model.pendragon.inventory.Equipment;
import com.wandrell.util.parser.xml.output.JDOMDocumentOutputProcessor;

public class ArmorDocumentOutputProcessor implements
        JDOMDocumentOutputProcessor<Armor> {

    private final JDOMDocumentOutputProcessor<Equipment> builder = new EquipableItemDocumentOutputProcessor();

    public ArmorDocumentOutputProcessor() {
        super();
    }

    @Override
    public final Document process(final Armor holder) {
        final Document doc;

        doc = builder.process(holder);

        doc.getRootElement().setName(FileToken.ARMOR);

        return doc;
    }

}
