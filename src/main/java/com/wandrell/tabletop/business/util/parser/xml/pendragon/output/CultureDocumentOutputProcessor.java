package com.wandrell.tabletop.business.util.parser.xml.pendragon.output;

import org.jdom2.Document;

import com.wandrell.pattern.parser.xml.output.JDOMDocumentEncoder;
import com.wandrell.tabletop.business.model.pendragon.chargen.CultureTemplate;

public class CultureDocumentOutputProcessor implements
        JDOMDocumentEncoder<CultureTemplate> {
    public CultureDocumentOutputProcessor() {
        super();
    }

    @Override
    public final Document encode(final CultureTemplate holder) {
        return null;
    }

}
