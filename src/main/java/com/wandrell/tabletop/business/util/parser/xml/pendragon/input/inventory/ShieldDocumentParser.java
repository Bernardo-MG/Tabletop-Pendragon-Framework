package com.wandrell.tabletop.business.util.parser.xml.pendragon.input.inventory;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.business.conf.pendragon.ModelXMLConf;
import com.wandrell.tabletop.business.model.pendragon.inventory.Shield;
import com.wandrell.tabletop.business.service.pendragon.ModelService;

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
