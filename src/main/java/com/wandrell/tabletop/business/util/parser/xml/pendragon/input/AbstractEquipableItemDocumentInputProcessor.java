package com.wandrell.tabletop.business.util.parser.xml.pendragon.input;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.business.conf.pendragon.FileToken;
import com.wandrell.tabletop.business.model.pendragon.inventory.Equipment;
import com.wandrell.util.parser.xml.input.JDOMDocumentInputProcessor;

public abstract class AbstractEquipableItemDocumentInputProcessor<V extends Equipment>
        implements JDOMDocumentInputProcessor<V> {

    public AbstractEquipableItemDocumentInputProcessor() {
        super();
    }

    @SuppressWarnings("unchecked")
    @Override
    public V process(final Document doc) {
        final Element values, flags, bonus;
        Equipment holder = null;
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
