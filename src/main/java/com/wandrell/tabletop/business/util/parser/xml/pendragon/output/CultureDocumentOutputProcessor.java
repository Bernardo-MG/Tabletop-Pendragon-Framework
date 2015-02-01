package com.wandrell.tabletop.business.util.parser.xml.pendragon.output;

import org.jdom2.Document;

import com.wandrell.tabletop.business.model.pendragon.chargen.CultureTemplate;
import com.wandrell.util.parser.xml.output.JDOMDocumentEncoder;

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
