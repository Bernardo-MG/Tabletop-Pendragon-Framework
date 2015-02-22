package com.wandrell.tabletop.pendragon.util.outputter.chargen;

import java.util.LinkedHashMap;
import java.util.Map;

import com.wandrell.tabletop.pendragon.model.glory.FatherClassGlory;
import com.wandrell.tabletop.pendragon.util.outputter.YAMLOutputter;

public final class FatherClassGloryYAMLOutputter extends
        YAMLOutputter<FatherClassGlory> {

    public FatherClassGloryYAMLOutputter() {
        super();
    }

    @Override
    protected final Map<String, Object> buildMap(final FatherClassGlory glory) {
        final Map<String, Object> data;

        data = new LinkedHashMap<String, Object>();

        data.put("father_class", glory.getFatherClass());
        data.put("base_glory", glory.getBaseGlory());
        data.put("yearly_glory", glory.getYearlyGlory());

        return data;
    }

}
