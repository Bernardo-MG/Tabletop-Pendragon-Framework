package com.wandrell.tabletop.pendragon.util.parser.xml.input.inventory;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.conf.ModelXMLConf;
import com.wandrell.tabletop.pendragon.model.inventory.Shield;
import com.wandrell.tabletop.pendragon.service.ModelService;

public final class ShieldDocumentParser implements Parser<Document, Shield> {

    private final ModelService modelService;

    public ShieldDocumentParser(final ModelService service) {
        super();

        modelService = service;
    }

    @Override
    public final Shield parse(final Document doc) {
        final Element root;
        final String name;
        final Integer armorValue;

        root = doc.getRootElement();

        name = root.getChildText(ModelXMLConf.NAME);

        armorValue = Integer.valueOf(root
                .getChildText(ModelXMLConf.ARMOR_VALUE));

        return getModelService().getShield(name, "",
                getModelService().getMoney(0, 0), armorValue);
    }

    private final ModelService getModelService() {
        return modelService;
    }

}
