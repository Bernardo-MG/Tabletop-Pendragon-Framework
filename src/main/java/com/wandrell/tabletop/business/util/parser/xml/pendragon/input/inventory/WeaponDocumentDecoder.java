package com.wandrell.tabletop.business.util.parser.xml.pendragon.input.inventory;

import java.util.LinkedHashMap;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.pattern.parser.xml.input.JDOMDocumentDecoder;
import com.wandrell.tabletop.business.conf.pendragon.ModelXMLConf;
import com.wandrell.tabletop.business.model.pendragon.inventory.ArmorType;
import com.wandrell.tabletop.business.model.pendragon.inventory.Weapon;
import com.wandrell.tabletop.business.service.pendragon.ModelService;

public class WeaponDocumentDecoder implements JDOMDocumentDecoder<Weapon> {

    private final ModelService modelService;

    public WeaponDocumentDecoder(final ModelService service) {
        super();

        modelService = service;
    }

    @Override
    public final Weapon decode(final Document doc) {
        final Element root;
        final Element flags;
        final Element damageOverrideNode;
        final Element rangeNode;
        final Element rofNode;
        final Element armorBonusNode;
        final String name;
        final String skill;
        final Boolean twoHanded;
        final Integer damageBonus;
        final Integer diceBonus;
        final Boolean breakEnemyDraw;
        final Boolean breakFumble;
        final Boolean hitsBack;
        final Boolean ignoresShield;
        final Boolean shieldToRoll;
        final Integer damageOverride;
        final Integer range;
        final Integer rof;
        final Map<ArmorType, Integer> armorBonus;
        ArmorType armorType;
        Integer value;

        root = doc.getRootElement();

        name = root.getChildText(ModelXMLConf.NAME);
        skill = root.getChildText(ModelXMLConf.SKILL);
        twoHanded = Boolean.valueOf(root.getChildText(ModelXMLConf.TWO_HANDED));
        damageBonus = Integer.valueOf(root
                .getChildText(ModelXMLConf.DAMAGE_BONUS));
        diceBonus = Integer.valueOf(root.getChildText(ModelXMLConf.DICE_BONUS));

        flags = root.getChild(ModelXMLConf.FLAGS);

        breakEnemyDraw = Boolean.parseBoolean(flags
                .getChildText(ModelXMLConf.BREAK_ENEMY_ON_DRAW));
        breakFumble = Boolean.parseBoolean(flags
                .getChildText(ModelXMLConf.BREAK_ON_FUMBLE));
        hitsBack = Boolean.parseBoolean(flags
                .getChildText(ModelXMLConf.HITS_BACK));
        ignoresShield = Boolean.parseBoolean(flags
                .getChildText(ModelXMLConf.IGNORES_SHIELD));
        shieldToRoll = Boolean.parseBoolean(flags
                .getChildText(ModelXMLConf.REDUCE_SHIELD_TO_ROLL));

        damageOverrideNode = root.getChild(ModelXMLConf.DAMAGE_OVERRIDE);
        if (damageOverrideNode == null) {
            damageOverride = 0;
        } else {
            damageOverride = Integer.valueOf(damageOverrideNode.getText());
        }

        rangeNode = root.getChild(ModelXMLConf.RANGE);
        if (rangeNode == null) {
            range = 0;
        } else {
            range = Integer.valueOf(rangeNode.getText());
        }

        rofNode = root.getChild(ModelXMLConf.RATE_OF_FIRE);
        if (rofNode == null) {
            rof = 0;
        } else {
            rof = Integer.valueOf(rofNode.getText());
        }

        armorBonus = new LinkedHashMap<>();
        armorBonusNode = root.getChild(ModelXMLConf.VS_ARMOR_BONUS);
        if (armorBonusNode != null) {
            for (final Element node : armorBonusNode.getChildren()) {
                armorType = ArmorType.valueOf(node.getChildText(
                        ModelXMLConf.ARMOR_TYPE).toUpperCase());
                value = Integer.valueOf(node
                        .getChildText(ModelXMLConf.DICE_BONUS));
                armorBonus.put(armorType, value);
            }
        }

        return getModelService().getWeapon(name, "",
                getModelService().getMoney(0, 0), skill, twoHanded,
                damageBonus, diceBonus, damageOverride, range, rof, armorBonus,
                breakEnemyDraw, breakFumble, hitsBack, ignoresShield,
                shieldToRoll);
    }

    private final ModelService getModelService() {
        return modelService;
    }
}
