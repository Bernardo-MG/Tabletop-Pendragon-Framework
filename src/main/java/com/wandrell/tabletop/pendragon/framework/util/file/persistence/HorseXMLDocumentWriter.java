package com.wandrell.tabletop.pendragon.framework.util.file.persistence;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.pendragon.character.HorseCharacter;
import com.wandrell.tabletop.pendragon.framework.conf.FileToken;
import com.wandrell.util.file.api.xml.XMLDocumentWriter;

public class HorseXMLDocumentWriter implements
        XMLDocumentWriter<HorseCharacter> {

    public HorseXMLDocumentWriter() {
        super();
    }

    @SuppressWarnings("unused")
    @Override
    public final Document getDocument(final HorseCharacter holder) {
        final Document doc;
        Element node;

        doc = new Document(new Element(FileToken.HORSE));
        // TODO
        // PersistenceFactory.getCharacterService().addBaseCharacterToDocument(
        // doc, holder);

        // Horse race
        // doc.getRootElement().setAttribute(FileStreamerTags.NAME,
        // holder.getHorseType());
        doc.getRootElement().addContent(0,
                new Element(FileToken.RACE).setText(holder.getHorseType()));

        // Secondary attributes
        // TODO
        // doc.getRootElement().addContent(
        // XMLUtils.buildIntegerValueHandlerXMLTree(new Element(
        // FileLabels.SECONDARY_ATTRIBUTES), holder
        // .getSecondaryAttributesIterator(),
        // FileLabels.SECONDARY_ATTRIBUTE));

        // Flags
        // TODO
        // node = XMLUtils.buildBooleansSetXMLTree(new
        // Element(FileLabels.FLAGS),
        // holder.getFlagsIterator());
        // if (node.getChildren().size() > 0) {
        // doc.getRootElement().addContent(node);
        // }

        return doc;
    }

}
