package com.wandrell.tabletop.business.util.parser.xml.pendragon.input.inventory;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.business.conf.pendragon.ModelXMLConf;
import com.wandrell.tabletop.business.model.pendragon.inventory.Shield;
import com.wandrell.tabletop.business.service.pendragon.ModelService;
import com.wandrell.util.parser.xml.input.JDOMDocumentInputProcessor;

public final class ShieldDocumentInputProcessor implements
        JDOMDocumentInputProcessor<Shield> {

    private final ModelService modelService;

    public ShieldDocumentInputProcessor(final ModelService service) {
        super();

        modelService = service;
    }

    @Override
    public final Shield process(final Document doc) {
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
