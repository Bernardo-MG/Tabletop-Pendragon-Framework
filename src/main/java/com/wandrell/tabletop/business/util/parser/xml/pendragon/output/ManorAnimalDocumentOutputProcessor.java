package com.wandrell.tabletop.business.util.parser.xml.pendragon.output;

import org.jdom2.Document;

import com.wandrell.tabletop.business.model.pendragon.manor.Pet;
import com.wandrell.util.parser.xml.output.JDOMDocumentEncoder;

public class ManorAnimalDocumentOutputProcessor implements
        JDOMDocumentEncoder<Pet> {

    public ManorAnimalDocumentOutputProcessor() {
        super();
    }

    @Override
    public final Document encode(final Pet holder) {
        return null;
    }

}
