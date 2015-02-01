package com.wandrell.tabletop.business.util.parser.xml.pendragon.output;

import org.jdom2.Document;

import com.wandrell.tabletop.business.model.interval.IntervalTable;
import com.wandrell.util.parser.xml.output.JDOMDocumentEncoder;

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
