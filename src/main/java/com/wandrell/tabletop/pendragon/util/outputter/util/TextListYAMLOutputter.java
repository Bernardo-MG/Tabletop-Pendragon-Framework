package com.wandrell.tabletop.pendragon.util.outputter.util;

import java.util.LinkedHashMap;
import java.util.Map;

import com.wandrell.tabletop.pendragon.model.util.TextList;
import com.wandrell.tabletop.pendragon.util.outputter.YAMLOutputter;

public final class TextListYAMLOutputter extends YAMLOutputter<TextList> {

    public TextListYAMLOutputter() {
        super();
    }

    @Override
    protected final Map<String, Object> buildMap(final TextList value) {
        final Map<String, Object> data;

        data = new LinkedHashMap<String, Object>();

        data.put("name", value.getName());
        data.put("values", value.getValues());

        return data;
    }

}
