package com.wandrell.tabletop.pendragon.util.parser.inventory;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.conf.ModelXMLConf;
import com.wandrell.tabletop.pendragon.model.inventory.Armor;
import com.wandrell.tabletop.pendragon.model.inventory.ArmorType;
import com.wandrell.tabletop.pendragon.service.ModelService;

public final class ArmorDocumentParser implements Parser<Document, Armor> {

    private final ModelService modelService;

    public ArmorDocumentParser(final ModelService service) {
        super();

        modelService = service;
    }

    @Override
    public final Armor parse(final Document doc) {
        final Element root;
        final String name;
        final ArmorType type;
        final Boolean heavy;
        final Integer armorValue;
        final Integer dexModifier;

        root = doc.getRootElement();

        name = root.getChildText(ModelXMLConf.NAME);

        type = ArmorType.valueOf(root.getChildText(ModelXMLConf.TYPE)
                .toUpperCase());

        heavy = Boolean.valueOf(root.getChildText(ModelXMLConf.HEAVY));

        armorValue = Integer.valueOf(root
                .getChildText(ModelXMLConf.ARMOR_VALUE));

        dexModifier = Integer.valueOf(root
                .getChildText(ModelXMLConf.DEXTERITY_MODIFIER));

        return getModelService().getArmor(name, "",
                getModelService().getMoney(0, 0), type, armorValue,
                dexModifier, heavy);
    }

    private final ModelService getModelService() {
        return modelService;
    }

}
