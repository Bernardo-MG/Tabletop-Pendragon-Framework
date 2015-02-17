package com.wandrell.tabletop.pendragon.util.parser.xml.output;

import org.jdom2.Document;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.model.manor.Pet;

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
