package com.wandrell.tabletop.testing.pendragon.test.unit.procedure.glory;

import org.mockito.Matchers;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.tabletop.pendragon.model.character.PendragonCharacter;
import com.wandrell.tabletop.pendragon.procedure.glory.GloryEvaluator;
import com.wandrell.tabletop.pendragon.procedure.stats.TraitsAchievementEvaluator;
import com.wandrell.tabletop.pendragon.procedure.util.glory.TraitsAchievementGloryEvaluator;

public final class TestTraitsAchievementGloryEvaluator {

    public TestTraitsAchievementGloryEvaluator() {
        super();
    }

    @Test
    public final void testIsFulfilling_Fulfills() {
        final GloryEvaluator evaluatorGlory;
        final TraitsAchievementEvaluator evaluatorTraits;
        final PendragonCharacter character;

        evaluatorTraits = Mockito.mock(TraitsAchievementEvaluator.class);

        Mockito.when(
                evaluatorTraits.isFulfilling(Matchers
                        .any(PendragonCharacter.class))).thenReturn(true);

        evaluatorGlory = new TraitsAchievementGloryEvaluator("name", 100,
                evaluatorTraits);

        character = Mockito.mock(PendragonCharacter.class);

        Assert.assertTrue(evaluatorGlory.isApplicable(character));
    }

    @Test
    public final void testIsFulfilling_NotFulfills() {
        final GloryEvaluator evaluatorGlory;
        final TraitsAchievementEvaluator evaluatorTraits;
        final PendragonCharacter character;

        evaluatorTraits = Mockito.mock(TraitsAchievementEvaluator.class);

        Mockito.when(
                evaluatorTraits.isFulfilling(Matchers
                        .any(PendragonCharacter.class))).thenReturn(false);

        evaluatorGlory = new TraitsAchievementGloryEvaluator("name", 100,
                evaluatorTraits);

        character = Mockito.mock(PendragonCharacter.class);

        Assert.assertTrue(!evaluatorGlory.isApplicable(character));
    }

}
