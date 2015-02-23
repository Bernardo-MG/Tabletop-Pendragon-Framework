package com.wandrell.tabletop.pendragon.util.parser.dictionary.inventory;

import java.util.LinkedHashMap;
import java.util.Map;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.model.inventory.Shield;

public final class ShieldMapParser implements
        Parser<Shield, Map<String, Object>> {

    public ShieldMapParser() {
        super();
    }

    @Override
    public final Map<String, Object> parse(final Shield value) {
        final Map<String, Object> data;

        data = new LinkedHashMap<String, Object>();
        data.put("name", value.getName());
        data.put("armor_value", value.getArmorValue());

        return data;
    }

}
