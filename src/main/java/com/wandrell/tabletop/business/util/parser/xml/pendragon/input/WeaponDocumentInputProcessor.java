package com.wandrell.tabletop.business.util.parser.xml.pendragon.input;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.business.conf.pendragon.FileToken;
import com.wandrell.tabletop.business.model.pendragon.inventory.Weapon;

public class WeaponDocumentInputProcessor extends
        AbstractEquipableItemDocumentInputProcessor<Weapon> {

    public WeaponDocumentInputProcessor() {
        super();
    }

    @Override
    public final Weapon process(final Document doc) {
        final Element skill;
        final Weapon weapon;
        final Element root;

        weapon = super.process(doc);

        root = doc.getRootElement();

        // Acquires the different sections
        skill = root.getChild(FileToken.SKILL);
        // weapon.setSkill(skill.getText());

        return weapon;
    }

}
