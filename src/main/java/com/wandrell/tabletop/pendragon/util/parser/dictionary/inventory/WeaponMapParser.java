package com.wandrell.tabletop.pendragon.util.parser.dictionary.inventory;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.model.inventory.ArmorType;
import com.wandrell.tabletop.pendragon.model.inventory.RangedWeapon;
import com.wandrell.tabletop.pendragon.model.inventory.Weapon;

public final class WeaponMapParser implements
        Parser<Weapon, Map<String, Object>> {

    public WeaponMapParser() {
        super();
    }

    @Override
    public final Map<String, Object> parse(final Weapon value) {
        final Map<String, Object> data;
        final Map<String, Boolean> flags;
        final Collection<Map<String, Object>> armorBonus;
        final RangedWeapon ranged;
        Map<String, Object> bonus;

        data = new LinkedHashMap<String, Object>();
        flags = new LinkedHashMap<String, Boolean>();
        armorBonus = new LinkedList<>();

        flags.put("break_enemy_on_draw", value.isBreakingEnemyOnDraw());
        flags.put("break_on_fumble", value.isBreakingOnFumble());
        flags.put("hits_back", value.isHittingBack());
        flags.put("ignores_shield", value.isIgnoringShield());
        flags.put("reduce_shield_to_roll", value.isReducingShieldToRoll());

        for (final Entry<ArmorType, Integer> entry : value.getArmorBonusDice()
                .entrySet()) {
            bonus = new LinkedHashMap<String, Object>();

            bonus.put("armor_type", entry.getKey().toString().toLowerCase());
            bonus.put("dice_bonus", entry.getValue());

            armorBonus.add(bonus);
        }

        data.put("name", value.getName());
        data.put("skill", value.getSkill());
        data.put("two_handed", value.isTwoHanded());
        data.put("damage_bonus", value.getDamageBonus());
        data.put("dice_bonus", value.getDamageDiceBonus());

        data.put("damage_override", value.getDamageOverrideDice());

        if (!flags.isEmpty()) {
            data.put("flags", flags);
        }

        if (!armorBonus.isEmpty()) {
            data.put("vs_armor_bonus", armorBonus);
        }

        if (value instanceof RangedWeapon) {
            ranged = (RangedWeapon) value;

            data.put("range", ranged.getMaxRange());
            data.put("rate_of_fire", ranged.getRoundsToReload());
        }

        return data;
    }

}
