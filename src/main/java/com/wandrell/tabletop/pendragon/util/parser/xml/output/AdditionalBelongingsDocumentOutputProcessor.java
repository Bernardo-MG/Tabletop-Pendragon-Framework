package com.wandrell.tabletop.pendragon.util.parser.xml.output;

import org.jdom2.Document;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.interval.IntervalTable;
import com.wandrell.tabletop.pendragon.model.chargen.AdditionalBelongings;

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
