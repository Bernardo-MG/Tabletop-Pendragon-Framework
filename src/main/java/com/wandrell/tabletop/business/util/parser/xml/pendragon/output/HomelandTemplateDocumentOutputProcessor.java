package com.wandrell.tabletop.business.util.parser.xml.pendragon.output;

import org.jdom2.Document;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.business.model.pendragon.chargen.HomelandTemplate;

public class HomelandTemplateDocumentOutputProcessor implements
        Parser<HomelandTemplate, Document> {

    public HomelandTemplateDocumentOutputProcessor() {
        super();
    }

    @Override
    public final Document parse(final HomelandTemplate holder) {
        return null;
    }

}
