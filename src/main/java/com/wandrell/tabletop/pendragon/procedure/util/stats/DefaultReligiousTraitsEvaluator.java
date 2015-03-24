package com.wandrell.tabletop.pendragon.procedure.util.stats;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.wandrell.tabletop.pendragon.model.character.PendragonHumanCharacter;
import com.wandrell.tabletop.pendragon.model.character.background.Religion;
import com.wandrell.tabletop.pendragon.model.character.stats.TraitsHolder;
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

        traits = getTraits(character.getTraits());
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

    private final Map<String, Integer> getTraits(final TraitsHolder holder) {
        final Map<String, Integer> traits;

        traits = new LinkedHashMap<>();
        traits.put("chaste", holder.getChaste());
        traits.put("lustful", holder.getLustful());
        traits.put("energetic", holder.getEnergetic());
        traits.put("lazy", holder.getLazy());
        traits.put("forgiving", holder.getForgiving());
        traits.put("vengeful", holder.getVengeful());
        traits.put("generous", holder.getGenerous());
        traits.put("selfish", holder.getSelfish());
        traits.put("honest", holder.getHonest());
        traits.put("deceitful", holder.getDeceitful());
        traits.put("just", holder.getJust());
        traits.put("arbitrary", holder.getArbitrary());
        traits.put("merciful", holder.getMerciful());
        traits.put("cruel", holder.getCruel());
        traits.put("modest", holder.getModest());
        traits.put("proud", holder.getProud());
        traits.put("pious", holder.getPious());
        traits.put("prudent", holder.getPrudent());
        traits.put("reckless", holder.getReckless());
        traits.put("temperate", holder.getTemperate());
        traits.put("indulgent", holder.getIndulgent());
        traits.put("trusting", holder.getTrusting());
        traits.put("suspicious", holder.getSuspicious());
        traits.put("valorous", holder.getValorous());
        traits.put("cowardly", holder.getCowardly());

        return traits;
    }

}
