package com.wandrell.tabletop.pendragon.model.character;

import com.wandrell.tabletop.pendragon.character.stats.derived.DamageValueBox;
import com.wandrell.tabletop.pendragon.character.stats.derived.DexterityRollValueBox;
import com.wandrell.tabletop.pendragon.character.stats.derived.HealingRateValueBox;
import com.wandrell.tabletop.pendragon.character.stats.derived.HitPointsValueBox;
import com.wandrell.tabletop.pendragon.character.stats.derived.KnockdownValueBox;
import com.wandrell.tabletop.pendragon.character.stats.derived.MajorWoundsValueBox;
import com.wandrell.tabletop.pendragon.character.stats.derived.MoveRateValueBox;
import com.wandrell.tabletop.pendragon.character.stats.derived.UnconciousValueBox;
import com.wandrell.tabletop.pendragon.character.stats.derived.WeightValueBox;
import com.wandrell.tabletop.pendragon.model.character.stats.AttributesHolder;
import com.wandrell.tabletop.pendragon.model.character.stats.DerivedAttributesHolder;
import com.wandrell.tabletop.pendragon.service.ruleset.DerivedAttributesService;
import com.wandrell.tabletop.stats.valuebox.ValueBox;

public final class DefaultDerivedAttributeBuilder implements
        DerivedAttributeBuilder {

    private final DerivedAttributesService derivedService;

    public DefaultDerivedAttributeBuilder(
            final DerivedAttributesService derivedService) {
        super();

        this.derivedService = derivedService;
    }

    @Override
    public final ValueBox getDamage(final AttributesHolder attributes,
            final DerivedAttributesHolder derived) {
        return new DamageValueBox(attributes, derived,
                getDerivedAttributesService());
    }

    @Override
    public final ValueBox getDexterityRoll(final AttributesHolder attributes,
            final DerivedAttributesHolder derived) {
        return new DexterityRollValueBox(attributes, derived,
                getDerivedAttributesService());
    }

    @Override
    public final ValueBox getHealingRate(final AttributesHolder attributes,
            final DerivedAttributesHolder derived) {
        return new HealingRateValueBox(attributes, derived,
                getDerivedAttributesService());
    }

    @Override
    public final ValueBox getHitPoints(final AttributesHolder attributes,
            final DerivedAttributesHolder derived) {
        return new HitPointsValueBox(attributes, derived,
                getDerivedAttributesService());
    }

    @Override
    public final ValueBox getKnockdown(final AttributesHolder attributes,
            final DerivedAttributesHolder derived) {
        return new KnockdownValueBox(attributes, derived,
                getDerivedAttributesService());
    }

    @Override
    public final ValueBox getMajorWoundTreshold(
            final AttributesHolder attributes,
            final DerivedAttributesHolder derived) {
        return new MajorWoundsValueBox(attributes, derived,
                getDerivedAttributesService());
    }

    @Override
    public final ValueBox getMoveRate(final AttributesHolder attributes,
            final DerivedAttributesHolder derived) {
        return new MoveRateValueBox(attributes, derived,
                getDerivedAttributesService());
    }

    @Override
    public final ValueBox getUnconciousTreshold(
            final AttributesHolder attributes,
            final DerivedAttributesHolder derived) {
        return new UnconciousValueBox(attributes, derived,
                getDerivedAttributesService());
    }

    @Override
    public final ValueBox getWeight(final AttributesHolder attributes,
            final DerivedAttributesHolder derived) {
        return new WeightValueBox(attributes, derived,
                getDerivedAttributesService());
    }

    private final DerivedAttributesService getDerivedAttributesService() {
        return derivedService;
    }

}
