package com.wandrell.tabletop.pendragon.util.parser.dictionary.character.background;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.model.character.background.Religion;

public final class ReligionTemplateMapParser implements
        Parser<Religion, Map<String, Object>> {

    public ReligionTemplateMapParser() {
        super();
    }

    @Override
    public final Map<String, Object> parse(final Religion religion) {
        final Map<String, Object> data;

        data = new LinkedHashMap<String, Object>();

        data.put("name", religion.getName());
        if (!religion.getReligiousTraits().isEmpty()) {
            data.put("traits", religion.getReligiousTraits());
        }
        data.put("bonus", getBonus(religion));

        return data;
    }

    private final Map<String, Object> getBonus(final Religion religion) {
        final Map<String, Object> bonus;

        bonus = new LinkedHashMap<String, Object>();

        bonus.put("armor_bonus", religion.getArmorBonus());
        bonus.put("damage_bonus", religion.getDamageBonus());
        bonus.put("damage_dice_bonus", religion.getDamageDiceBonus());
        bonus.put("derived_attributes", getDerivedAttributes(religion));

        return bonus;
    }

    private final Collection<Map<String, Object>> getDerivedAttributes(
            final Religion religion) {
        final Collection<Map<String, Object>> derived;
        Map<String, Object> value;

        derived = new LinkedList<>();

        if (religion.getDerivedAttributeBonus().getDamage() > 0) {
            value = new LinkedHashMap<String, Object>();
            value.put("name", "damage");
            value.put("value", religion.getDerivedAttributeBonus().getDamage());

            derived.add(value);
        }
        if (religion.getDerivedAttributeBonus().getDexterityRoll() > 0) {
            value = new LinkedHashMap<String, Object>();
            value.put("name", "dexterity_roll");
            value.put("value", religion.getDerivedAttributeBonus()
                    .getDexterityRoll());

            derived.add(value);
        }
        if (religion.getDerivedAttributeBonus().getHealingRate() > 0) {
            value = new LinkedHashMap<String, Object>();
            value.put("name", "healing_rate");
            value.put("value", religion.getDerivedAttributeBonus()
                    .getHealingRate());

            derived.add(value);
        }
        if (religion.getDerivedAttributeBonus().getHitPoints() > 0) {
            value = new LinkedHashMap<String, Object>();
            value.put("name", "hitpoints");
            value.put("value", religion.getDerivedAttributeBonus()
                    .getHitPoints());

            derived.add(value);
        }
        if (religion.getDerivedAttributeBonus().getKnockdown() > 0) {
            value = new LinkedHashMap<String, Object>();
            value.put("name", "knockdown");
            value.put("value", religion.getDerivedAttributeBonus()
                    .getKnockdown());

            derived.add(value);
        }
        if (religion.getDerivedAttributeBonus().getMajorWoundTreshold() > 0) {
            value = new LinkedHashMap<String, Object>();
            value.put("name", "major_wound");
            value.put("value", religion.getDerivedAttributeBonus()
                    .getMajorWoundTreshold());

            derived.add(value);
        }
        if (religion.getDerivedAttributeBonus().getMoveRate() > 0) {
            value = new LinkedHashMap<String, Object>();
            value.put("name", "move_rate");
            value.put("value", religion.getDerivedAttributeBonus()
                    .getMoveRate());

            derived.add(value);
        }
        if (religion.getDerivedAttributeBonus().getUnconciousTreshold() > 0) {
            value = new LinkedHashMap<String, Object>();
            value.put("name", "unconcious_treshold");
            value.put("value", religion.getDerivedAttributeBonus()
                    .getUnconciousTreshold());

            derived.add(value);
        }
        if (religion.getDerivedAttributeBonus().getWeight() > 0) {
            value = new LinkedHashMap<String, Object>();
            value.put("name", "weight");
            value.put("value", religion.getDerivedAttributeBonus().getWeight());

            derived.add(value);
        }

        return derived;
    }

}
