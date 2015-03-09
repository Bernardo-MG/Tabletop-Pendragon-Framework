package com.wandrell.tabletop.pendragon.stats;

import com.wandrell.tabletop.event.ValueChangeEvent;
import com.wandrell.tabletop.pendragon.model.character.PendragonBaseCharacter;
import com.wandrell.tabletop.pendragon.model.character.event.PendragonCharacterListener;
import com.wandrell.tabletop.pendragon.service.ruleset.DerivedAttributesService;
import com.wandrell.tabletop.valuebox.derived.AbstractDerivedValueViewPoint;

public final class UnconciousViewPoint extends AbstractDerivedValueViewPoint {

    private final PendragonBaseCharacter   character;
    private final DerivedAttributesService derivedService;

    public UnconciousViewPoint(final PendragonBaseCharacter character,
            final DerivedAttributesService derivedService) {
        super();

        this.character = character;
        this.derivedService = derivedService;

        character
                .addPendragonCharacterListener(new PendragonCharacterListener() {

                    @Override
                    public final void attributesChanged(
                            final ValueChangeEvent event) {
                        fireValueChangedEvent(new ValueChangeEvent(this, 0, 0));
                    }

                });
    }

    @Override
    public final Integer getValue() {
        return derivedService.getUnconcious(character);
    }

}
