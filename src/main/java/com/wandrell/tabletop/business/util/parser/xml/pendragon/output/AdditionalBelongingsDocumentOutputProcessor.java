package com.wandrell.tabletop.business.util.parser.xml.pendragon.output;

import org.jdom2.Document;

import com.wandrell.tabletop.business.model.interval.IntervalTable;
import com.wandrell.tabletop.business.model.pendragon.chargen.AdditionalBelongings;
import com.wandrell.util.parser.xml.output.JDOMDocumentEncoder;

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
