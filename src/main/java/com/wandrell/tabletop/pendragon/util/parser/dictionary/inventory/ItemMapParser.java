package com.wandrell.tabletop.pendragon.util.parser.dictionary.inventory;

import java.util.LinkedHashMap;
import java.util.Map;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.model.inventory.Item;

public final class ItemMapParser implements Parser<Item, Map<String, Object>> {

    public ItemMapParser() {
        super();
    }

    @Override
    public final Map<String, Object> parse(final Item value) {
        final Map<String, Object> data;

        data = new LinkedHashMap<String, Object>();
        data.put("name", value.getName());
        data.put("description", value.getDescription());

        return data;
    }

}
