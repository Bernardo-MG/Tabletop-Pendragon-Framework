package com.wandrell.tabletop.pendragon.util.parser.dictionary.chargen;

import java.util.LinkedHashMap;
import java.util.Map;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.model.glory.FatherClassGlory;

public final class FatherClassGloryMapParser implements
        Parser<FatherClassGlory, Map<String, Object>> {

    public FatherClassGloryMapParser() {
        super();
    }

    @Override
    public final Map<String, Object> parse(final FatherClassGlory glory) {
        final Map<String, Object> data;

        data = new LinkedHashMap<String, Object>();

        // Father class
        data.put("father_class", glory.getFatherClass());
        // Base glory
        data.put("base_glory", glory.getBaseGlory());
        // Yearly glory
        data.put("yearly_glory", glory.getYearlyGlory());

        return data;
    }

}
