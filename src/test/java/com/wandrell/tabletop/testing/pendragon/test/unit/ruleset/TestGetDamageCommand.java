package com.wandrell.tabletop.testing.pendragon.test.unit.ruleset;

import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.wandrell.pattern.command.ReturnCommand;
import com.wandrell.tabletop.pendragon.service.ruleset.command.GetDamageCommand;
import com.wandrell.tabletop.testing.pendragon.framework.conf.factory.parameter.DerivedAttributesTestParameters;

public final class TestGetDamageCommand {

    protected static final String DATA = "data";

    @DataProvider(name = DATA)
    public final static Iterator<Object[]> getData() throws Exception {
        return DerivedAttributesTestParameters.getInstance().getDamage();
    }

    public TestGetDamageCommand() {
        super();
    }

    @Test(dataProvider = DATA)
    public final void testValid(final Integer size, final Integer strength,
            final Integer expected) throws Exception {
        final ReturnCommand<Integer> command;

        command = new GetDamageCommand(size, strength);

        Assert.assertEquals(command.execute(), expected);
    }

}
