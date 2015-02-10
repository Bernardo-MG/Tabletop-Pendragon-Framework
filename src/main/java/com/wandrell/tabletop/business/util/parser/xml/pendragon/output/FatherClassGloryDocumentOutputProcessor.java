package com.wandrell.tabletop.business.util.parser.xml.pendragon.output;

import org.jdom2.Document;

import com.wandrell.pattern.parser.xml.output.JDOMDocumentEncoder;
import com.wandrell.tabletop.business.model.pendragon.glory.FatherClassGlory;

public class FatherClassGloryDocumentOutputProcessor implements
        JDOMDocumentEncoder<FatherClassGlory> {

    public FatherClassGloryDocumentOutputProcessor() {
        super();
    }

    @Override
    public final Document encode(final FatherClassGlory holder) {
        return null;
    }

}
