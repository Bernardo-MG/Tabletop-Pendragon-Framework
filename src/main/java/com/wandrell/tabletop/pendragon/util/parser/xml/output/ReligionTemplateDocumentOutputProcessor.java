package com.wandrell.tabletop.pendragon.util.parser.xml.output;

import org.jdom2.Document;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.model.chargen.ReligionTemplate;

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
