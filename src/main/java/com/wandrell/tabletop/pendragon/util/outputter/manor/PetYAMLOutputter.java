package com.wandrell.tabletop.pendragon.util.outputter.manor;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import com.wandrell.tabletop.interval.Interval;
import com.wandrell.tabletop.pendragon.model.manor.AnimalYearResult;
import com.wandrell.tabletop.pendragon.model.manor.Pet;
import com.wandrell.tabletop.pendragon.util.outputter.YAMLOutputter;

public final class PetYAMLOutputter extends YAMLOutputter<Pet> {

    public PetYAMLOutputter() {
        super();
    }

    @Override
    protected final Map<String, Object> buildMap(final Pet value) {
        final Map<String, Object> data;
        final Collection<Map<String, Object>> year_results;
        Map<String, Object> year_result;
        Map<String, Object> money;

        data = new LinkedHashMap<String, Object>();
        year_results = new LinkedList<>();

        data.put("name", value.getName());
        data.put("year_results_table", year_results);

        for (final Entry<Interval, AnimalYearResult> row : value
                .getAnnualCheckMap().getIntervals().entrySet()) {
            year_result = new LinkedHashMap<String, Object>();

            year_result.put("lower_limit", row.getKey().getLowerLimit());
            year_result.put("description", row.getValue().getDescription());

            if (!row.getValue().getPuppy().isEmpty()) {
                year_result.put("puppy_name", row.getValue().getPuppy());
            }

            if (row.getValue().isDying()) {
                year_result.put("dies", row.getValue().isDying());
            }

            if ((row.getValue().getMoney().getLibra() > 0)
                    && (row.getValue().getMoney().getDenarii() > 0)) {
                money = new LinkedHashMap<String, Object>();
                money.put("libra", row.getValue().getMoney().getLibra());
                money.put("denarii", row.getValue().getMoney().getDenarii());

                year_result.put("money", money);
            }

            year_results.add(year_result);
        }

        return data;
    }

}
