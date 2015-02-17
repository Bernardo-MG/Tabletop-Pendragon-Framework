package com.wandrell.tabletop.pendragon.util.parser.xml.input.character;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.conf.ModelXMLConf;
import com.wandrell.tabletop.pendragon.model.character.Horse;
import com.wandrell.tabletop.pendragon.service.ModelService;

public class HorseDocumentParser implements Parser<Document, Horse> {

    private final ModelService modelService;

    public HorseDocumentParser(final ModelService service) {
        super();

        modelService = service;
    }

    @Override
    public final Horse parse(final Document doc) {
        final Element root;
        final Element attributesNode;
        final Element derivedNode;
        final Element flagsNode;
        final String race;
        final Integer constitution;
        final Integer dexterity;
        final Integer size;
        final Integer strength;
        final Integer damage;
        final Integer movement;
        final Integer armor;
        final Boolean armored;
        final Boolean combat;
        final Boolean hunting;

        root = doc.getRootElement();

        race = root.getChildText(ModelXMLConf.TYPE);

        attributesNode = root.getChild(ModelXMLConf.ATTRIBUTES);
        derivedNode = root.getChild(ModelXMLConf.DERIVED_ATTRIBUTES);
        flagsNode = root.getChild(ModelXMLConf.FLAGS);

        constitution = Integer.valueOf(attributesNode
                .getChildText(ModelXMLConf.CONSTITUTION));
        dexterity = Integer.valueOf(attributesNode
                .getChildText(ModelXMLConf.DEXTERITY));
        size = Integer.valueOf(attributesNode.getChildText(ModelXMLConf.SIZE));
        strength = Integer.valueOf(attributesNode
                .getChildText(ModelXMLConf.STRENGTH));

        damage = Integer.valueOf(derivedNode.getChildText(ModelXMLConf.DAMAGE));
        movement = Integer.valueOf(derivedNode
                .getChildText(ModelXMLConf.MOVEMENT_RATE));

        armor = Integer.valueOf(root.getChildText(ModelXMLConf.NATURAL_ARMOR));

        armored = Boolean.valueOf(flagsNode.getChildText(ModelXMLConf.ARMORED));
        combat = Boolean.valueOf(flagsNode.getChildText(ModelXMLConf.COMBAT));
        hunting = Boolean.valueOf(flagsNode.getChildText(ModelXMLConf.HUNTING));

        return getModelService().getHorse(race, constitution, dexterity, size,
                strength, damage, movement, armor, armored, combat, hunting);
    }

    private final ModelService getModelService() {
        return modelService;
    }

}
