package com.wandrell.tabletop.pendragon.util.parser.yaml.chargen;

import java.io.Reader;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.model.glory.FatherClassGlory;
import com.wandrell.tabletop.pendragon.service.ModelService;

public final class FatherClassGloryYAMLParser implements
        Parser<Reader, FatherClassGlory> {

    private final ModelService modelService;

    public FatherClassGloryYAMLParser(final ModelService service) {
        super();

        modelService = service;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final FatherClassGlory parse(final Reader reader) {
        final Yaml yaml;
        final Map<String, Object> values;
        final String name;
        final Integer glory;
        final Integer yearlyGlory;

        yaml = new Yaml();

        values = (Map<String, Object>) yaml.load(reader);

        // Name
        name = (String) values.get("father_class");

        // Glory
        glory = (Integer) values.get("base_glory");
        yearlyGlory = (Integer) values.get("yearly_glory");

        return getModelService().getFatherClassGlory(name, glory, yearlyGlory);
    }

    private final ModelService getModelService() {
        return modelService;
    }

}
