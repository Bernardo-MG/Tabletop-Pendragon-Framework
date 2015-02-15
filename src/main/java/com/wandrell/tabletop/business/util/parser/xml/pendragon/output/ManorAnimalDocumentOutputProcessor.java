package com.wandrell.tabletop.business.util.parser.xml.pendragon.output;

import org.jdom2.Document;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.business.model.pendragon.manor.Pet;

public class ManorAnimalDocumentOutputProcessor implements
        Parser<Pet, Document> {

    public ManorAnimalDocumentOutputProcessor() {
        super();
    }

    @Override
    public final Document parse(final Pet holder) {
        return null;
    }

}
