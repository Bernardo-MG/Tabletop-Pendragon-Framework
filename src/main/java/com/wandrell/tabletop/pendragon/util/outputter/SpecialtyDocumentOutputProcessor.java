package com.wandrell.tabletop.pendragon.util.outputter;

import org.jdom2.Document;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.model.stats.SpecialtySkill;

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
