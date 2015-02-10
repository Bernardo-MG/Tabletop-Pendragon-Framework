package com.wandrell.tabletop.business.util.parser.xml.pendragon.input.inventory;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.pattern.parser.xml.input.JDOMDocumentDecoder;
import com.wandrell.tabletop.business.conf.pendragon.ModelXMLConf;
import com.wandrell.tabletop.business.model.pendragon.inventory.Armor;
import com.wandrell.tabletop.business.model.pendragon.inventory.ArmorType;
import com.wandrell.tabletop.business.service.pendragon.ModelService;

public final class ArmorDocumentDecoder implements JDOMDocumentDecoder<Armor> {

    private final ModelService modelService;

    public ArmorDocumentDecoder(final ModelService service) {
        super();

        modelService = service;
    }

    @Override
    public final Armor decode(final Document doc) {
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
