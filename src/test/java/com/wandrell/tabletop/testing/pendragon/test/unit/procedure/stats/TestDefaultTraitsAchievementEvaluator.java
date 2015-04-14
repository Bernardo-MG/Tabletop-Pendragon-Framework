package com.wandrell.tabletop.testing.pendragon.test.unit.procedure.stats;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.tabletop.pendragon.model.character.PendragonCharacter;
import com.wandrell.tabletop.pendragon.model.character.stats.TraitsHolder;
import com.wandrell.tabletop.pendragon.procedure.stats.TraitsAchievementEvaluator;
import com.wandrell.tabletop.pendragon.procedure.util.stats.DefaultTraitsAchievementEvaluator;
import com.wandrell.tabletop.pendragon.service.ruleset.TraitsAchievementService;

public final class TestDefaultTraitsAchievementEvaluator {

    private final TraitsAchievementEvaluator evaluator;

    {
        final TraitsAchievementService service;

        service = new TraitsAchievementService() {

            @Override
            public final Integer getTraitThreshold() {
                return 5;
            }

            @Override
            public final Boolean isBonusTrait(final String trait) {
                return trait.equals("chaste") || trait.equals("valorous")
                        || trait.equals("lazy");
            }

        };

        evaluator = new DefaultTraitsAchievementEvaluator(service);
    }

    public TestDefaultTraitsAchievementEvaluator() {
        super();
    }

    @Test
    public final void testIsFulfilling_Fulfills() {
        final TraitsHolder traits;
        final PendragonCharacter character;

        traits = Mockito.mock(TraitsHolder.class);
        character = Mockito.mock(PendragonCharacter.class);

        Mockito.when(traits.getChaste()).thenReturn(10);
        Mockito.when(traits.getValorous()).thenReturn(10);
        Mockito.when(traits.getLazy()).thenReturn(5);

        Mockito.when(character.getTraits()).thenReturn(traits);

        Assert.assertTrue(evaluator.isFulfilling(character));
    }

    @Test
    public final void testIsFulfilling_NotFulfills() {
        final TraitsHolder traits;
        final PendragonCharacter character;

        traits = Mockito.mock(TraitsHolder.class);
        character = Mockito.mock(PendragonCharacter.class);

        Mockito.when(traits.getChaste()).thenReturn(10);
        Mockito.when(traits.getValorous()).thenReturn(10);
        Mockito.when(traits.getLazy()).thenReturn(4);

        Mockito.when(character.getTraits()).thenReturn(traits);

        Assert.assertTrue(!evaluator.isFulfilling(character));
    }

}
