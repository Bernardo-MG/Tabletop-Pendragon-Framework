package com.wandrell.tabletop.pendragon.framework.util.file.persistence;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.conf.FileStreamerTags;
import com.wandrell.tabletop.pendragon.character.background.DefaultReligion;
import com.wandrell.tabletop.pendragon.character.background.Religion;
import com.wandrell.util.file.api.xml.XMLDocumentReader;

public class ReligionTemplateXMLDocumentReader implements
	XMLDocumentReader<Religion> {

    public ReligionTemplateXMLDocumentReader() {
	super();
    }

    @Override
    public final Religion getValue(final Document doc) {
	final Religion holder;
	// final Element traits, bonus;
	final Element root;

	root = doc.getRootElement();

	// Acquires the different sections
	// traits = root.getChild(FileLabels.TRAITS);
	// bonus = root.getChild(FileLabels.TEMPLATE_BONUS);

	// Religion's name
	holder = new DefaultReligion(
		root.getAttributeValue(FileStreamerTags.NAME));

	// Religious traits
	// TODO
	// holder.setReligiousTraits(XMLUtils.readStringsListXMLTree(traits));

	// Religious bonus
	// TODO
	// if (bonus != null) {
	// holder.setBonusTemplate(PersistenceFactory
	// .getPendragonTemplateService()
	// .readPendragonTemplateXMLTree(bonus,
	// new DefaultPendragonTemplate()));
	// }

	return holder;
    }

}
