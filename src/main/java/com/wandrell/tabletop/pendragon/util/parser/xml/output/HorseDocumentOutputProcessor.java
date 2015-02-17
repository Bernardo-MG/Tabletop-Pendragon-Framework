package com.wandrell.tabletop.pendragon.util.parser.xml.output;

import org.jdom2.Document;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.model.character.Horse;

public class HorseDocumentOutputProcessor implements Parser<Horse, Document> {

    public HorseDocumentOutputProcessor() {
        super();
    }

    @Override
    public final Document parse(final Horse holder) {
        return null;
    }

}
