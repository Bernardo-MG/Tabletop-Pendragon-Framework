package com.wandrell.tabletop.testing.pendragon.test.integration.stats;

import org.mockito.Matchers;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.tabletop.pendragon.model.character.DefaultPendragonHumanCharacter;
import com.wandrell.tabletop.pendragon.model.character.DerivedAttributeBuilder;
import com.wandrell.tabletop.pendragon.model.character.PendragonBaseCharacter;
import com.wandrell.tabletop.pendragon.model.character.PendragonHumanCharacter;
import com.wandrell.tabletop.pendragon.model.character.background.Religion;
import com.wandrell.tabletop.pendragon.service.ruleset.TraitsAchievementService;
import com.wandrell.tabletop.pendragon.valuebox.TraitsAchievementValueBox;
import com.wandrell.tabletop.valuebox.ValueBox;

public final class ITEventTraitsAchievementViewPoint {

    private final PendragonHumanCharacter  character;
    private final DerivedAttributeBuilder  derivedBuilder;

    private final TraitsAchievementService service;

    {
        derivedBuilder = Mockito.mock(DerivedAttributeBuilder.class);

        Mockito.when(
                derivedBuilder.getDamage(Matchers
                        .any(PendragonBaseCharacter.class))).thenReturn(
                Mockito.mock(ValueBox.class));
        Mockito.when(
                derivedBuilder.getDexterityRoll(Matchers
                        .any(PendragonBaseCharacter.class))).thenReturn(
                Mockito.mock(ValueBox.class));
        Mockito.when(
                derivedBuilder.getHealingRate(Matchers
                        .any(PendragonBaseCharacter.class))).thenReturn(
                Mockito.mock(ValueBox.class));
        Mockito.when(
                derivedBuilder.getHitPoints(Matchers
                        .any(PendragonBaseCharacter.class))).thenReturn(
                Mockito.mock(ValueBox.class));
        Mockito.when(
                derivedBuilder.getMajorWoundTreshold(Matchers
                        .any(PendragonBaseCharacter.class))).thenReturn(
                Mockito.mock(ValueBox.class));
        Mockito.when(
                derivedBuilder.getMoveRate(Matchers
                        .any(PendragonBaseCharacter.class))).thenReturn(
                Mockito.mock(ValueBox.class));
        Mockito.when(
                derivedBuilder.getUnconciousTreshold(Matchers
                        .any(PendragonBaseCharacter.class))).thenReturn(
                Mockito.mock(ValueBox.class));
        Mockito.when(
                derivedBuilder.getWeight(Matchers
                        .any(PendragonBaseCharacter.class))).thenReturn(
                Mockito.mock(ValueBox.class));
    }

    {
        final Religion religion;

        religion = Mockito.mock(Religion.class);

        character = new DefaultPendragonHumanCharacter("name", derivedBuilder,
                "culture", religion, "", "", "");

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

    public ITEventTraitsAchievementViewPoint() {
        super();
    }

    @Test
    public final void testTraitsSumChanges() {
        final ValueBox viewPoint;

        viewPoint = new TraitsAchievementValueBox(character, service);

        character.getTraits().setChaste(10);
        character.getTraits().setValorous(10);
        character.getTraits().setLazy(5);

        Assert.assertEquals(viewPoint.getValue(), (Integer) 25);

        character.getTraits().setLazy(10);

        Assert.assertEquals(viewPoint.getValue(), (Integer) 30);
    }

}
