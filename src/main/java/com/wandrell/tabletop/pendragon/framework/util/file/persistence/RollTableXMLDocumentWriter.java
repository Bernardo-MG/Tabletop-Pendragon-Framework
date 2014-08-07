package com.wandrell.tabletop.pendragon.framework.util.file.persistence;

import java.util.Map.Entry;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.conf.FileStreamerTags;
import com.wandrell.tabletop.dice.DefaultRollTable;
import com.wandrell.tabletop.dice.RollTable;
import com.wandrell.tabletop.interval.ContrastInterval;
import com.wandrell.tabletop.pendragon.framework.conf.FileToken;
import com.wandrell.tabletop.util.XMLUtil;
import com.wandrell.util.file.api.xml.XMLDocumentWriter;

public class RollTableXMLDocumentWriter implements
        XMLDocumentWriter<RollTable<String>> {

    public RollTableXMLDocumentWriter() {
        super();
    }

    @Override
    public final Document getDocument(final RollTable<String> holder) {
        final Document doc;
        final Element element;

        // Root and table's name
        element = new Element(FileToken.ROLL_TABLE);
        // TODO
        // element.setAttribute(FileStreamerTags.NAME, holder.getName());
        doc = new Document(element);

        // Intervals and results
        doc.getRootElement().addContent(
                buildIntervalsXMLTree((DefaultRollTable<String>) holder));

        return doc;
    }

    private final Element buildIntervalsXMLTree(
            final DefaultRollTable<String> holder) {
        final Element root;
        Element intervalNode;

        root = new Element(FileToken.INTERVALS);
        for (final Entry<ContrastInterval<Integer>, String> intervals : holder
                .getIntervals().entrySet()) {
            intervalNode = XMLUtil.buildIntervalXMLNode(intervals.getKey(),
                    FileToken.INTERVAL);
            intervalNode.addContent(new Element(FileStreamerTags.VALUE)
                    .setText(intervals.getValue()));
            root.addContent(intervalNode);
        }

        return root;
    }

}
