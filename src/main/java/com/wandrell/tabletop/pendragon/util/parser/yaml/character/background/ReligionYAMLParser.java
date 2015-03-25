package com.wandrell.tabletop.pendragon.util.parser.yaml.character.background;

import java.io.Reader;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.model.character.background.Religion;
import com.wandrell.tabletop.pendragon.service.model.ModelConstructorService;
import com.wandrell.tabletop.valuebox.DefaultSkillBox;
import com.wandrell.tabletop.valuebox.SkillBox;

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
        final Collection<SkillBox> bonusDerived;
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
        bonusDerived = new LinkedList<SkillBox>();
        derived = (Collection<Map<String, Object>>) bonus
                .get("derived_attributes");
        if (derived != null) {
            for (final Map<String, Object> attribute : derived) {
                bonusDerived.add(new DefaultSkillBox((String) attribute
                        .get("name"), (Integer) attribute.get("value"), 0,
                        Integer.MAX_VALUE));
            }
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

    private final ModelConstructorService getModelService() {
        return modelService;
    }

}
