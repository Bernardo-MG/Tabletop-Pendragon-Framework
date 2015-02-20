package com.wandrell.tabletop.pendragon.util.outputter;

import org.jdom2.Document;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.model.chargen.FamilyCharacteristicTemplate;

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
