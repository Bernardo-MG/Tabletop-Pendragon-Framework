package com.wandrell.tabletop.testing.pendragon.framework.test.unit.ruleset;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.pattern.command.DefaultCommandExecutor;
import com.wandrell.tabletop.pendragon.service.ruleset.ChivaldryService;
import com.wandrell.tabletop.pendragon.service.ruleset.DefaultChivaldryService;

public final class TestChivaldryService {

    private final ChivaldryService service;

    {
        service = new DefaultChivaldryService(new DefaultCommandExecutor());
    }

    public TestChivaldryService() {
        super();
    }

    @Test
    public final void testArmor() {
        Assert.assertEquals(service.getChivaldryArmorBonus(), (Integer) 3);
    }

    @Test
    public final void testTraits() {
        Assert.assertTrue(service.isBonusTrait("just"));
    }

    @Test
    public final void testTreshold() {
        Assert.assertEquals(service.getTraitThreshold(), (Integer) 80);
    }

}
