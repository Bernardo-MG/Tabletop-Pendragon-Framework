package com.wandrell.tabletop.pendragon.util.parser.yaml.chargen;

import java.io.Reader;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.model.chargen.ReligionTemplate;
import com.wandrell.tabletop.pendragon.service.model.ModelConstructorService;

public final class ReligionTemplateYAMLParser implements
        Parser<Reader, ReligionTemplate> {

    private final ModelConstructorService modelService;

    public ReligionTemplateYAMLParser(final ModelConstructorService service) {
        super();

        modelService = service;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final ReligionTemplate parse(final Reader reader) {
        final Yaml yaml;
        final Map<String, Object> values;
        final Map<String, Object> bonus;
        final Collection<Map<String, Object>> derived;
        final String name;
        final Collection<String> traits;
        final Map<String, Integer> bonusDerived;
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
        bonusDerived = new LinkedHashMap<String, Integer>();
        derived = (Collection<Map<String, Object>>) bonus
                .get("derived_attributes");
        if (derived != null) {
            for (final Map<String, Object> attribute : derived) {
                bonusDerived.put((String) attribute.get("name"),
                        (Integer) attribute.get("value"));
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

        return getModelService().getReligionTemplate(name, traits,
                bonusDerived, bonusArmor, bonusDamage, bonusDamageDice);
    }

    private final ModelConstructorService getModelService() {
        return modelService;
    }

}
