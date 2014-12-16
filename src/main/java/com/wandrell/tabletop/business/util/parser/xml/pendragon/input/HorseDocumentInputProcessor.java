package com.wandrell.tabletop.business.util.parser.xml.pendragon.input;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.business.conf.pendragon.FileToken;
import com.wandrell.tabletop.business.model.pendragon.character.HorseCharacter;
import com.wandrell.util.parser.xml.input.JDOMDocumentInputProcessor;

public class HorseDocumentInputProcessor implements
        JDOMDocumentInputProcessor<HorseCharacter> {

    public HorseDocumentInputProcessor() {
        super();
    }

    @SuppressWarnings("unused")
    @Override
    public final HorseCharacter process(final Document doc) {
        final HorseCharacter holder;
        final Element secAttributes, flags;
        final Element root;

        root = doc.getRootElement();

        // holder = new DefaultHorseCharacter(new LinkedList<Attribute>());

        // TODO
        // PersistenceFactory.getCharacterService().addToBaseCharacter(root,
        // holder);

        // Acquires the different sections
        secAttributes = root.getChild(FileToken.SECONDARY_ATTRIBUTES);
        flags = root.getChild(FileToken.FLAGS);

        // Horse race
        // holder.setHorseType(root.getChildText(FileToken.RACE));

        // Secondary attributes
        // TODO
        // holder.setSecondaryAttributes(XMLUtils.readIntegerValueHandlerXMLTree(
        // secAttributes, new IntegerValueHandler()));

        // Flags
        // holder.setFlags(XMLUtil.readBooleanXMLTree(flags));

        return null;
    }

}
