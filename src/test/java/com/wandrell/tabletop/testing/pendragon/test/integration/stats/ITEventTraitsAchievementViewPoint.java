package com.wandrell.tabletop.testing.pendragon.test.integration.stats;

import org.mockito.Matchers;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.tabletop.pendragon.model.character.DefaultPendragonCharacter;
import com.wandrell.tabletop.pendragon.model.character.DerivedAttributeBuilder;
import com.wandrell.tabletop.pendragon.model.character.PendragonCharacter;
import com.wandrell.tabletop.pendragon.model.character.stats.AttributesHolder;
import com.wandrell.tabletop.pendragon.model.character.stats.DerivedAttributesHolder;
import com.wandrell.tabletop.pendragon.service.ruleset.TraitsAchievementService;
import com.wandrell.tabletop.pendragon.valuebox.TraitsAchievementValueBox;
import com.wandrell.tabletop.valuebox.ValueBox;

public final class ITEventTraitsAchievementViewPoint {

    private final PendragonCharacter       character;
    private final DerivedAttributeBuilder  derivedBuilder;

    private final TraitsAchievementService service;

    {
        derivedBuilder = Mockito.mock(DerivedAttributeBuilder.class);

        Mockito.when(
                derivedBuilder.getDamage(Matchers.any(AttributesHolder.class),
                        Matchers.any(DerivedAttributesHolder.class)))
                .thenReturn(Mockito.mock(ValueBox.class));
        Mockito.when(
                derivedBuilder.getDexterityRoll(
                        Matchers.any(AttributesHolder.class),
                        Matchers.any(DerivedAttributesHolder.class)))
                .thenReturn(Mockito.mock(ValueBox.class));
        Mockito.when(
                derivedBuilder.getHealingRate(
                        Matchers.any(AttributesHolder.class),
                        Matchers.any(DerivedAttributesHolder.class)))
                .thenReturn(Mockito.mock(ValueBox.class));
        Mockito.when(
                derivedBuilder.getHitPoints(
                        Matchers.any(AttributesHolder.class),
                        Matchers.any(DerivedAttributesHolder.class)))
                .thenReturn(Mockito.mock(ValueBox.class));
        Mockito.when(
                derivedBuilder.getKnockdown(
                        Matchers.any(AttributesHolder.class),
                        Matchers.any(DerivedAttributesHolder.class)))
                .thenReturn(Mockito.mock(ValueBox.class));
        Mockito.when(
                derivedBuilder.getMajorWoundTreshold(
                        Matchers.any(AttributesHolder.class),
                        Matchers.any(DerivedAttributesHolder.class)))
                .thenReturn(Mockito.mock(ValueBox.class));
        Mockito.when(
                derivedBuilder.getMoveRate(
                        Matchers.any(AttributesHolder.class),
                        Matchers.any(DerivedAttributesHolder.class)))
                .thenReturn(Mockito.mock(ValueBox.class));
        Mockito.when(
                derivedBuilder.getUnconciousTreshold(
                        Matchers.any(AttributesHolder.class),
                        Matchers.any(DerivedAttributesHolder.class)))
                .thenReturn(Mockito.mock(ValueBox.class));
        Mockito.when(
                derivedBuilder.getWeight(Matchers.any(AttributesHolder.class),
                        Matchers.any(DerivedAttributesHolder.class)))
                .thenReturn(Mockito.mock(ValueBox.class));
    }

    {
        character = new DefaultPendragonCharacter("name", derivedBuilder);

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
