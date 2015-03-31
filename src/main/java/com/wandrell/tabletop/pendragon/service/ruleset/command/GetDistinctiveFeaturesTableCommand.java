package com.wandrell.tabletop.pendragon.service.ruleset.command;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.wandrell.pattern.command.ResultCommand;
import com.wandrell.tabletop.interval.DefaultInterval;
import com.wandrell.tabletop.interval.DefaultIntervalTable;
import com.wandrell.tabletop.interval.Interval;
import com.wandrell.tabletop.interval.IntervalTable;
import com.wandrell.tabletop.pendragon.conf.FileConfig;
import com.wandrell.util.ResourceUtils;

public final class GetDistinctiveFeaturesTableCommand implements
        ResultCommand<IntervalTable<Integer>> {

    private IntervalTable<Integer> table;

    public GetDistinctiveFeaturesTableCommand() {
        super();
    }

    @SuppressWarnings("unchecked")
    @Override
    public final void execute() {
        final Yaml yaml;
        final Map<Interval, Integer> intervals;
        final Collection<Map<String, Object>> intervalsInfo;
        Integer limit;
        Interval interval;
        Map<String, Object> values;

        yaml = new Yaml();

        values = (Map<String, Object>) yaml.load(ResourceUtils
                .getClassPathReader(FileConfig.RULESET_CONFIG));

        intervals = new LinkedHashMap<>();

        values = (Map<String, Object>) values.get("distinctiveFeatures");
        intervalsInfo = (Collection<Map<String, Object>>) values
                .get("intervals");

        limit = (Integer) intervalsInfo.iterator().next().get("lowerLimit") - 1;
        intervals.put(new DefaultInterval(0, limit),
                (Integer) values.get("outOfTableValue"));
        for (final Map<String, Object> intervalInfo : intervalsInfo) {
            interval = new DefaultInterval(
                    (Integer) intervalInfo.get("lowerLimit"),
                    (Integer) intervalInfo.get("upperLimit"));
            intervals.put(interval, (Integer) intervalInfo.get("value"));

            limit = interval.getUpperLimit();
        }
        intervals.put(new DefaultInterval(limit + 1, Integer.MAX_VALUE),
                (Integer) values.get("outOfTableValue"));

        table = new DefaultIntervalTable<Integer>(intervals);
    }

    @Override
    public final IntervalTable<Integer> getResult() {
        return table;
    }

}
