package com.wandrell.tabletop.pendragon.util.parser.yaml.inventory;

import java.io.Reader;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.model.inventory.Item;
import com.wandrell.tabletop.pendragon.service.model.ModelConstructorService;

public final class ItemYAMLParser implements Parser<Reader, Item> {

    private final ModelConstructorService modelService;

    public ItemYAMLParser(final ModelConstructorService service) {
        super();

        modelService = service;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final Item parse(final Reader reader) {
        final Yaml yaml;
        final Map<String, Object> values;
        final String name;
        final String description;

        yaml = new Yaml();

        values = (Map<String, Object>) yaml.load(reader);

        name = (String) values.get("name");
        description = (String) values.get("description");

        return getModelService().getItem(name, description);
    }

    private final ModelConstructorService getModelService() {
        return modelService;
    }

}
