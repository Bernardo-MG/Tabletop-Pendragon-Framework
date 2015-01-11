package com.wandrell.tabletop.business.util.parser.xml.pendragon.output;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.business.conf.pendragon.FileToken;
import com.wandrell.tabletop.business.model.pendragon.inventory.Item;
import com.wandrell.tabletop.business.model.pendragon.inventory.Weapon;
import com.wandrell.util.parser.xml.output.JDOMDocumentOutputProcessor;

public class WeaponDocumentOutputProcessor implements
        JDOMDocumentOutputProcessor<Weapon> {

    private final JDOMDocumentOutputProcessor<Item> builder = new EquipableItemDocumentOutputProcessor();

    public WeaponDocumentOutputProcessor() {
        super();
    }

    @Override
    public final Document process(final Weapon holder) {
        final Document doc;
        Element node;

        doc = builder.process(holder);

        node = new Element(FileToken.SKILL);
        node.setText(holder.getSkill());

        doc.getRootElement().setName(FileToken.WEAPON);

        return doc;
    }

}
