package com.wandrell.tabletop.testing.pendragon.test.unit.procedure.glory;

import org.mockito.Matchers;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.tabletop.pendragon.model.character.PendragonBaseCharacter;
import com.wandrell.tabletop.pendragon.model.character.PendragonHumanCharacter;
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
        final PendragonBaseCharacter character;

        evaluatorTraits = Mockito.mock(TraitsAchievementEvaluator.class);

        Mockito.when(
                evaluatorTraits.isFulfilling(Matchers
                        .any(PendragonHumanCharacter.class))).thenReturn(true);

        evaluatorGlory = new TraitsAchievementGloryEvaluator("name", 100,
                evaluatorTraits);

        character = Mockito.mock(PendragonHumanCharacter.class);

        Assert.assertTrue(evaluatorGlory.isApplicable(character));
    }

    @Test
    public final void testIsFulfilling_InvalidClass_NotFulfills() {
        final GloryEvaluator evaluatorGlory;
        final TraitsAchievementEvaluator evaluatorTraits;
        final PendragonBaseCharacter character;

        evaluatorTraits = Mockito.mock(TraitsAchievementEvaluator.class);

        Mockito.when(
                evaluatorTraits.isFulfilling(Matchers
                        .any(PendragonHumanCharacter.class))).thenReturn(true);

        evaluatorGlory = new TraitsAchievementGloryEvaluator("name", 100,
                evaluatorTraits);

        character = Mockito.mock(PendragonBaseCharacter.class);

        Assert.assertTrue(!evaluatorGlory.isApplicable(character));
    }

    @Test
    public final void testIsFulfilling_NotFulfills() {
        final GloryEvaluator evaluatorGlory;
        final TraitsAchievementEvaluator evaluatorTraits;
        final PendragonBaseCharacter character;

        evaluatorTraits = Mockito.mock(TraitsAchievementEvaluator.class);

        Mockito.when(
                evaluatorTraits.isFulfilling(Matchers
                        .any(PendragonHumanCharacter.class))).thenReturn(false);

        evaluatorGlory = new TraitsAchievementGloryEvaluator("name", 100,
                evaluatorTraits);

        character = Mockito.mock(PendragonHumanCharacter.class);

        Assert.assertTrue(!evaluatorGlory.isApplicable(character));
    }

}
