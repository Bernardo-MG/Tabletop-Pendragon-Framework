package com.wandrell.tabletop.business.conf.pendragon.factory;

import java.util.Map;
import java.util.TreeMap;

import com.wandrell.tabletop.business.model.interval.DefaultInterval;
import com.wandrell.tabletop.business.model.interval.DefaultIntervalTable;
import com.wandrell.tabletop.business.model.interval.Interval;
import com.wandrell.tabletop.business.model.interval.IntervalTable;
import com.wandrell.tabletop.business.util.comparator.interval.IntervalComparator;

public final class PendragonRulesFactory {

    private static IntervalTable<Integer> featuresCount;
    private static PendragonRulesFactory  instance;

    public static final synchronized PendragonRulesFactory getInstance() {
        if (instance == null) {
            instance = new PendragonRulesFactory();
        }

        return instance;
    }

    private PendragonRulesFactory() {
        super();
    }

    public final synchronized IntervalTable<Integer> getFeaturesCountTable() {
        final Map<Interval, Integer> map;

        if (featuresCount == null) {
            // TODO: Load the configuration from a file
            map = new TreeMap<>(new IntervalComparator());
            map.put(new DefaultInterval(Integer.MIN_VALUE, 6), 3);
            map.put(new DefaultInterval(7, 9), 2);
            map.put(new DefaultInterval(10, 12), 1);
            map.put(new DefaultInterval(13, 16), 2);
            map.put(new DefaultInterval(17, Integer.MAX_VALUE), 3);

            featuresCount = new DefaultIntervalTable<>(map);
        }

        return featuresCount;
    }

}
