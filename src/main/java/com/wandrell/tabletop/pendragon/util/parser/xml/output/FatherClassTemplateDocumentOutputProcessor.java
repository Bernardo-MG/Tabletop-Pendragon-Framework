package com.wandrell.tabletop.pendragon.util.parser.xml.output;

import org.jdom2.Document;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.model.chargen.FatherClassTemplate;

public class FatherClassTemplateDocumentOutputProcessor implements
        Parser<FatherClassTemplate, Document> {

    public FatherClassTemplateDocumentOutputProcessor() {
        super();
    }

    @Override
    public final Document parse(final FatherClassTemplate holder) {
        return null;
    }

}
