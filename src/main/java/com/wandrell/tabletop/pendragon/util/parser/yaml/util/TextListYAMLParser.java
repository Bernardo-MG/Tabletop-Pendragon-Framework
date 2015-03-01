package com.wandrell.tabletop.pendragon.util.parser.yaml.util;

import java.io.Reader;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.model.util.TextList;
import com.wandrell.tabletop.pendragon.service.model.ModelService;

public final class TextListYAMLParser implements Parser<Reader, TextList> {

    private final ModelService modelService;

    public TextListYAMLParser(final ModelService service) {
        super();

        modelService = service;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final TextList parse(final Reader reader) {
        final Yaml yaml;
        final Map<String, Object> values;
        final String name;
        final Collection<String> texts;

        yaml = new Yaml();

        values = (Map<String, Object>) yaml.load(reader);

        // Name
        name = (String) values.get("name");

        if ((values.containsKey("values")) && (values.get("values") != null)) {
            texts = (Collection<String>) values.get("values");
        } else {
            texts = new LinkedList<>();
        }

        return getModelService().getTextList(name, texts);
    }

    private final ModelService getModelService() {
        return modelService;
    }

}
