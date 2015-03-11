package com.wandrell.tabletop.testing.pendragon.test.unit.procedure.stats;

import java.util.Collection;
import java.util.LinkedList;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.tabletop.pendragon.model.character.PendragonHumanCharacter;
import com.wandrell.tabletop.pendragon.model.chargen.ReligionTemplate;
import com.wandrell.tabletop.pendragon.procedure.stats.ReligiousTraitsEvaluator;
import com.wandrell.tabletop.pendragon.procedure.util.stats.DefaultReligiousTraitsEvaluator;
import com.wandrell.tabletop.pendragon.service.ruleset.ReligiousService;

public final class TestDefaultReligiousTraitsEvaluator {

    private final ReligiousTraitsEvaluator evaluator;
    private final ReligionTemplate         religion;

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

        religion = Mockito.mock(ReligionTemplate.class);

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
        final PendragonHumanCharacter character;

        character = Mockito.mock(PendragonHumanCharacter.class);

        Mockito.when(character.getChaste()).thenReturn(10);
        Mockito.when(character.getValorous()).thenReturn(10);
        Mockito.when(character.getLazy()).thenReturn(5);

        Assert.assertTrue(evaluator.isFulfilling(character, religion));
    }

    @Test
    public final void testIsFulfilling_NotFulfills() {
        final PendragonHumanCharacter character;

        character = Mockito.mock(PendragonHumanCharacter.class);

        Mockito.when(character.getChaste()).thenReturn(10);
        Mockito.when(character.getValorous()).thenReturn(10);
        Mockito.when(character.getLazy()).thenReturn(4);

        Assert.assertTrue(evaluator.isFulfilling(character, religion));
    }

}
