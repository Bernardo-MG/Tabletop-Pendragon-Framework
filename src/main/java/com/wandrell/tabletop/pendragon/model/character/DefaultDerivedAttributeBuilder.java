package com.wandrell.tabletop.pendragon.model.character;

import com.wandrell.tabletop.pendragon.service.ruleset.DerivedAttributesService;
import com.wandrell.tabletop.pendragon.stats.DamageViewPoint;
import com.wandrell.tabletop.pendragon.stats.DexterityRollViewPoint;
import com.wandrell.tabletop.pendragon.stats.HealingRateViewPoint;
import com.wandrell.tabletop.pendragon.stats.HitPointsViewPoint;
import com.wandrell.tabletop.pendragon.stats.MajorWoundsViewPoint;
import com.wandrell.tabletop.pendragon.stats.MoveRateViewPoint;
import com.wandrell.tabletop.pendragon.stats.UnconciousViewPoint;
import com.wandrell.tabletop.pendragon.stats.WeightViewPoint;
import com.wandrell.tabletop.valuebox.ValueBox;
import com.wandrell.tabletop.valuebox.derived.DerivedValueBox;

public final class DefaultDerivedAttributeBuilder implements
        DerivedAttributeBuilder {

    private final DerivedAttributesService derivedService;

    public DefaultDerivedAttributeBuilder(
            final DerivedAttributesService derivedService) {
        super();

        this.derivedService = derivedService;
    }

    @Override
    public final ValueBox getDamage(final PendragonBaseCharacter character) {
        return new DerivedValueBox(new DamageViewPoint(character,
                getDerivedAttributesService()));
    }

    @Override
    public final ValueBox getDexterityRoll(
            final PendragonBaseCharacter character) {
        return new DerivedValueBox(new DexterityRollViewPoint(character,
                getDerivedAttributesService()));
    }

    @Override
    public final ValueBox
            getHealingRate(final PendragonBaseCharacter character) {
        return new DerivedValueBox(new HealingRateViewPoint(character,
                getDerivedAttributesService()));
    }

    @Override
    public final ValueBox getHitPoints(final PendragonBaseCharacter character) {
        return new DerivedValueBox(new HitPointsViewPoint(character,
                getDerivedAttributesService()));
    }

    @Override
    public final ValueBox getMajorWoundTreshold(
            final PendragonBaseCharacter character) {
        return new DerivedValueBox(new MajorWoundsViewPoint(character,
                getDerivedAttributesService()));
    }

    @Override
    public final ValueBox getMoveRate(final PendragonBaseCharacter character) {
        return new DerivedValueBox(new MoveRateViewPoint(character,
                getDerivedAttributesService()));
    }

    @Override
    public final ValueBox getUnconciousTreshold(
            final PendragonBaseCharacter character) {
        return new DerivedValueBox(new UnconciousViewPoint(character,
                getDerivedAttributesService()));
    }

    @Override
    public final ValueBox getWeight(final PendragonBaseCharacter character) {
        return new DerivedValueBox(new WeightViewPoint(character,
                getDerivedAttributesService()));
    }

    private final DerivedAttributesService getDerivedAttributesService() {
        return derivedService;
    }

}
