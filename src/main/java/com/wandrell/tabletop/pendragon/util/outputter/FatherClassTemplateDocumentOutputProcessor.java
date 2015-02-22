package com.wandrell.tabletop.pendragon.util.outputter;

import org.jdom2.Document;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.model.chargen.FatherClassTemplate;

public final class FatherClassTemplateDocumentOutputProcessor implements
        Parser<FatherClassTemplate, Document> {

    public FatherClassTemplateDocumentOutputProcessor() {
        super();
    }

    @Override
    public final Document parse(final FatherClassTemplate holder) {
        return null;
    }

}
