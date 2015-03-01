package com.wandrell.tabletop.testing.pendragon.framework.test.unit.ruleset;

import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.wandrell.pattern.command.ReturnCommand;
import com.wandrell.tabletop.pendragon.service.ruleset.command.GetMoveRateCommand;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.factory.parameter.DerivedAttributesTestParameters;

public final class TestGetMoveRateCommand {

    protected static final String DATA = "data";

    @DataProvider(name = DATA)
    public final static Iterator<Object[]> getData() throws Exception {
        return DerivedAttributesTestParameters.getInstance().getMoveRate();
    }

    public TestGetMoveRateCommand() {
        super();
    }

    @Test(dataProvider = DATA)
    public final void testValid(final Integer dexterity,
            final Integer strength, final Integer expected) throws Exception {
        final ReturnCommand<Integer> command;

        command = new GetMoveRateCommand(dexterity, strength);

        Assert.assertEquals(command.execute(), expected);
    }

}
