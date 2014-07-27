package com.wandrell.tabletop.pendragon.framework.util.file.persistence;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.conf.FileStreamerTags;
import com.wandrell.tabletop.pendragon.character.background.DefaultHomeland;
import com.wandrell.tabletop.pendragon.character.background.Homeland;
import com.wandrell.util.file.api.xml.XMLDocumentReader;

public class HomelandTemplateXMLDocumentReader implements
	XMLDocumentReader<Homeland> {

    public HomelandTemplateXMLDocumentReader() {
	super();
    }

    @Override
    public final Homeland getValue(final Document doc) {
	final Homeland holder;
	final Element root;

	root = doc.getRootElement();

	// Homeland's name
	holder = new DefaultHomeland(
		root.getAttributeValue(FileStreamerTags.NAME));

	// TODO
	// PersistenceFactory.getTemplatesContainerService().readXMLTree(root,
	// holder);

	return holder;
    }

}
