package com.wandrell.tabletop.business.util.parser.xml.pendragon.input;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.business.model.interval.IntervalTable;
import com.wandrell.util.parser.xml.input.JDOMDocumentInputProcessor;

public class RollTableDocumentInputProcessor implements
        JDOMDocumentInputProcessor<IntervalTable<String>> {

    public RollTableDocumentInputProcessor() {
        super();
    }

    @Override
    public final IntervalTable<String> process(final Document doc) {
        final IntervalTable<String> holder;
        // final Element intervals;
        final Element root;

        root = doc.getRootElement();

        // TODO: Get the correct upper limit
        // holder = new DefaultRollTable<String>(
        // root.getAttributeValue(FileStreamerTags.NAME));

        // Acquires the different sections
        // intervals = root.getChild(FileLabels.INTERVALS);

        // Intervals and results
        // readIntervalsXMLTree(intervals, holder);

        return null;
    }

    @SuppressWarnings("unused")
    private final void readIntervalsXMLTree(final Element root,
            final IntervalTable<String> holder) {
        for (final Element node : root.getChildren()) {
            // TODO
            // holder.put((ContrastInterval<Integer>) XMLUtils
            // .readIntegerIntervalXMLNode(node), node
            // .getChildText(FileStreamerTags.VALUE));
        }
    }

}
