package com.wandrell.tabletop.pendragon.util.parser.yaml.inventory;

import java.io.Reader;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.model.inventory.Armor;
import com.wandrell.tabletop.pendragon.model.inventory.ArmorType;
import com.wandrell.tabletop.pendragon.service.model.ModelService;

public final class ArmorYAMLParser implements Parser<Reader, Armor> {

    private final ModelService modelService;

    public ArmorYAMLParser(final ModelService service) {
        super();

        modelService = service;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final Armor parse(final Reader reader) {
        final Yaml yaml;
        final Map<String, Object> values;
        final String name;
        final ArmorType type;
        final Boolean heavy;
        final Integer armorValue;
        final Integer dexModifier;

        yaml = new Yaml();

        values = (Map<String, Object>) yaml.load(reader);

        name = (String) values.get("name");

        type = ArmorType.valueOf(((String) values.get("type")).toUpperCase());

        heavy = (Boolean) values.get("heavy");

        armorValue = (Integer) values.get("armor_value");

        dexModifier = (Integer) values.get("dexterity_modifier");

        return getModelService().getArmor(name, "",
                getModelService().getMoney(0, 0), type, armorValue,
                dexModifier, heavy);
    }

    private final ModelService getModelService() {
        return modelService;
    }

}
