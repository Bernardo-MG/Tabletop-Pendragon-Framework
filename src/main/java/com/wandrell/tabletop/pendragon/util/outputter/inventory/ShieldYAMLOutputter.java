package com.wandrell.tabletop.pendragon.util.outputter.inventory;

import java.util.LinkedHashMap;
import java.util.Map;

import com.wandrell.tabletop.pendragon.model.inventory.Shield;
import com.wandrell.tabletop.pendragon.util.outputter.YAMLOutputter;

public final class ShieldYAMLOutputter extends YAMLOutputter<Shield> {

    public ShieldYAMLOutputter() {
        super();
    }

    @Override
    protected final Map<String, Object> buildMap(final Shield value) {
        final Map<String, Object> data;

        data = new LinkedHashMap<String, Object>();
        data.put("name", value.getName());
        data.put("armor_value", value.getArmorValue());

        return data;
    }

}
