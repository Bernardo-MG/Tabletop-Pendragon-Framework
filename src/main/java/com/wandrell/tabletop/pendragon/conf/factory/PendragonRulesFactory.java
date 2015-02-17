package com.wandrell.tabletop.pendragon.conf.factory;

import java.util.Map;
import java.util.TreeMap;

import com.wandrell.tabletop.interval.DefaultInterval;
import com.wandrell.tabletop.interval.DefaultIntervalTable;
import com.wandrell.tabletop.interval.Interval;
import com.wandrell.tabletop.interval.IntervalTable;
import com.wandrell.tabletop.util.comparator.interval.IntervalComparator;

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
            map = new TreeMap<Interval, Integer>(new IntervalComparator());
            map.put(new DefaultInterval(Integer.MIN_VALUE, 6), 3);
            map.put(new DefaultInterval(7, 9), 2);
            map.put(new DefaultInterval(10, 12), 1);
            map.put(new DefaultInterval(13, 16), 2);
            map.put(new DefaultInterval(17, Integer.MAX_VALUE), 3);

            featuresCount = new DefaultIntervalTable<Integer>(map);
        }

        return featuresCount;
    }

}
