package com.wandrell.tabletop.business.util.parser.xml.pendragon.output;

import org.jdom2.Document;

import com.wandrell.pattern.parser.xml.output.JDOMDocumentEncoder;
import com.wandrell.tabletop.business.model.interval.IntervalTable;
import com.wandrell.tabletop.business.model.pendragon.chargen.AdditionalBelongings;

public class AdditionalBelongingsDocumentOutputProcessor implements
        JDOMDocumentEncoder<IntervalTable<AdditionalBelongings>> {

    public AdditionalBelongingsDocumentOutputProcessor() {
        super();
    }

    @Override
    public final Document encode(
            final IntervalTable<AdditionalBelongings> holder) {
        return null;
    }

}
