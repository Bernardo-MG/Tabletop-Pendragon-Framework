package com.wandrell.tabletop.business.util.parser.xml.pendragon.input;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.business.conf.pendragon.FileToken;
import com.wandrell.tabletop.business.model.pendragon.chargen.FamilyCharacteristicBonus;
import com.wandrell.util.parser.xml.input.JDOMDocumentInputProcessor;

public class FamilyCharacteristicDocumentInputProcessor implements
        JDOMDocumentInputProcessor<FamilyCharacteristicBonus> {

    public FamilyCharacteristicDocumentInputProcessor() {
        super();
    }

    @Override
    public final FamilyCharacteristicBonus process(final Document doc) {
        final Element intervals;
        final FamilyCharacteristicBonus holder;
        final Element root;

        root = doc.getRootElement();

        // TODO
        // holder = ClassInstanceFactory.getNewValue(TemplateRollTable.class);

        // Acquires the different sections
        intervals = root.getChild(FileToken.INTERVALS);

        // Intervals and results
        // readIntervalsXMLTree(intervals, holder);

        // holder = new DefaultFamilyCharacteristic(
        // root.getAttributeValue(FileStreamerTags.NAME));

        return null;
    }

    @SuppressWarnings("unused")
    private final void readIntervalsXMLTree(final Element root,
            final FamilyCharacteristicBonus holder) {
        String name;

        // Goes through each interval
        for (final Element node : root.getChildren()) {
            // name = node.getAttributeValue(FileStreamerTags.NAME);

            // TODO
            // holder.put(
            // (ContrastInterval<Integer>) XMLUtils
            // .readIntegerIntervalXMLNode(node),
            // PersistenceFactory.getPendragonTemplateService()
            // .readPendragonTemplateXMLTree(
            // node.getChild(FileLabels.TEMPLATE_BONUS),
            // new DefaultPendragonTemplate(name)));
        }
    }

}
