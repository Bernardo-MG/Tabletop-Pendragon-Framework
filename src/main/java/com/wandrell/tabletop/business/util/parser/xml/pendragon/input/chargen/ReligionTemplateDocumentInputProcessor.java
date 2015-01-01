package com.wandrell.tabletop.business.util.parser.xml.pendragon.input.chargen;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.business.conf.pendragon.ModelXMLConf;
import com.wandrell.tabletop.business.model.pendragon.chargen.ReligionTemplate;
import com.wandrell.tabletop.business.service.pendragon.ModelService;
import com.wandrell.util.parser.xml.input.JDOMDocumentInputProcessor;

public final class ReligionTemplateDocumentInputProcessor implements
        JDOMDocumentInputProcessor<ReligionTemplate> {

    private final ModelService modelService;

    public ReligionTemplateDocumentInputProcessor(final ModelService service) {
        super();

        modelService = service;
    }

    @Override
    public final ReligionTemplate process(final Document doc) {
        final Element root;
        final Element traitsNode;
        final Element armorNode;
        final Element damageNode;
        final Element damageDiceNode;
        final Element bonus;
        final Element derived;
        final String name;
        final Collection<String> traits;
        final Map<String, Integer> bonusDerived;
        final Integer bonusArmor;
        final Integer bonusDamage;
        final Integer bonusDamageDice;
        Integer value;

        root = doc.getRootElement();

        // Acquires the different sections
        traitsNode = root.getChild(ModelXMLConf.TRAITS);
        bonus = root.getChild(ModelXMLConf.BONUS);

        // Name
        name = root.getChildText(ModelXMLConf.NAME);

        // Traits
        traits = new LinkedList<>();
        for (final Element trait : traitsNode.getChildren()) {
            traits.add(trait.getText());
        }

        // Derived attributes bonus
        bonusDerived = new LinkedHashMap<>();
        derived = bonus.getChild(ModelXMLConf.DERIVED_ATTRIBUTES);
        if (derived != null) {
            for (final Element attribute : derived.getChildren()) {
                value = Integer.parseInt(attribute
                        .getChildText(ModelXMLConf.VALUE));
                bonusDerived.put(attribute.getChildText(ModelXMLConf.NAME),
                        value);
            }
        }

        // Armor bonus
        armorNode = bonus.getChild(ModelXMLConf.ARMOR_BONUS);
        if (armorNode != null) {
            bonusArmor = Integer.parseInt(armorNode.getText());
        } else {
            bonusArmor = 0;
        }

        // Armor bonus
        damageNode = bonus.getChild(ModelXMLConf.DAMAGE_BONUS);
        if (damageNode != null) {
            bonusDamage = Integer.parseInt(damageNode.getText());
        } else {
            bonusDamage = 0;
        }

        // Armor bonus
        damageDiceNode = bonus.getChild(ModelXMLConf.DAMAGE_DICE_BONUS);
        if (damageDiceNode != null) {
            bonusDamageDice = Integer.parseInt(damageDiceNode.getText());
        } else {
            bonusDamageDice = 0;
        }

        return getModelService().getReligionTemplate(name, traits,
                bonusDerived, bonusArmor, bonusDamage, bonusDamageDice);
    }

    private final ModelService getModelService() {
        return modelService;
    }

}
