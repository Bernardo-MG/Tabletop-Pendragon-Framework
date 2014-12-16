package com.wandrell.tabletop.business.util.parser.xml.pendragon.input;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.business.model.pendragon.chargen.ReligionBonus;
import com.wandrell.util.parser.xml.input.JDOMDocumentInputProcessor;

public class ReligionTemplateDocumentInputProcessor implements
        JDOMDocumentInputProcessor<ReligionBonus> {

    public ReligionTemplateDocumentInputProcessor() {
        super();
    }

    @Override
    public final ReligionBonus process(final Document doc) {
        final ReligionBonus holder;
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
