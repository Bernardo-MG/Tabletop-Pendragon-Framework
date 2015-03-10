package com.wandrell.tabletop.testing.pendragon.test.unit.stats;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.tabletop.pendragon.model.character.PendragonHumanCharacter;
import com.wandrell.tabletop.pendragon.service.ruleset.TraitsAchievementService;
import com.wandrell.tabletop.pendragon.stats.TraitsAchievementViewPoint;
import com.wandrell.tabletop.valuebox.derived.DerivedValueViewPoint;

public final class TestSumTraitsAchievementViewPoint {

    private final PendragonHumanCharacter  character;
    private final TraitsAchievementService service;

    {
        character = Mockito.mock(PendragonHumanCharacter.class);

        Mockito.when(character.getChaste()).thenReturn(10);
        Mockito.when(character.getValorous()).thenReturn(10);
        Mockito.when(character.getLazy()).thenReturn(5);

        service = new TraitsAchievementService() {

            @Override
            public final Integer getTraitThreshold() {
                return null;
            }

            @Override
            public final Boolean isBonusTrait(final String trait) {
                return trait.equals("chaste") || trait.equals("valorous")
                        || trait.equals("lazy");
            }

        };
    }

    public TestSumTraitsAchievementViewPoint() {
        super();
    }

    @Test
    public final void testTraitsSum() {
        final DerivedValueViewPoint viewPoint;

        viewPoint = new TraitsAchievementViewPoint(character, service);

        Assert.assertEquals(viewPoint.getValue(), (Integer) 25);
    }

}
