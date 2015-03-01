package com.wandrell.tabletop.testing.pendragon.framework.framework.util.parser;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import org.jdom2.Document;
import org.jdom2.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wandrell.pattern.parser.Parser;

public final class HealingRateParser implements
        Parser<Document, Collection<Collection<Object>>> {

    private static final Logger logger = LoggerFactory
                                               .getLogger(HealingRateParser.class);

    private static final Logger getLogger() {
        return logger;
    }

    public HealingRateParser() {
        super();
    }

    @Override
    public final Collection<Collection<Object>> parse(final Document doc) {
        final Collection<Collection<Object>> colData;

        colData = new LinkedHashSet<Collection<Object>>();
        for (final Element node : doc.getRootElement().getChildren()) {
            colData.add(readNode(node));
        }

        return colData;
    }

    private final Collection<Object> readNode(final Element node) {
        final Collection<Object> data;
        final Integer constitution;
        final Integer strength;
        final Integer result;

        data = new LinkedList<Object>();

        constitution = Integer.parseInt(node.getChildText("constitution"));
        strength = Integer.parseInt(node.getChildText("strength"));
        result = Integer.parseInt(node.getChildText("result"));

        data.add(constitution);
        data.add(strength);
        data.add(result);

        getLogger().debug(
                String.format(
                        "Read strength %d, constitution %d and result %d",
                        strength, constitution, result));

        return data;
    }
}
