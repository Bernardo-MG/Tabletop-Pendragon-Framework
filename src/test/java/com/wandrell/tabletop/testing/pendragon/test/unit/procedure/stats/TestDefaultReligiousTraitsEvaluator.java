package com.wandrell.tabletop.testing.pendragon.test.unit.procedure.stats;

import java.util.Collection;
import java.util.LinkedList;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.tabletop.pendragon.model.character.PendragonCharacter;
import com.wandrell.tabletop.pendragon.model.character.background.Religion;
import com.wandrell.tabletop.pendragon.model.character.stats.TraitsHolder;
import com.wandrell.tabletop.pendragon.procedure.stats.ReligiousTraitsEvaluator;
import com.wandrell.tabletop.pendragon.procedure.util.stats.DefaultReligiousTraitsEvaluator;
import com.wandrell.tabletop.pendragon.service.ruleset.ReligiousService;

public final class TestDefaultReligiousTraitsEvaluator {

    private final ReligiousTraitsEvaluator evaluator;
    private final Religion                 religion;

    {
        final ReligiousService service;
        final Collection<String> traits;

        service = new ReligiousService() {

            @Override
            public Integer getReligiousThreshold() {
                return 15;
            }

        };

        evaluator = new DefaultReligiousTraitsEvaluator(service);

        religion = Mockito.mock(Religion.class);

        traits = new LinkedList<>();

        traits.add("chaste");
        traits.add("valorous");
        traits.add("lazy");

        Mockito.when(religion.getReligiousTraits()).thenReturn(traits);
    }

    public TestDefaultReligiousTraitsEvaluator() {
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

        Assert.assertTrue(evaluator.isFulfilling(character, religion));
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

        Assert.assertTrue(evaluator.isFulfilling(character, religion));
    }

}
