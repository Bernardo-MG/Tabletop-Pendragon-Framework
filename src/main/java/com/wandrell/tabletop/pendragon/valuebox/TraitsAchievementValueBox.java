package com.wandrell.tabletop.pendragon.valuebox;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.wandrell.tabletop.pendragon.model.character.PendragonCharacter;
import com.wandrell.tabletop.pendragon.model.character.event.PendragonCharacterListenerAdapter;
import com.wandrell.tabletop.pendragon.model.character.stats.TraitsHolder;
import com.wandrell.tabletop.pendragon.service.ruleset.TraitsAchievementService;
import com.wandrell.tabletop.stats.event.ValueChangeEvent;
import com.wandrell.tabletop.stats.valuebox.AbstractValueBoxEventFirer;

public final class TraitsAchievementValueBox extends AbstractValueBoxEventFirer {

    private final PendragonCharacter       character;
    private final TraitsAchievementService serviceTraits;
    private Integer                        sum;

    public TraitsAchievementValueBox(final PendragonCharacter character,
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

    @Override
    public final void setValue(final Integer value) {
        throw new UnsupportedOperationException("Setting the value is disabled");
    }

    private final PendragonCharacter getCharacter() {
        return character;
    }

    private final Integer getSum() {
        final Iterator<Entry<String, Integer>> itrTraits;
        Entry<String, Integer> trait;
        Integer total;

        itrTraits = getTraits(getCharacter().getTraits()).entrySet().iterator();
        total = 0;
        while (itrTraits.hasNext()) {
            trait = itrTraits.next();
            if (getTraitsAchievementService().isBonusTrait(trait.getKey())) {
                total += trait.getValue();
            }
        }

        return total;
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

    private final TraitsAchievementService getTraitsAchievementService() {
        return serviceTraits;
    }

}
