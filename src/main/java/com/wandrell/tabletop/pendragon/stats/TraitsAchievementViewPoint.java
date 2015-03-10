package com.wandrell.tabletop.pendragon.stats;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.wandrell.tabletop.event.ValueChangeEvent;
import com.wandrell.tabletop.pendragon.model.character.PendragonHumanCharacter;
import com.wandrell.tabletop.pendragon.model.character.event.PendragonCharacterListenerAdapter;
import com.wandrell.tabletop.pendragon.service.ruleset.TraitsAchievementService;
import com.wandrell.tabletop.valuebox.derived.AbstractDerivedValueViewPoint;

public final class TraitsAchievementViewPoint extends
        AbstractDerivedValueViewPoint {

    private final PendragonHumanCharacter  character;
    private final TraitsAchievementService serviceTraits;
    private Integer                        sum;

    public TraitsAchievementViewPoint(final PendragonHumanCharacter character,
            final TraitsAchievementService service) {
        super();

        serviceTraits = service;
        this.character = character;

        sum = getSum();

        character
                .addPendragonCharacterListener(new PendragonCharacterListenerAdapter() {

                    @Override
                    public void traitChanged(final ValueChangeEvent event) {
                        sum = getSum();
                    }

                });
    }

    @Override
    public final Integer getValue() {
        return sum;
    }

    private final PendragonHumanCharacter getCharacter() {
        return character;
    }

    private final Integer getSum() {
        final Iterator<Entry<String, Integer>> itrTraits;
        Entry<String, Integer> trait;
        Integer total;

        itrTraits = getTraits(getCharacter()).entrySet().iterator();
        total = 0;
        while (itrTraits.hasNext()) {
            trait = itrTraits.next();
            if (getTraitsAchievementService().isBonusTrait(trait.getKey())) {
                total += trait.getValue();
            }
        }

        return total;
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

    private final TraitsAchievementService getTraitsAchievementService() {
        return serviceTraits;
    }

}
