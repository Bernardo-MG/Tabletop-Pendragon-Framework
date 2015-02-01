package com.wandrell.tabletop.business.util.parser.xml.pendragon.output;

import org.jdom2.Document;

import com.wandrell.tabletop.business.model.pendragon.chargen.HomelandTemplate;
import com.wandrell.util.parser.xml.output.JDOMDocumentEncoder;

public class HomelandTemplateDocumentOutputProcessor implements
        JDOMDocumentEncoder<HomelandTemplate> {

    public HomelandTemplateDocumentOutputProcessor() {
        super();
    }

    @Override
    public final Document encode(final HomelandTemplate holder) {
        return null;
    }

}
