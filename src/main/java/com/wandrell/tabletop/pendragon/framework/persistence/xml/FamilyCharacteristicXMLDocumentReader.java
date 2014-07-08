package com.wandrell.tabletop.pendragon.framework.persistence.xml;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.conf.FileStreamerTags;
import com.wandrell.tabletop.pendragon.character.background.FamilyCharacteristic;
import com.wandrell.tabletop.pendragon.framework.conf.FileLabels;
import com.wandrell.util.file.api.xml.XMLDocumentReader;

public class FamilyCharacteristicXMLDocumentReader implements
	XMLDocumentReader<FamilyCharacteristic> {

    public FamilyCharacteristicXMLDocumentReader() {
	super();
    }

    @Override
    public final FamilyCharacteristic getValue(final Document doc) {
	final Element intervals;
	final FamilyCharacteristic holder;
	final Element root;

	root = doc.getRootElement();
	holder = null;

	// TODO
	// holder = ClassInstanceFactory.getNewValue(TemplateRollTable.class);

	// Acquires the different sections
	intervals = root.getChild(FileLabels.INTERVALS);

	// Table's name
	// TODO
	// holder.setName(root.getAttributeValue(FileStreamerTags.NAME));

	// Intervals and results
	readIntervalsXMLTree(intervals, holder);

	return holder;
    }

    @SuppressWarnings("unused")
    private final void readIntervalsXMLTree(final Element root,
	    final FamilyCharacteristic holder) {
	String name;

	// Goes through each interval
	for (final Element node : root.getChildren()) {
	    name = node.getAttributeValue(FileStreamerTags.NAME);

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
