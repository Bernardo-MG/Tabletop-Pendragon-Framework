package com.wandrell.tabletop.pendragon.util.parser.yaml.inventory;

import java.io.Reader;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.model.inventory.armor.Shield;
import com.wandrell.tabletop.pendragon.service.model.ModelConstructorService;

public final class ShieldYAMLParser implements Parser<Reader, Shield> {

    private final ModelConstructorService modelService;

    public ShieldYAMLParser(final ModelConstructorService service) {
        super();

        modelService = service;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final Shield parse(final Reader reader) {
        final Yaml yaml;
        final Map<String, Object> values;
        final String name;
        final Integer armorValue;

        yaml = new Yaml();

        values = (Map<String, Object>) yaml.load(reader);

        name = (String) values.get("name");

        armorValue = (Integer) values.get("armor_value");

        return getModelService().getShield(name, "", armorValue);
    }

    private final ModelConstructorService getModelService() {
        return modelService;
    }

}
