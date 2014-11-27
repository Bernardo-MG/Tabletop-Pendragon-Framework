package com.wandrell.tabletop.business.util.parser.xml.pendragon.input;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.business.model.pendragon.character.background.Religion;
import com.wandrell.util.parser.xml.input.JDOMDocumentInputProcessor;

public class ReligionTemplateDocumentInputProcessor implements
        JDOMDocumentInputProcessor<Religion> {

    public ReligionTemplateDocumentInputProcessor() {
        super();
    }

    @Override
    public final Religion process(final Document doc) {
        final Religion holder;
        // final Element traits, bonus;
        final Element root;

        root = doc.getRootElement();

        // Acquires the different sections
        // traits = root.getChild(FileLabels.TRAITS);
        // bonus = root.getChild(FileLabels.TEMPLATE_BONUS);

        // Religion's name
        // holder = new DefaultReligion(
        // root.getAttributeValue(FileStreamerTags.NAME));

        // Religious traits
        // TODO
        // holder.setReligiousTraits(XMLUtils.readStringsListXMLTree(traits));

        // Religious bonus
        // TODO
        // if (bonus != null) {
        // holder.setBonusTemplate(PersistenceFactory
        // .getPendragonTemplateService()
        // .readPendragonTemplateXMLTree(bonus,
        // new DefaultPendragonTemplate()));
        // }

        return null;
    }

}
