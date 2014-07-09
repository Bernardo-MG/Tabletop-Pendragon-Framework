package com.wandrell.tabletop.pendragon.framework.persistence.xml;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.conf.FileStreamerTags;
import com.wandrell.tabletop.dice.DefaultRollTable;
import com.wandrell.tabletop.dice.RollTable;
import com.wandrell.util.file.api.xml.XMLDocumentReader;

public class RollTableXMLDocumentReader implements
	XMLDocumentReader<RollTable<String>> {

    public RollTableXMLDocumentReader() {
	super();
    }

    @Override
    public final RollTable<String> getValue(final Document doc) {
	final DefaultRollTable<String> holder;
	// final Element intervals;
	final Element root;

	root = doc.getRootElement();

	// TODO: Get the correct upper limit
	holder = new DefaultRollTable<String>(
		root.getAttributeValue(FileStreamerTags.NAME));

	// Acquires the different sections
	// intervals = root.getChild(FileLabels.INTERVALS);

	// Intervals and results
	// readIntervalsXMLTree(intervals, holder);

	return holder;
    }

    @SuppressWarnings("unused")
    private final void readIntervalsXMLTree(final Element root,
	    final DefaultRollTable<String> holder) {
	for (final Element node : root.getChildren()) {
	    // TODO
	    // holder.put((ContrastInterval<Integer>) XMLUtils
	    // .readIntegerIntervalXMLNode(node), node
	    // .getChildText(FileStreamerTags.VALUE));
	}
    }

}
