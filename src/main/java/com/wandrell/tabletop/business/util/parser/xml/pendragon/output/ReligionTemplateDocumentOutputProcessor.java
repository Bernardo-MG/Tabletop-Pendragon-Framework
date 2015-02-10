package com.wandrell.tabletop.business.util.parser.xml.pendragon.output;

import org.jdom2.Document;

import com.wandrell.pattern.parser.xml.output.JDOMDocumentEncoder;
import com.wandrell.tabletop.business.model.pendragon.chargen.ReligionTemplate;

public class ReligionTemplateDocumentOutputProcessor implements
        JDOMDocumentEncoder<ReligionTemplate> {

    public ReligionTemplateDocumentOutputProcessor() {
        super();
    }

    @Override
    public final Document encode(final ReligionTemplate holder) {
        return null;
    }

}
