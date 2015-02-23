package com.wandrell.tabletop.pendragon.util.outputter.inventory;

import java.util.LinkedHashMap;
import java.util.Map;

import com.wandrell.tabletop.pendragon.model.inventory.Armor;
import com.wandrell.tabletop.pendragon.util.outputter.YAMLOutputter;

public final class ArmorYAMLOutputter extends YAMLOutputter<Armor> {

    public ArmorYAMLOutputter() {
        super();
    }

    @Override
    protected final Map<String, Object> buildMap(final Armor value) {
        final Map<String, Object> data;

        data = new LinkedHashMap<String, Object>();

        data.put("name", value.getName());
        data.put("type", value.getArmorType().toString().toLowerCase());
        data.put("heavy", value.isHeavyLoad());
        data.put("armor_value", value.getArmorValue());
        data.put("dexterity_modifier", value.getDexterityModifier());

        return data;
    }

}
