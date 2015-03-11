package com.wandrell.tabletop.testing.pendragon.test.unit.procedure.stats;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.tabletop.pendragon.model.character.PendragonHumanCharacter;
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
        final PendragonHumanCharacter character;

        character = Mockito.mock(PendragonHumanCharacter.class);

        Mockito.when(character.getChaste()).thenReturn(10);
        Mockito.when(character.getValorous()).thenReturn(10);
        Mockito.when(character.getLazy()).thenReturn(5);

        Assert.assertTrue(evaluator.isFulfilling(character));
    }

    @Test
    public final void testIsFulfilling_NotFulfills() {
        final PendragonHumanCharacter character;

        character = Mockito.mock(PendragonHumanCharacter.class);

        Mockito.when(character.getChaste()).thenReturn(10);
        Mockito.when(character.getValorous()).thenReturn(10);
        Mockito.when(character.getLazy()).thenReturn(4);

        Assert.assertTrue(!evaluator.isFulfilling(character));
    }

}
