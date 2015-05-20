package com.wandrell.tabletop.pendragon.character.stats.derived;

import com.wandrell.tabletop.pendragon.model.character.event.PendragonCharacterListenerAdapter;
import com.wandrell.tabletop.pendragon.model.character.stats.AttributesHolder;
import com.wandrell.tabletop.pendragon.model.character.stats.DerivedAttributesHolder;
import com.wandrell.tabletop.pendragon.service.ruleset.DerivedAttributesService;
import com.wandrell.tabletop.stats.event.ValueChangeEvent;
import com.wandrell.tabletop.stats.valuebox.AbstractValueBoxEventFirer;

public final class HealingRateValueBox extends AbstractValueBoxEventFirer {

    private final AttributesHolder         attributes;
    private final DerivedAttributesHolder  derived;
    private final DerivedAttributesService derivedService;

    public HealingRateValueBox(final AttributesHolder attributes,
            final DerivedAttributesHolder derived,
            final DerivedAttributesService derivedService) {
        super();

        this.attributes = attributes;
        this.derived = derived;
        this.derivedService = derivedService;

        attributes
                .addAttributesListener(new PendragonCharacterListenerAdapter() {

                    @Override
                    public final void constitutionChanged(
                            final ValueChangeEvent event) {
                        fireValueChangedEvent(new ValueChangeEvent(this, 0, 0));
                    }

                    @Override
                    public final void strengthChanged(
                            final ValueChangeEvent event) {
                        fireValueChangedEvent(new ValueChangeEvent(this, 0, 0));
                    }

                });
    }

    @Override
    public final Integer getValue() {
        return derivedService.getHealingRate(attributes, derived);
    }

    @Override
    public final void setValue(final Integer value) {
        throw new UnsupportedOperationException("Setting the value is disabled");
    }

}
