package com.wandrell.tabletop.business.util.parser.xml.pendragon.output;

import org.jdom2.Document;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.business.model.pendragon.chargen.FamilyCharacteristicTemplate;

public class FamilyCharacteristicDocumentOutputProcessor implements
        Parser<FamilyCharacteristicTemplate, Document> {

    public FamilyCharacteristicDocumentOutputProcessor() {
        super();
    }

    @Override
    public final Document parse(final FamilyCharacteristicTemplate holder) {
        return null;
    }

}
