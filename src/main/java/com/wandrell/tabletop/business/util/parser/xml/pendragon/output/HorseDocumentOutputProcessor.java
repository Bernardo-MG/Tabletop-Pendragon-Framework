package com.wandrell.tabletop.business.util.parser.xml.pendragon.output;

import org.jdom2.Document;

import com.wandrell.tabletop.business.model.pendragon.character.Horse;
import com.wandrell.util.parser.xml.output.JDOMDocumentEncoder;

public class HorseDocumentOutputProcessor implements JDOMDocumentEncoder<Horse> {

    public HorseDocumentOutputProcessor() {
        super();
    }

    @Override
    public final Document encode(final Horse holder) {
        return null;
    }

}
