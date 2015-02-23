package com.wandrell.tabletop.pendragon.util.parser.dictionary.chargen;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.dice.Dice;
import com.wandrell.tabletop.interval.Interval;
import com.wandrell.tabletop.pendragon.model.character.Horse;
import com.wandrell.tabletop.pendragon.model.chargen.AdditionalBelongings;
import com.wandrell.tabletop.pendragon.model.chargen.AdditionalBelongingsTable;
import com.wandrell.tabletop.pendragon.model.inventory.Item;
import com.wandrell.tabletop.pendragon.model.manor.Pet;

public final class AdditionalBelongingsMapParser implements
        Parser<AdditionalBelongingsTable, Map<String, Object>> {

    public AdditionalBelongingsMapParser() {
        super();
    }

    @Override
    public final Map<String, Object>
            parse(final AdditionalBelongingsTable value) {
        final Map<String, Object> data;
        final Collection<Map<String, Object>> belongings;
        Map<String, Object> belongingsData;
        Map<String, Object> belongingsRow;
        Map<String, Object> dataMap;
        Collection<Object> dataCol;

        data = new LinkedHashMap<String, Object>();
        belongings = new LinkedList<Map<String, Object>>();

        for (final Entry<Interval, AdditionalBelongings> row : value
                .getIntervals().entrySet()) {
            belongingsRow = new LinkedHashMap<>();
            belongingsData = new LinkedHashMap<>();

            belongings.add(belongingsRow);

            belongingsRow.put("lower_limit", row.getKey().getLowerLimit());
            belongingsRow.put("belongings", belongingsData);

            belongingsData.put("has_to_choose", row.getValue().hasToChoose());

            if (!row.getValue().getMoneyName().isEmpty()) {
                dataMap = new LinkedHashMap<>();

                dataMap.put("name", row.getValue().getMoneyName());
                dataMap.put("libra", row.getValue().getMoney().getLibra());
                dataMap.put("denarii", row.getValue().getMoney().getDenarii());

                belongingsData.put("money", dataMap);
            }

            if (!row.getValue().getRerollTable().isEmpty()) {
                dataMap = new LinkedHashMap<>();
                dataCol = new LinkedList<>();

                dataMap.put("reroll_table", row.getValue().getRerollTable());

                for (final Dice dice : row.getValue().getRerolls()) {
                    dataCol.add(dice.getTextValue());
                }
                dataMap.put("rerolls", dataCol);

                belongingsData.put("reroll", dataMap);
            }

            if (!row.getValue().getHorses().isEmpty()) {
                dataCol = new LinkedList<>();

                for (final Horse horse : row.getValue().getHorses()) {
                    dataCol.add(horse.getHorseType());
                }

                belongingsData.put("horses", dataCol);
            }

            if (!row.getValue().getItems().isEmpty()) {
                dataCol = new LinkedList<>();

                for (final Item item : row.getValue().getItems()) {
                    dataCol.add(item.getName());
                }

                belongingsData.put("items", dataCol);
            }

            if (!row.getValue().getShields().isEmpty()) {
                dataCol = new LinkedList<>();

                for (final Item item : row.getValue().getShields()) {
                    dataCol.add(item.getName());
                }

                belongingsData.put("shields", dataCol);
            }

            if (!row.getValue().getWeapons().isEmpty()) {
                dataCol = new LinkedList<>();

                for (final Item item : row.getValue().getWeapons()) {
                    dataCol.add(item.getName());
                }

                belongingsData.put("weapons", dataCol);
            }

            if (!row.getValue().getPets().isEmpty()) {
                dataCol = new LinkedList<>();

                for (final Pet pet : row.getValue().getPets()) {
                    dataCol.add(pet.getName());
                }

                belongingsData.put("pets", dataCol);
            }
        }

        data.put("name", value.getName());
        data.put("belongings_table", belongings);

        return data;
    }

}
