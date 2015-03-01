package com.wandrell.tabletop.testing.pendragon.framework.framework.util.parser;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import org.jdom2.Document;
import org.jdom2.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wandrell.pattern.parser.Parser;

public final class HitPointsParser implements
        Parser<Document, Collection<Collection<Object>>> {

    private static final Logger logger = LoggerFactory
                                               .getLogger(HitPointsParser.class);

    private static final Logger getLogger() {
        return logger;
    }

    public HitPointsParser() {
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
        final Integer size;
        final Integer result;

        data = new LinkedList<Object>();

        size = Integer.parseInt(node.getChildText("size"));
        constitution = Integer.parseInt(node.getChildText("constitution"));
        result = Integer.parseInt(node.getChildText("result"));

        data.add(constitution);
        data.add(size);
        data.add(result);

        getLogger().debug(
                String.format("Read size %d, constitution %d and result %d",
                        size, constitution, result));

        return data;
    }
}
