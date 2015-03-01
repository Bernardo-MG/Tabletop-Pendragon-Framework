package com.wandrell.tabletop.pendragon.util.parser.yaml.inventory;

import java.io.Reader;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.model.inventory.ArmorType;
import com.wandrell.tabletop.pendragon.model.inventory.Weapon;
import com.wandrell.tabletop.pendragon.service.model.ModelService;

public class WeaponYAMLParser implements Parser<Reader, Weapon> {

    private final ModelService modelService;

    public WeaponYAMLParser(final ModelService service) {
        super();

        modelService = service;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final Weapon parse(final Reader reader) {
        final Yaml yaml;
        final Map<String, Object> values;
        final Map<String, Boolean> flags;
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

        yaml = new Yaml();

        values = (Map<String, Object>) yaml.load(reader);

        name = (String) values.get("name");
        skill = (String) values.get("skill");
        twoHanded = (Boolean) values.get("two_handed");
        damageBonus = (Integer) values.get("damage_bonus");
        diceBonus = (Integer) values.get("dice_bonus");

        if (values.containsKey("flags")) {
            flags = (Map<String, Boolean>) values.get("flags");

            if (flags.containsKey("break_enemy_on_draw")) {
                breakEnemyDraw = flags.get("break_enemy_on_draw");
            } else {
                breakEnemyDraw = false;
            }
            if (flags.containsKey("break_on_fumble")) {
                breakFumble = flags.get("break_on_fumble");
            } else {
                breakFumble = false;
            }
            if (flags.containsKey("hits_back")) {
                hitsBack = flags.get("hits_back");
            } else {
                hitsBack = false;
            }
            if (flags.containsKey("ignores_shield")) {
                ignoresShield = flags.get("ignores_shield");
            } else {
                ignoresShield = false;
            }
            if (flags.containsKey("reduce_shield_to_roll")) {
                shieldToRoll = flags.get("reduce_shield_to_roll");
            } else {
                shieldToRoll = false;
            }
        } else {
            breakEnemyDraw = false;
            breakFumble = false;
            hitsBack = false;
            ignoresShield = false;
            shieldToRoll = false;
        }

        if (values.containsKey("damage_override")) {
            damageOverride = (Integer) values.get("damage_override");
        } else {
            damageOverride = 0;
        }

        if (values.containsKey("range")) {
            range = (Integer) values.get("range");
        } else {
            range = 0;
        }

        if (values.containsKey("rounds_to_reload")) {
            rof = (Integer) values.get("rounds_to_reload");
        } else {
            rof = 0;
        }

        armorBonus = new LinkedHashMap<ArmorType, Integer>();
        if (values.containsKey("vs_armor_bonus")) {
            for (final Map<String, Object> node : (Collection<Map<String, Object>>) values
                    .get("vs_armor_bonus")) {
                armorType = ArmorType.valueOf(((String) node.get("armor_type"))
                        .toUpperCase());
                value = (Integer) node.get("dice_bonus");
                armorBonus.put(armorType, value);
            }
        }

        return getModelService().getWeapon(name, "", skill, twoHanded,
                damageBonus, diceBonus, damageOverride, range, rof, armorBonus,
                breakEnemyDraw, breakFumble, hitsBack, ignoresShield,
                shieldToRoll);
    }

    private final ModelService getModelService() {
        return modelService;
    }
}
