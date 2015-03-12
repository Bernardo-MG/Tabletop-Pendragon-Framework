package com.wandrell.tabletop.pendragon.procedure.util.stats;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.wandrell.tabletop.pendragon.model.character.PendragonHumanCharacter;
import com.wandrell.tabletop.pendragon.model.character.background.Religion;
import com.wandrell.tabletop.pendragon.procedure.stats.ReligiousTraitsEvaluator;
import com.wandrell.tabletop.pendragon.service.ruleset.ReligiousService;

public final class DefaultReligiousTraitsEvaluator implements
        ReligiousTraitsEvaluator {

    private final ReligiousService religiousService;

    public DefaultReligiousTraitsEvaluator(final ReligiousService service) {
        super();

        religiousService = service;
    }

    @Override
    public final Boolean isFulfilling(final PendragonHumanCharacter character,
            final Religion religion) {
        final Map<String, Integer> traits;
        final Iterator<Entry<String, Integer>> itrTraits;
        Entry<String, Integer> trait;
        Integer sum;

        traits = getTraits(character);
        itrTraits = traits.entrySet().iterator();
        sum = 0;
        while (itrTraits.hasNext()) {
            trait = itrTraits.next();
            if (religion.getReligiousTraits().contains(trait.getKey())) {
                sum += trait.getValue();
            }
        }

        return (sum >= getReligiousService().getReligiousThreshold());
    }

    private final ReligiousService getReligiousService() {
        return religiousService;
    }

    private final Map<String, Integer> getTraits(
            final PendragonHumanCharacter character) {
        final Map<String, Integer> traits;

        traits = new LinkedHashMap<>();
        traits.put("chaste", character.getChaste());
        traits.put("lustful", character.getLustful());
        traits.put("energetic", character.getEnergetic());
        traits.put("lazy", character.getLazy());
        traits.put("forgiving", character.getForgiving());
        traits.put("vengeful", character.getVengeful());
        traits.put("generous", character.getGenerous());
        traits.put("selfish", character.getSelfish());
        traits.put("honest", character.getHonest());
        traits.put("deceitful", character.getDeceitful());
        traits.put("just", character.getJust());
        traits.put("arbitrary", character.getArbitrary());
        traits.put("merciful", character.getMerciful());
        traits.put("cruel", character.getCruel());
        traits.put("modest", character.getModest());
        traits.put("proud", character.getProud());
        traits.put("pious", character.getPious());
        traits.put("prudent", character.getPrudent());
        traits.put("reckless", character.getReckless());
        traits.put("temperate", character.getTemperate());
        traits.put("indulgent", character.getIndulgent());
        traits.put("trusting", character.getTrusting());
        traits.put("suspicious", character.getSuspicious());
        traits.put("valorous", character.getValorous());
        traits.put("cowardly", character.getCowardly());

        return traits;
    }

}
