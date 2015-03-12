package com.wandrell.tabletop.pendragon.util.parser.dictionary.inventory;

import java.util.LinkedHashMap;
import java.util.Map;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.model.inventory.armor.Armor;

public final class ArmorMapParser implements Parser<Armor, Map<String, Object>> {

    public ArmorMapParser() {
        super();
    }

    @Override
    public final Map<String, Object> parse(final Armor value) {
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
