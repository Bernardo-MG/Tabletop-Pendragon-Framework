package com.wandrell.tabletop.pendragon.framework.persistence.xml;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.conf.FileStreamerTags;
import com.wandrell.tabletop.pendragon.character.background.culture.Culture;
import com.wandrell.tabletop.pendragon.character.background.culture.CultureCharacterTemplate;
import com.wandrell.tabletop.pendragon.framework.conf.FileToken;
import com.wandrell.util.file.api.xml.XMLDocumentWriter;

public class CultureXMLDocumentWriter implements XMLDocumentWriter<Culture> {

    private final static Element buildAttributesLimitsTemplateXMLNode(
	    final CultureCharacterTemplate holder, final Element root) {
	// TODO
	// final Iterator<NamedContrastInterval<Integer>> itrAttributes;
	// NamedInterval<Integer> interval;
	// Element node, nodeInterval, result;
	//
	// result = PersistenceFactory.getPendragonTemplateService()
	// .buildPendragonTemplateXMLNode(holder, root);
	//
	// Attributes intervals
	// itrAttributes = holder.getAttributesLimitsIterator();
	// node = new Element(FileLabels.ATTRIBUTES_LIMITS);
	// while (itrAttributes.hasNext()) {
	// interval = itrAttributes.next();
	// nodeInterval = XMLUtils.buildIntervalXMLNode(interval,
	// FileLabels.ATTRIBUTE);
	// if (nodeInterval != null) {
	// node.addContent(nodeInterval);
	// }
	// }
	// if (node.getChildren().size() > 0) {
	// result.addContent(node);
	// }

	return null;
    }

    public CultureXMLDocumentWriter() {
	super();
    }

    @Override
    public final Document getDocument(final Culture holder) {
	final Document doc;
	Element node;

	// Main body and name of the culture
	node = new Element(FileToken.CULTURE);
	node.setAttribute(FileStreamerTags.NAME, holder.getName());
	doc = new Document(node);

	// Selector files
	// TODO
	// node = XMLUtils.buildStringsDictionaryXMLTree(new Element(
	// FileLabels.SELECTORS_FILES), holder.getFilesIterator(),
	// FileLabels.FILE);
	if (node.getChildren().size() > 0) {
	    doc.getRootElement().addContent(node);
	}

	// Female template
	node = new Element(FileToken.TEMPLATE_FEMALE);
	buildAttributesLimitsTemplateXMLNode(holder.getFemaleTemplate(), node);
	doc.getRootElement().addContent(node);

	// Male template
	node = new Element(FileToken.TEMPLATE_MALE);
	buildAttributesLimitsTemplateXMLNode(holder.getMaleTemplate(), node);
	doc.getRootElement().addContent(node);

	// Random generation templates

	// Female template
	node = new Element(FileToken.TEMPLATE_FEMALE_RANDOM);
	buildAttributesLimitsTemplateXMLNode(holder.getFemaleRandomTemplate(),
		node);
	if (node.getChild(FileToken.ATTRIBUTES) == null) {
	    node.addContent(new Element(FileToken.ATTRIBUTES));
	}
	if (node.getChild(FileToken.PASSIONS) == null) {
	    node.addContent(new Element(FileToken.PASSIONS));
	}
	doc.getRootElement().addContent(node);

	// Male template
	node = new Element(FileToken.TEMPLATE_MALE_RANDOM);
	buildAttributesLimitsTemplateXMLNode(holder.getMaleRandomTemplate(),
		node);
	if (node.getChild(FileToken.ATTRIBUTES) == null) {
	    node.addContent(new Element(FileToken.ATTRIBUTES));
	}
	if (node.getChild(FileToken.PASSIONS) == null) {
	    node.addContent(new Element(FileToken.PASSIONS));
	}
	doc.getRootElement().addContent(node);

	return doc;
    }

}
