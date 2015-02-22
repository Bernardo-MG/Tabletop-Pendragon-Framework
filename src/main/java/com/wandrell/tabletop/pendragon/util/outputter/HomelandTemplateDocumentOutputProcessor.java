package com.wandrell.tabletop.pendragon.util.outputter;

import org.jdom2.Document;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.model.chargen.HomelandTemplate;

public final class HomelandTemplateDocumentOutputProcessor implements
        Parser<HomelandTemplate, Document> {

    public HomelandTemplateDocumentOutputProcessor() {
        super();
    }

    @Override
    public final Document parse(final HomelandTemplate holder) {
        return null;
    }

}
