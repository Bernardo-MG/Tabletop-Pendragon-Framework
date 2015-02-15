package com.wandrell.tabletop.business.util.parser.xml.pendragon.output;

import org.jdom2.Document;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.business.model.pendragon.character.Horse;

public class HorseDocumentOutputProcessor implements Parser<Horse, Document> {

    public HorseDocumentOutputProcessor() {
        super();
    }

    @Override
    public final Document parse(final Horse holder) {
        return null;
    }

}
