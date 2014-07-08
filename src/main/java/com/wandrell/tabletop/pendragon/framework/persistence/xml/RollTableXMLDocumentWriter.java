package com.wandrell.tabletop.pendragon.framework.persistence.xml;

import java.util.Map.Entry;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.conf.FileStreamerTags;
import com.wandrell.tabletop.dice.RollTable;
import com.wandrell.tabletop.interval.ContrastInterval;
import com.wandrell.tabletop.pendragon.framework.conf.FileLabels;
import com.wandrell.tabletop.util.XMLUtils;
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
	element = new Element(FileLabels.ROLL_TABLE);
	// TODO
	// element.setAttribute(FileStreamerTags.NAME, holder.getName());
	doc = new Document(element);

	// Intervals and results
	doc.getRootElement().addContent(buildIntervalsXMLTree(holder));

	return doc;
    }

    private final Element buildIntervalsXMLTree(final RollTable<String> holder) {
	final Element root;
	Element intervalNode;

	root = new Element(FileLabels.INTERVALS);
	for (final Entry<ContrastInterval<Integer>, String> intervals : holder
		.getValuesMap().entrySet()) {
	    intervalNode = XMLUtils.buildIntervalXMLNode(intervals.getKey(),
		    FileLabels.INTERVAL);
	    intervalNode.addContent(new Element(FileStreamerTags.VALUE)
		    .setText(intervals.getValue()));
	    root.addContent(intervalNode);
	}

	return root;
    }

}
