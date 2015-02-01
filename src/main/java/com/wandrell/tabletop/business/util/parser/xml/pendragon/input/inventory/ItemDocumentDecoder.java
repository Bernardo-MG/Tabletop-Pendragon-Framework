package com.wandrell.tabletop.business.util.parser.xml.pendragon.input.inventory;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.business.conf.pendragon.ModelXMLConf;
import com.wandrell.tabletop.business.model.pendragon.inventory.Item;
import com.wandrell.tabletop.business.service.pendragon.ModelService;
import com.wandrell.util.parser.xml.input.JDOMDocumentDecoder;

public final class ItemDocumentDecoder implements JDOMDocumentDecoder<Item> {

    private final ModelService modelService;

    public ItemDocumentDecoder(final ModelService service) {
        super();

        modelService = service;
    }

    @Override
    public final Item decode(final Document doc) {
        final Element root;
        final String name;
        final String description;

        root = doc.getRootElement();

        name = root.getChildText(ModelXMLConf.NAME);
        description = root.getChildText(ModelXMLConf.DESCRIPTION);

        return getModelService().getItem(name, description);
    }

    private final ModelService getModelService() {
        return modelService;
    }

}
