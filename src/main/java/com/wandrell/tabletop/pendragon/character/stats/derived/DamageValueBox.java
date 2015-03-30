package com.wandrell.tabletop.pendragon.character.stats.derived;

import com.wandrell.tabletop.event.ValueChangeEvent;
import com.wandrell.tabletop.pendragon.model.character.event.PendragonCharacterListenerAdapter;
import com.wandrell.tabletop.pendragon.model.character.stats.AttributesHolder;
import com.wandrell.tabletop.pendragon.model.character.stats.DerivedAttributesHolder;
import com.wandrell.tabletop.pendragon.service.ruleset.DerivedAttributesService;
import com.wandrell.tabletop.valuebox.AbstractValueBox;

public final class DamageValueBox extends AbstractValueBox {

    private final AttributesHolder         attributes;
    private final DerivedAttributesHolder  derived;
    private final DerivedAttributesService derivedService;

    public DamageValueBox(final AttributesHolder attributes,
            final DerivedAttributesHolder derived,
            final DerivedAttributesService derivedService) {
        super();

        this.attributes = attributes;
        this.derived = derived;
        this.derivedService = derivedService;

        attributes
                .addAttributesListener(new PendragonCharacterListenerAdapter() {

                    @Override
                    public final void sizeChanged(final ValueChangeEvent event) {
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
    public final DamageValueBox createNewInstance() {
        return new DamageValueBox(attributes, derived, derivedService);
    }

    @Override
    public final Integer getValue() {
        return derivedService.getDamage(attributes, derived);
    }
}
