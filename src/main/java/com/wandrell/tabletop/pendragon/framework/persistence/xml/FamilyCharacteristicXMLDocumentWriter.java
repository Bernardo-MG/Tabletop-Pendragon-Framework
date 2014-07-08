package com.wandrell.tabletop.pendragon.framework.persistence.xml;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.pendragon.character.background.FamilyCharacteristic;
import com.wandrell.tabletop.pendragon.framework.conf.FileLabels;
import com.wandrell.util.file.api.xml.XMLDocumentWriter;

public class FamilyCharacteristicXMLDocumentWriter implements
	XMLDocumentWriter<FamilyCharacteristic> {

    public FamilyCharacteristicXMLDocumentWriter() {
	super();
    }

    @Override
    public final Document getDocument(final FamilyCharacteristic holder) {
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

    @SuppressWarnings("unused")
    private final Element buildIntervalsXMLTree(
	    final FamilyCharacteristic holder) {
	final Element root;
	Element intervalNode;

	root = new Element(FileLabels.INTERVALS);
	// Creates intervals tree
	// TODO
	// for (final Entry<ContrastInterval<Integer>, PendragonTemplate> entry
	// : holder
	// .entrySet()) {
	// Creates the interval root
	// intervalNode = XMLUtils.buildIntervalXMLNode(entry.getKey(),
	// FileStreamerTags.INTERVAL);
	// intervalNode.setAttribute(FileStreamerTags.NAME, entry.getValue()
	// .getName());
	//
	// Creates the bonus tree
	// intervalNode.addContent(PersistenceFactory
	// .getPendragonTemplateService()
	// .buildPendragonTemplateXMLNode(entry.getValue(),
	// new Element(FileLabels.TEMPLATE_BONUS)));
	//
	// root.addContent(intervalNode);
	// }

	return root;
    }

}
