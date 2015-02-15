package com.wandrell.tabletop.business.util.parser.xml.pendragon.output;

import org.jdom2.Document;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.business.model.interval.IntervalTable;

public class RollTableDocumentOutputProcessor implements
        Parser<IntervalTable<String>, Document> {

    public RollTableDocumentOutputProcessor() {
        super();
    }

    @Override
    public final Document parse(final IntervalTable<String> holder) {
        return null;
    }

}
