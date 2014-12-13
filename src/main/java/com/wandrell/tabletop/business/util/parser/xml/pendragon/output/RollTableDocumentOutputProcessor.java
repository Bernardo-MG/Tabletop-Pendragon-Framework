package com.wandrell.tabletop.business.util.parser.xml.pendragon.output;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.business.conf.pendragon.FileToken;
import com.wandrell.tabletop.business.model.interval.IntervalTable;
import com.wandrell.util.parser.xml.output.JDOMDocumentOutputProcessor;

public class RollTableDocumentOutputProcessor implements
        JDOMDocumentOutputProcessor<IntervalTable<String>> {

    public RollTableDocumentOutputProcessor() {
        super();
    }

    @Override
    public final Document process(final IntervalTable<String> holder) {
        final Document doc;
        final Element element;

        // Root and table's name
        element = new Element(FileToken.ROLL_TABLE);
        // TODO
        // element.setAttribute(FileStreamerTags.NAME, holder.getName());
        doc = new Document(element);

        // Intervals and results
        // doc.getRootElement().addContent(
        // / buildIntervalsXMLTree((DefaultIntervalTable<String>) holder));

        return doc;
    }

    private final Element buildIntervalsXMLTree(
            final IntervalTable<String> holder) {
        final Element root;
        Element intervalNode;

        root = new Element(FileToken.INTERVALS);
        // for (final Entry<ContrastInterval<Integer>, String> intervals :
        // holder
        // .getIntervals().entrySet()) {
        // intervalNode = XMLUtil.buildIntervalXMLNode(intervals.getKey(),
        // FileToken.INTERVAL);
        // intervalNode.addContent(new Element(FileStreamerTags.VALUE)
        // .setText(intervals.getValue()));
        // root.addContent(intervalNode);
        // }

        return null;
    }

}
