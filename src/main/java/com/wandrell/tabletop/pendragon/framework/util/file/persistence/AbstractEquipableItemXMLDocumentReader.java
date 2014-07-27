package com.wandrell.tabletop.pendragon.framework.util.file.persistence;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.pendragon.framework.conf.FileToken;
import com.wandrell.tabletop.pendragon.inventory.PendragonEquipment;
import com.wandrell.util.file.api.xml.XMLDocumentReader;

public abstract class AbstractEquipableItemXMLDocumentReader<V extends PendragonEquipment>
	implements XMLDocumentReader<V> {

    public AbstractEquipableItemXMLDocumentReader() {
	super();
    }

    @SuppressWarnings("unchecked")
    @Override
    public V getValue(final Document doc) {
	final Element values, flags, bonus;
	PendragonEquipment holder = null;
	final Element root = doc.getRootElement();

	// TODO
	// holder = (PendragonEquipment) PersistenceFactory.getItemService()
	// .readItemXMLNode(root,
	// ClassInstanceFactory.getNewValue(classValue));

	// Acquires the different sections
	values = root.getChild(FileToken.VALUE_HANDLERS);
	flags = root.getChild(FileToken.FLAGS);
	bonus = root.getChild(FileToken.TEMPLATE_BONUS);

	// Values
	if (values != null) {
	    // TODO
	    // holder.setValues(XMLUtils.readIntegerValueHandlerXMLTree(values,
	    // new IntegerValueHandler()));
	}

	// Flags
	if (flags != null) {
	    // TODO
	    // holder.setFlags(XMLUtils.readBooleanXMLTree(flags));
	}

	// Bonus
	if (bonus != null) {
	    // TODO
	    // holder.setBonusValuesTemplate(PersistenceFactory
	    // .getPendragonTemplateService()
	    // .readPendragonTemplateXMLTree(bonus,
	    // new DefaultPendragonTemplate()));
	}

	return (V) holder;
    }

}
