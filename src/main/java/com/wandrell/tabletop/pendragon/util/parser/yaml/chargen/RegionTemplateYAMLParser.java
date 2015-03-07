package com.wandrell.tabletop.pendragon.util.parser.yaml.chargen;

import java.io.Reader;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.model.chargen.region.RegionTemplate;
import com.wandrell.tabletop.pendragon.service.model.ModelConstructorService;

public final class RegionTemplateYAMLParser implements
        Parser<Reader, RegionTemplate> {

    private final ModelConstructorService modelService;

    public RegionTemplateYAMLParser(final ModelConstructorService service) {
        super();

        modelService = service;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final RegionTemplate parse(final Reader reader) {
        final Yaml yaml;
        final Map<String, Object> values;
        final Map<String, Collection<Map<String, Object>>> bonus;
        final String name;
        final Map<String, Integer> traits;

        yaml = new Yaml();

        values = (Map<String, Object>) yaml.load(reader);

        // Name
        name = (String) values.get("name");

        bonus = (Map<String, Collection<Map<String, Object>>>) values
                .get("bonus");

        traits = new LinkedHashMap<String, Integer>();

        if (bonus != null) {
            // Traits
            if (bonus.containsKey("traits")) {
                for (final Map<String, Object> trait : bonus.get("traits")) {
                    traits.put((String) trait.get("name"),
                            (Integer) trait.get("value"));
                }
            }
        }

        return getModelService().getRegionTemplate(name, traits);
    }

    private final ModelConstructorService getModelService() {
        return modelService;
    }

}
