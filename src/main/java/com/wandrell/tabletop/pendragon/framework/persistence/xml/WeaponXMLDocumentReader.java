package com.wandrell.tabletop.pendragon.framework.persistence.xml;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.pendragon.framework.conf.FileLabels;
import com.wandrell.tabletop.pendragon.inventory.WeaponEquipment;

public class WeaponXMLDocumentReader extends
	AbstractEquipableItemXMLDocumentReader<WeaponEquipment> {

    public WeaponXMLDocumentReader() {
	super();
    }

    @Override
    public final WeaponEquipment getValue(final Document doc) {
	final Element skill;
	final WeaponEquipment weapon;
	final Element root;

	weapon = super.getValue(doc);

	root = doc.getRootElement();

	// Acquires the different sections
	skill = root.getChild(FileLabels.SKILL);
	weapon.setSkill(skill.getText());

	return weapon;
    }

}
