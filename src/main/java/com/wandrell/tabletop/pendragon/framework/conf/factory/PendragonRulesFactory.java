package com.wandrell.tabletop.pendragon.framework.conf.factory;

import com.wandrell.tabletop.dice.DefaultRollTable;
import com.wandrell.tabletop.dice.RollTable;

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
        if (featuresCount == null) {
            // TODO: Load the configuration from a file
            featuresCount = new DefaultRollTable<>("features_table");
            featuresCount.setInterval(Integer.MIN_VALUE, 6, 3);
            featuresCount.addInterval(9, 2);
            featuresCount.addInterval(12, 1);
            featuresCount.addInterval(16, 2);
            featuresCount.addInterval(Integer.MAX_VALUE, 3);
        }

        return featuresCount;
    }

}
