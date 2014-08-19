package com.wandrell.tabletop.pendragon.framework.conf.factory;

import java.util.Map;
import java.util.TreeMap;

import com.wandrell.tabletop.dice.DefaultRollTable;
import com.wandrell.tabletop.dice.RollTable;
import com.wandrell.tabletop.interval.ContrastInterval;
import com.wandrell.tabletop.interval.DefaultInterval;
import com.wandrell.tabletop.interval.IntervalComparator;

public final class PendragonRulesFactory {

    private static DefaultRollTable<Integer> featuresCount;
    private static PendragonRulesFactory     instance;

    public static final synchronized PendragonRulesFactory getInstance() {
        if (instance == null) {
            instance = new PendragonRulesFactory();
        }

        return instance;
    }

    private PendragonRulesFactory() {
        super();
    }

    public final synchronized RollTable<Integer> getFeaturesCountTable() {
        final Map<ContrastInterval, Integer> map;

        if (featuresCount == null) {
            // TODO: Load the configuration from a file
            map = new TreeMap<>(new IntervalComparator());
            map.put(new DefaultInterval(Integer.MIN_VALUE, 6), 3);
            map.put(new DefaultInterval(7, 9), 2);
            map.put(new DefaultInterval(10, 12), 1);
            map.put(new DefaultInterval(13, 16), 2);
            map.put(new DefaultInterval(17, Integer.MAX_VALUE), 3);

            featuresCount = new DefaultRollTable<>("features_table", map);
        }

        return featuresCount;
    }

}
