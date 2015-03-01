package com.wandrell.tabletop.testing.pendragon.framework.test.unit.ruleset;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.pattern.command.DefaultCommandExecutor;
import com.wandrell.tabletop.pendragon.service.ruleset.GentlewomanService;
import com.wandrell.tabletop.pendragon.service.ruleset.TraitsAchievementService;

public final class TestGentlewomanService {

    private final TraitsAchievementService service;

    {
        service = new GentlewomanService(new DefaultCommandExecutor());
    }

    public TestGentlewomanService() {
        super();
    }

    @Test
    public final void testTraits() {
        Assert.assertTrue(service.isBonusTrait("modest"));
    }

    @Test
    public final void testTreshold() {
        Assert.assertEquals(service.getTraitThreshold(), (Integer) 80);
    }

}
