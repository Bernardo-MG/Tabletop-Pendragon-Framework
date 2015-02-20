package com.wandrell.tabletop.pendragon.util.parser.inventory;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.conf.ModelXMLConf;
import com.wandrell.tabletop.pendragon.model.inventory.Item;
import com.wandrell.tabletop.pendragon.service.ModelService;

public final class ItemDocumentParser implements Parser<Document, Item> {

    private final ModelService modelService;

    public ItemDocumentParser(final ModelService service) {
        super();

        modelService = service;
    }

    @Override
    public final Item parse(final Document doc) {
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
