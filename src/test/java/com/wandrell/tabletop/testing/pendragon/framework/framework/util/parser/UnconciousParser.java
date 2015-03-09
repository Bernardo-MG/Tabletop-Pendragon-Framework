package com.wandrell.tabletop.testing.pendragon.framework.framework.util.parser;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import org.jdom2.Document;
import org.jdom2.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wandrell.pattern.parser.Parser;

public final class UnconciousParser implements
        Parser<Document, Collection<Collection<Object>>> {

    private static final Logger logger = LoggerFactory
                                               .getLogger(UnconciousParser.class);

    private static final Logger getLogger() {
        return logger;
    }

    public UnconciousParser() {
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
        final Integer hitpoints;
        final Integer result;

        data = new LinkedList<Object>();

        hitpoints = Integer.parseInt(node.getChildText("hitpoints"));
        result = Integer.parseInt(node.getChildText("result"));

        data.add(hitpoints);
        data.add(result);

        getLogger().debug(
                String.format("Read hitpoints %d and result %d", hitpoints,
                        result));

        return data;
    }
}
