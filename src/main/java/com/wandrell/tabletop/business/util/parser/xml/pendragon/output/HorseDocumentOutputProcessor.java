package com.wandrell.tabletop.business.util.parser.xml.pendragon.output;

import org.jdom2.Document;

import com.wandrell.tabletop.business.model.pendragon.character.HorseCharacter;
import com.wandrell.util.parser.xml.output.JDOMDocumentEncoder;

public class HorseDocumentOutputProcessor implements
        JDOMDocumentEncoder<HorseCharacter> {

    public HorseDocumentOutputProcessor() {
        super();
    }

    @Override
    public final Document encode(final HorseCharacter holder) {
        return null;
    }

}
