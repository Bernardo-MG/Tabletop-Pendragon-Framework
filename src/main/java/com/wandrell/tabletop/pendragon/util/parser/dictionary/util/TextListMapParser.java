package com.wandrell.tabletop.pendragon.util.parser.dictionary.util;

import java.util.LinkedHashMap;
import java.util.Map;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.model.util.TextList;

public final class TextListMapParser implements
        Parser<TextList, Map<String, Object>> {

    public TextListMapParser() {
        super();
    }

    @Override
    public final Map<String, Object> parse(final TextList value) {
        final Map<String, Object> data;

        data = new LinkedHashMap<String, Object>();

        data.put("name", value.getName());
        data.put("values", value.getValues());

        return data;
    }

}
