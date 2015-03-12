package com.wandrell.tabletop.pendragon.util.parser.dictionary.inventory;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.model.inventory.armor.ArmorType;
import com.wandrell.tabletop.pendragon.model.inventory.weapon.RangedWeapon;
import com.wandrell.tabletop.pendragon.model.inventory.weapon.Weapon;

public final class WeaponMapParser implements
        Parser<Weapon, Map<String, Object>> {

    public WeaponMapParser() {
        super();
    }

    @Override
    public final Map<String, Object> parse(final Weapon weapon) {
        final Map<String, Object> data;
        final Collection<Map<String, Object>> armorBonus;
        final RangedWeapon ranged;
        final Map<String, Boolean> flags;

        data = new LinkedHashMap<String, Object>();

        data.put("name", weapon.getName());
        data.put("skill", weapon.getSkill());
        data.put("two_handed", weapon.isTwoHanded());
        data.put("damage_bonus", weapon.getDamageBonus());
        data.put("dice_bonus", weapon.getDamageDiceBonus());
        data.put("damage_override", weapon.getDamageOverrideDice());

        if (weapon instanceof RangedWeapon) {
            ranged = (RangedWeapon) weapon;

            data.put("range", ranged.getMaxRange());
            data.put("rounds_to_reload", ranged.getRoundsToReload());
        }

        flags = getFlags(weapon);
        if (!flags.isEmpty()) {
            data.put("flags", flags);
        }

        armorBonus = getArmorBonus(weapon);
        if (!armorBonus.isEmpty()) {
            data.put("vs_armor_bonus", armorBonus);
        }

        return data;
    }

    private final Collection<Map<String, Object>> getArmorBonus(
            final Weapon weapon) {
        final Collection<Map<String, Object>> armorBonus;
        Map<String, Object> bonus;

        armorBonus = new LinkedList<>();

        for (final Entry<ArmorType, Integer> entry : weapon.getArmorBonusDice()
                .entrySet()) {
            bonus = new LinkedHashMap<String, Object>();

            bonus.put("armor_type", entry.getKey().toString().toLowerCase());
            bonus.put("dice_bonus", entry.getValue());

            armorBonus.add(bonus);
        }

        return armorBonus;
    }

    private final Map<String, Boolean> getFlags(final Weapon weapon) {
        final Map<String, Boolean> flags;

        flags = new LinkedHashMap<String, Boolean>();

        if (weapon.isBreakingEnemyOnDraw()) {
            flags.put("break_enemy_on_draw", weapon.isBreakingEnemyOnDraw());
        }
        if (weapon.isBreakingOnFumble()) {
            flags.put("break_on_fumble", weapon.isBreakingOnFumble());
        }
        if (weapon.isHittingBack()) {
            flags.put("hits_back", weapon.isHittingBack());
        }
        if (weapon.isIgnoringShield()) {
            flags.put("ignores_shield", weapon.isIgnoringShield());
        }
        if (weapon.isReducingShieldToRoll()) {
            flags.put("reduce_shield_to_roll", weapon.isReducingShieldToRoll());
        }

        return flags;
    }

}
