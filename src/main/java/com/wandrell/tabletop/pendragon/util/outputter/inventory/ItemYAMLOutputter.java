package com.wandrell.tabletop.pendragon.util.outputter.inventory;

import java.util.LinkedHashMap;
import java.util.Map;

import com.wandrell.tabletop.pendragon.model.inventory.Item;
import com.wandrell.tabletop.pendragon.util.outputter.YAMLOutputter;

public final class ItemYAMLOutputter extends YAMLOutputter<Item> {

    public ItemYAMLOutputter() {
        super();
    }

    @Override
    protected final Map<String, Object> buildMap(final Item value) {
        final Map<String, Object> data;

        data = new LinkedHashMap<String, Object>();
        data.put("name", value.getName());
        data.put("description", value.getDescription());

        return data;
    }

}
