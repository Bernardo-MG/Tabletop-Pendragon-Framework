package com.wandrell.tabletop.pendragon.util.outputter;

import org.jdom2.Document;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.interval.IntervalTable;

public final class RollTableDocumentOutputProcessor implements
        Parser<IntervalTable<String>, Document> {

    public RollTableDocumentOutputProcessor() {
        super();
    }

    @Override
    public final Document parse(final IntervalTable<String> holder) {
        return null;
    }

}
