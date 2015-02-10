package com.wandrell.tabletop.business.util.parser.xml.pendragon.output;

import org.jdom2.Document;

import com.wandrell.pattern.parser.xml.output.JDOMDocumentEncoder;
import com.wandrell.tabletop.business.model.pendragon.chargen.FatherClassTemplate;

public class FatherClassTemplateDocumentOutputProcessor implements
        JDOMDocumentEncoder<FatherClassTemplate> {

    public FatherClassTemplateDocumentOutputProcessor() {
        super();
    }

    @Override
    public final Document encode(final FatherClassTemplate holder) {
        return null;
    }

}
