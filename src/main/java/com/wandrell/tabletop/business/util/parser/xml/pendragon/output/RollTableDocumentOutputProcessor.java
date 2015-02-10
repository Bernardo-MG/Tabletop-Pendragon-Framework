package com.wandrell.tabletop.business.util.parser.xml.pendragon.output;

import org.jdom2.Document;

import com.wandrell.pattern.parser.xml.output.JDOMDocumentEncoder;
import com.wandrell.tabletop.business.model.interval.IntervalTable;

public class RollTableDocumentOutputProcessor implements
        JDOMDocumentEncoder<IntervalTable<String>> {

    public RollTableDocumentOutputProcessor() {
        super();
    }

    @Override
    public final Document encode(final IntervalTable<String> holder) {
        return null;
    }

}
