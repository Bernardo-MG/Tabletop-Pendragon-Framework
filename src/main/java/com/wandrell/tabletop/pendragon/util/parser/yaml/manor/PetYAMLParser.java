package com.wandrell.tabletop.pendragon.util.parser.yaml.manor;

import java.io.Reader;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.interval.DefaultInterval;
import com.wandrell.tabletop.interval.DefaultIntervalTable;
import com.wandrell.tabletop.interval.Interval;
import com.wandrell.tabletop.interval.IntervalTable;
import com.wandrell.tabletop.pendragon.model.manor.AnimalYearResult;
import com.wandrell.tabletop.pendragon.model.manor.Pet;
import com.wandrell.tabletop.pendragon.service.model.ModelService;

public class PetYAMLParser implements Parser<Reader, Pet> {

    private final ModelService modelService;

    public PetYAMLParser(final ModelService service) {
        super();

        modelService = service;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final Pet parse(final Reader reader) {
        final Yaml yaml;
        final Map<String, Object> values;
        final Collection<Map<String, Object>> resultsMap;
        final String name;
        final IntervalTable<AnimalYearResult> results;
        final Map<Interval, AnimalYearResult> intervals;
        final List<Integer> limits;
        Integer pos;
        Interval interval;
        AnimalYearResult result;
        Map<String, Integer> moneyMap;
        String puppyName;
        String description;
        Integer libra;
        Integer denarii;
        Boolean dies;

        yaml = new Yaml();

        values = (Map<String, Object>) yaml.load(reader);

        name = (String) values.get("name");

        intervals = new LinkedHashMap<Interval, AnimalYearResult>();
        if (values.containsKey("year_results_table")) {
            resultsMap = (Collection<Map<String, Object>>) values
                    .get("year_results_table");

            if (resultsMap != null) {
                limits = new LinkedList<Integer>();
                for (final Map<String, Object> intervalMap : resultsMap) {
                    limits.add((Integer) intervalMap.get("lower_limit"));
                }

                pos = 0;
                for (final Map<String, Object> resultMap : resultsMap) {
                    description = (String) resultMap.get("description");

                    if (resultMap.containsKey("puppy_name")) {
                        puppyName = (String) resultMap.get("puppy_name");
                    } else {
                        puppyName = "";
                    }

                    if (resultMap.containsKey("dies")) {
                        dies = (Boolean) resultMap.get("dies");
                    } else {
                        dies = false;
                    }

                    moneyMap = (Map<String, Integer>) resultMap.get("money");
                    if (moneyMap != null) {
                        libra = moneyMap.get("libra");
                        denarii = moneyMap.get("denarii");
                    } else {
                        libra = 0;
                        denarii = 0;
                    }

                    result = getModelService().getAnimalYearResult(description,
                            puppyName, dies,
                            getModelService().getMoney(libra, denarii));

                    if (pos < (limits.size() - 1)) {
                        interval = new DefaultInterval(limits.get(pos),
                                limits.get(pos + 1) - 1);
                    } else {
                        interval = new DefaultInterval(limits.get(pos), 20);
                    }
                    pos++;

                    intervals.put(interval, result);
                }
            }
        }
        results = new DefaultIntervalTable<AnimalYearResult>(intervals);

        return getModelService().getPet(name, results);
    }

    private final ModelService getModelService() {
        return modelService;
    }

}
