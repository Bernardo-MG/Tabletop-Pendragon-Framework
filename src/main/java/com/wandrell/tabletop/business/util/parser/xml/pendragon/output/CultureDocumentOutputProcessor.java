package com.wandrell.tabletop.business.util.parser.xml.pendragon.output;

import org.jdom2.Document;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.business.model.pendragon.chargen.CultureTemplate;

public class CultureDocumentOutputProcessor implements
        Parser<CultureTemplate, Document> {
    public CultureDocumentOutputProcessor() {
        super();
    }

    @Override
    public final Document parse(final CultureTemplate holder) {
        return null;
    }

}
