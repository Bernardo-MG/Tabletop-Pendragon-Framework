package com.wandrell.tabletop.business.util.parser.xml.pendragon.output;

import org.jdom2.Document;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.business.model.pendragon.stats.SpecialtySkill;

public class SpecialtyDocumentOutputProcessor implements
        Parser<SpecialtySkill, Document> {

    public SpecialtyDocumentOutputProcessor() {
        super();
    }

    @Override
    public final Document parse(final SpecialtySkill holder) {
        return null;
    }

}
