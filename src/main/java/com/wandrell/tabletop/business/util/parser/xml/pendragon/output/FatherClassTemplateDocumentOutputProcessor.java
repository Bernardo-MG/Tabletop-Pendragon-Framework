package com.wandrell.tabletop.business.util.parser.xml.pendragon.output;

import org.jdom2.Document;

import com.wandrell.tabletop.business.model.pendragon.chargen.FatherClassTemplate;
import com.wandrell.util.parser.xml.output.JDOMDocumentEncoder;

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
