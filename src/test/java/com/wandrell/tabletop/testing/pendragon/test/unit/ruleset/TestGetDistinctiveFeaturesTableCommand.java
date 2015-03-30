package com.wandrell.tabletop.testing.pendragon.test.unit.ruleset;

import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.wandrell.tabletop.interval.IntervalTable;
import com.wandrell.tabletop.pendragon.service.ruleset.command.GetDistinctiveFeaturesTableCommand;
import com.wandrell.tabletop.testing.pendragon.framework.conf.factory.parameter.DerivedAttributesTestParameters;

public final class TestGetDistinctiveFeaturesTableCommand {

    protected static final String  DATA = "data";
    private IntervalTable<Integer> table;

    @DataProvider(name = DATA)
    public final static Iterator<Object[]> getData() throws Exception {
        return DerivedAttributesTestParameters.getInstance()
                .getDistinctiveFeatures();
    }

    public TestGetDistinctiveFeaturesTableCommand() {
        super();
    }

    @BeforeClass
    public final void initalize() {
        final GetDistinctiveFeaturesTableCommand command;

        command = new GetDistinctiveFeaturesTableCommand();

        command.execute();

        table = command.getResult();
    }

    @Test(dataProvider = DATA)
    public final void
            testValid(final Integer appearance, final Integer expected)
                    throws Exception {
        Assert.assertEquals(table.getValue(appearance), expected);
    }

}
