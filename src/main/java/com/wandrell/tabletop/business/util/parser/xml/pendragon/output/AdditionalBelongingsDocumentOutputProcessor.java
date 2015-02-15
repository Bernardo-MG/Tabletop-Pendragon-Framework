package com.wandrell.tabletop.business.util.parser.xml.pendragon.output;

import org.jdom2.Document;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.business.model.interval.IntervalTable;
import com.wandrell.tabletop.business.model.pendragon.chargen.AdditionalBelongings;

public class AdditionalBelongingsDocumentOutputProcessor implements
        Parser<IntervalTable<AdditionalBelongings>, Document> {

    public AdditionalBelongingsDocumentOutputProcessor() {
        super();
    }

    @Override
    public final Document
            parse(final IntervalTable<AdditionalBelongings> holder) {
        return null;
    }

}
