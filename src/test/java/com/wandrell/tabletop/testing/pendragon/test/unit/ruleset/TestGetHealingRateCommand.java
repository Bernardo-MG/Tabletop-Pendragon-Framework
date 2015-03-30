package com.wandrell.tabletop.testing.pendragon.test.unit.ruleset;

import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.wandrell.pattern.command.ReturnCommand;
import com.wandrell.tabletop.pendragon.service.ruleset.command.GetHealingRateCommand;
import com.wandrell.tabletop.testing.pendragon.framework.conf.factory.parameter.DerivedAttributesTestParameters;

public final class TestGetHealingRateCommand {

    protected static final String DATA = "data";

    @DataProvider(name = DATA)
    public final static Iterator<Object[]> getData() throws Exception {
        return DerivedAttributesTestParameters.getInstance().getHealingRate();
    }

    public TestGetHealingRateCommand() {
        super();
    }

    @Test(dataProvider = DATA)
    public final void testValid(final Integer constitution,
            final Integer strength, final Integer expected) throws Exception {
        final ReturnCommand<Integer> command;

        command = new GetHealingRateCommand(constitution, strength);

        command.execute();
        Assert.assertEquals(command.getResult(), expected);
    }

}
