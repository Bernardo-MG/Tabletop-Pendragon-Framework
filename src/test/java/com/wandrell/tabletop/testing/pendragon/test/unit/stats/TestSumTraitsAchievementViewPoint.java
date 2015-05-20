package com.wandrell.tabletop.testing.pendragon.test.unit.stats;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.tabletop.pendragon.model.character.PendragonCharacter;
import com.wandrell.tabletop.pendragon.model.character.stats.TraitsHolder;
import com.wandrell.tabletop.pendragon.service.ruleset.TraitsAchievementService;
import com.wandrell.tabletop.pendragon.valuebox.TraitsAchievementValueBox;
import com.wandrell.tabletop.stats.valuebox.ValueBox;

public final class TestSumTraitsAchievementViewPoint {

    private final PendragonCharacter       character;
    private final TraitsAchievementService service;

    {
        final TraitsHolder traits;

        traits = Mockito.mock(TraitsHolder.class);

        Mockito.when(traits.getChaste()).thenReturn(10);
        Mockito.when(traits.getValorous()).thenReturn(10);
        Mockito.when(traits.getLazy()).thenReturn(5);

        character = Mockito.mock(PendragonCharacter.class);

        Mockito.when(character.getTraits()).thenReturn(traits);

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
        final ValueBox viewPoint;

        viewPoint = new TraitsAchievementValueBox(character, service);

        Assert.assertEquals(viewPoint.getValue(), (Integer) 25);
    }

}
