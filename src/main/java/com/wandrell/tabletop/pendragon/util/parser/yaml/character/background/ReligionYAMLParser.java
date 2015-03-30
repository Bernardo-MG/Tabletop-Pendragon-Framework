package com.wandrell.tabletop.pendragon.util.parser.yaml.character.background;

import java.io.Reader;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.model.character.background.Religion;
import com.wandrell.tabletop.pendragon.model.character.stats.DerivedAttributesHolder;
import com.wandrell.tabletop.pendragon.model.character.stats.StaticDerivedAttributesHolder;
import com.wandrell.tabletop.pendragon.service.model.ModelConstructorService;

public final class ReligionYAMLParser implements Parser<Reader, Religion> {

    private final ModelConstructorService modelService;

    public ReligionYAMLParser(final ModelConstructorService service) {
        super();

        modelService = service;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final Religion parse(final Reader reader) {
        final Yaml yaml;
        final Map<String, Object> values;
        final Map<String, Object> bonus;
        final Collection<Map<String, Object>> derived;
        final String name;
        final Collection<String> traits;
        final DerivedAttributesHolder bonusDerived;
        final Integer bonusArmor;
        final Integer bonusDamage;
        final Integer bonusDamageDice;

        yaml = new Yaml();

        values = (Map<String, Object>) yaml.load(reader);

        // Name
        name = (String) values.get("name");

        // Acquires the different sections
        bonus = (Map<String, Object>) values.get("bonus");

        // Traits
        if ((values.containsKey("traits")) && (values.get("traits") != null)) {
            traits = (Collection<String>) values.get("traits");
        } else {
            traits = new LinkedList<>();
        }

        // Derived attributes bonus
        derived = (Collection<Map<String, Object>>) bonus
                .get("derived_attributes");
        if (derived != null) {
            bonusDerived = getDerivedValues(derived);
        } else {
            bonusDerived = new StaticDerivedAttributesHolder(0, 0, 0, 0, 0, 0,
                    0, 0, 0);
        }

        // Armor bonus
        if (bonus.get("armor_bonus") != null) {
            bonusArmor = (Integer) bonus.get("armor_bonus");
        } else {
            bonusArmor = 0;
        }

        // Armor bonus
        if (bonus.get("damage_bonus") != null) {
            bonusDamage = (Integer) bonus.get("damage_bonus");
        } else {
            bonusDamage = 0;
        }

        // Armor bonus
        if (bonus.get("damage_dice_bonus") != null) {
            bonusDamageDice = (Integer) bonus.get("damage_dice_bonus");
        } else {
            bonusDamageDice = 0;
        }

        return getModelService().getReligion(name, traits, bonusDerived,
                bonusArmor, bonusDamage, bonusDamageDice);
    }

    private final DerivedAttributesHolder getDerivedValues(
            final Collection<Map<String, Object>> derived) {
        Integer damage = 0;
        Integer dexterityRoll = 0;
        Integer healingRate = 0;
        Integer hitPoints = 0;
        Integer knockdown = 0;
        Integer majorWoundTreshold = 0;
        Integer moveRate = 0;
        Integer unconciousTreshold = 0;
        Integer weight = 0;

        for (final Map<String, Object> attribute : derived) {
            if (attribute.get("name").equals("damage")) {
                damage = (Integer) attribute.get("value");
            } else if (attribute.get("name").equals("dexterity_roll")) {
                dexterityRoll = (Integer) attribute.get("value");
            } else if (attribute.get("name").equals("healing_rate")) {
                healingRate = (Integer) attribute.get("value");
            } else if (attribute.get("name").equals("hitpoints")) {
                hitPoints = (Integer) attribute.get("value");
            } else if (attribute.get("name").equals("knockdown")) {
                knockdown = (Integer) attribute.get("value");
            } else if (attribute.get("name").equals("major_wound")) {
                majorWoundTreshold = (Integer) attribute.get("value");
            } else if (attribute.get("name").equals("move_rate")) {
                moveRate = (Integer) attribute.get("value");
            } else if (attribute.get("name").equals("unconcious_treshold")) {
                unconciousTreshold = (Integer) attribute.get("value");
            } else if (attribute.get("name").equals("weight")) {
                weight = (Integer) attribute.get("value");
            }
        }

        return new StaticDerivedAttributesHolder(damage, dexterityRoll,
                healingRate, hitPoints, knockdown, majorWoundTreshold,
                moveRate, unconciousTreshold, weight);
    }

    private final ModelConstructorService getModelService() {
        return modelService;
    }

}
