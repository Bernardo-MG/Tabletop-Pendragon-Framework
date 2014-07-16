package com.wandrell.tabletop.pendragon.framework.persistence.xml;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.conf.FileStreamerTags;
import com.wandrell.tabletop.pendragon.character.background.Religion;
import com.wandrell.tabletop.pendragon.framework.conf.FileToken;
import com.wandrell.util.file.api.xml.XMLDocumentWriter;

public class ReligionTemplateXMLDocumentWriter implements
	XMLDocumentWriter<Religion> {

    public ReligionTemplateXMLDocumentWriter() {
	super();
    }

    @Override
    public final Document getDocument(final Religion holder) {
	final Document doc;
	Element node;

	// Root and religion name
	node = new Element(FileToken.RELIGION);
	node.setAttribute(FileStreamerTags.NAME, holder.getName());
	doc = new Document(node);

	// Religious traits
	// TODO
	// doc.getRootElement().addContent(
	// XMLUtils.buildStringListXMLTree(new Element(FileLabels.TRAITS),
	// holder.getReligiousTraits(), FileLabels.TRAIT));

	// Religious bonus
	// TODO
	// node = PersistenceFactory.getPendragonTemplateService()
	// .buildPendragonTemplateXMLNode(holder.getBonusTemplate(),
	// new Element(FileLabels.TEMPLATE_BONUS));
	if (node.getChildren().size() > 0) {
	    doc.getRootElement().addContent(node);
	}

	return doc;
    }

}
