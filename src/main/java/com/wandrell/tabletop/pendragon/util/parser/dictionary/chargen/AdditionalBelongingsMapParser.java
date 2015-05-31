package com.wandrell.tabletop.pendragon.util.parser.dictionary.chargen;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.dice.notation.DiceFormula;
import com.wandrell.tabletop.interval.Interval;
import com.wandrell.tabletop.pendragon.model.character.Horse;
import com.wandrell.tabletop.pendragon.model.chargen.inventory.AdditionalBelongings;
import com.wandrell.tabletop.pendragon.model.chargen.inventory.AdditionalBelongingsTable;
import com.wandrell.tabletop.pendragon.model.inventory.Item;
import com.wandrell.tabletop.pendragon.model.manor.Pet;

public final class AdditionalBelongingsMapParser implements
        Parser<AdditionalBelongingsTable, Map<String, Object>> {

    public AdditionalBelongingsMapParser() {
        super();
    }

    @Override
    public final Map<String, Object>
            parse(final AdditionalBelongingsTable table) {
        final Map<String, Object> data;

        data = new LinkedHashMap<String, Object>();

        data.put("name", table.getName());
        data.put("belongings_table", getBelongings(table));

        return data;
    }

    private final Collection<Map<String, Object>> getBelongings(
            final AdditionalBelongingsTable table) {
        final Collection<Map<String, Object>> belongings;
        Map<String, Object> belongingsRow;

        belongings = new LinkedList<Map<String, Object>>();

        for (final Entry<Interval, AdditionalBelongings> row : table
                .getIntervals().entrySet()) {
            belongingsRow = new LinkedHashMap<>();

            belongingsRow.put("lower_limit", row.getKey().getLowerLimit());
            belongingsRow.put("belongings", getRowBelongings(row.getValue()));

            belongings.add(belongingsRow);
        }

        return belongings;
    }

    private final Map<String, Object> getRowBelongings(
            final AdditionalBelongings belongings) {
        final Map<String, Object> belongingsData;
        Map<String, Object> group;
        Collection<Object> values;

        belongingsData = new LinkedHashMap<>();

        // Has to choose flag
        belongingsData.put("has_to_choose", belongings.hasToChoose());

        // Money
        if (!belongings.getMoneyName().isEmpty()) {
            group = new LinkedHashMap<>();

            group.put("name", belongings.getMoneyName());
            group.put("libra", belongings.getMoney().getLibra());
            group.put("denarii", belongings.getMoney().getDenarii());

            belongingsData.put("money", group);
        }

        // Reroll table
        if (!belongings.getRerollTable().isEmpty()) {
            group = new LinkedHashMap<>();
            values = new LinkedList<>();

            group.put("reroll_table", belongings.getRerollTable());

            for (final DiceFormula dice : belongings.getRerolls()) {
                values.add(dice.getPrintableText());
            }
            group.put("rerolls", values);

            belongingsData.put("reroll", group);
        }

        // Horses
        if (!belongings.getHorses().isEmpty()) {
            values = new LinkedList<>();

            for (final Horse horse : belongings.getHorses()) {
                values.add(horse.getHorseType());
            }

            belongingsData.put("horses", values);
        }

        // Items
        if (!belongings.getItems().isEmpty()) {
            values = new LinkedList<>();

            for (final Item item : belongings.getItems()) {
                values.add(item.getName());
            }

            belongingsData.put("items", values);
        }

        // Shields
        if (!belongings.getShields().isEmpty()) {
            values = new LinkedList<>();

            for (final Item item : belongings.getShields()) {
                values.add(item.getName());
            }

            belongingsData.put("shields", values);
        }

        // Weapons
        if (!belongings.getWeapons().isEmpty()) {
            values = new LinkedList<>();

            for (final Item item : belongings.getWeapons()) {
                values.add(item.getName());
            }

            belongingsData.put("weapons", values);
        }

        // Pets
        if (!belongings.getPets().isEmpty()) {
            values = new LinkedList<>();

            for (final Pet pet : belongings.getPets()) {
                values.add(pet.getName());
            }

            belongingsData.put("pets", values);
        }

        return belongingsData;
    }

}
