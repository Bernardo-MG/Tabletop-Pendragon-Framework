package com.wandrell.tabletop.business.util.parser.xml.pendragon.input;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.business.conf.pendragon.FileToken;
import com.wandrell.tabletop.business.model.interval.IntervalTable;
import com.wandrell.tabletop.business.model.pendragon.manor.AnimalYearResult;
import com.wandrell.tabletop.business.model.pendragon.manor.DefaultAnimalYearResult;
import com.wandrell.tabletop.business.model.pendragon.manor.DefaultManorAnimal;
import com.wandrell.tabletop.business.model.pendragon.manor.Pet;
import com.wandrell.util.parser.xml.input.JDOMDocumentInputProcessor;

public class ManorAnimalDocumentInputProcessor implements
        JDOMDocumentInputProcessor<Pet> {

    public ManorAnimalDocumentInputProcessor() {
        super();
    }

    @Override
    public final Pet process(final Document doc) {
        final DefaultManorAnimal holder;
        final Element intervals;
        final Element root;

        root = doc.getRootElement();
        // holder = new DefaultManorAnimal(new
        // DefaultRollTable<AnimalYearResult>(
        // null, null, null));

        // Acquires the different sections
        intervals = root.getChild(FileToken.INTERVALS);

        // Pet's name
        // holder.setName(root.getAttributeValue(FileStreamerTags.NAME));

        // Intervals and results
        // readIntervalsXMLTree(intervals, holder.getAnnualCheckMap());

        return null;
    }

    private final void readIntervalsXMLTree(final Element root,
            final IntervalTable<AnimalYearResult> holder) {
        DefaultAnimalYearResult result;

        // Goes through each interval
        for (final Element node : root.getChildren()) {
            // result = readYearResultNode(node.getChild(FileToken.RESULTS));
            // result.setName(node.getAttributeValue(FileStreamerTags.NAME));
            // TODO
            // holder.put((ContrastInterval<Integer>) XMLUtils
            // .readIntegerIntervalXMLNode(node), result);
        }
    }

    private final DefaultAnimalYearResult
            readYearResultNode(final Element root) {
        final Element values, flags, files;
        // final DefaultAnimalYearResult result = new DefaultAnimalYearResult();

        values = root.getChild(FileToken.VALUE_HANDLERS);
        flags = root.getChild(FileToken.FLAGS);
        files = root.getChild(FileToken.SELECTORS_FILES);

        // Values
        if (values != null) {
            // TODO
            // result.setValues(XMLUtils.readIntegerValueHandlerXMLTree(values,
            // new IntegerValueHandler()));
        }

        // Flags
        if (flags != null) {
            // TODO
            // result.setFlags(XMLUtils.readBooleanXMLTree(flags));
        }

        // Files
        if (files != null) {
            // TODO
            // result.setFiles(XMLUtils.readStringsDictionaryXMLTree(files));
        }

        return null;
    }

}
