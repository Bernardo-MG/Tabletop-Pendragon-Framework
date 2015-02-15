package com.wandrell.tabletop.business.util.parser.xml.pendragon.output;

import org.jdom2.Document;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.business.model.pendragon.chargen.ReligionTemplate;

public class ReligionTemplateDocumentOutputProcessor implements
        Parser<ReligionTemplate, Document> {

    public ReligionTemplateDocumentOutputProcessor() {
        super();
    }

    @Override
    public final Document parse(final ReligionTemplate holder) {
        return null;
    }

}
