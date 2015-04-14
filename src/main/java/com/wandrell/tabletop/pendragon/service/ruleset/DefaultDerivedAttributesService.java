package com.wandrell.tabletop.pendragon.service.ruleset;

import static com.google.common.base.Preconditions.checkNotNull;

import com.wandrell.pattern.command.CommandExecutor;
import com.wandrell.tabletop.interval.IntervalTable;
import com.wandrell.tabletop.pendragon.model.character.stats.AttributesHolder;
import com.wandrell.tabletop.pendragon.model.character.stats.DerivedAttributesHolder;
import com.wandrell.tabletop.pendragon.service.ruleset.command.GetDamageCommand;
import com.wandrell.tabletop.pendragon.service.ruleset.command.GetDistinctiveFeaturesTableCommand;
import com.wandrell.tabletop.pendragon.service.ruleset.command.GetHealingRateCommand;
import com.wandrell.tabletop.pendragon.service.ruleset.command.GetHitPointsCommand;
import com.wandrell.tabletop.pendragon.service.ruleset.command.GetMoveRateCommand;
import com.wandrell.tabletop.pendragon.service.ruleset.command.GetUnconciousCommand;
import com.wandrell.tabletop.pendragon.service.ruleset.command.GetWeightCommand;

public final class DefaultDerivedAttributesService implements
        DerivedAttributesService {

    private final CommandExecutor  comExec;
    private IntervalTable<Integer> tableFeatures;

    public DefaultDerivedAttributesService(final CommandExecutor executor) {
        super();

        checkNotNull(executor, "Received a null pointer as command executor");

        comExec = executor;
    }

    @Override
    public final Integer getDamage(final AttributesHolder attributes,
            final DerivedAttributesHolder derived) {
        return getCommandExecutor().execute(
                new GetDamageCommand(attributes.getSize(), attributes
                        .getStrength()));
    }

    @Override
    public final Integer getDexterityRoll(final AttributesHolder attributes,
            final DerivedAttributesHolder derived) {
        return attributes.getDexterity();
    }

    @Override
    public final Integer getDistinctiveFeatures(
            final AttributesHolder attributes,
            final DerivedAttributesHolder derived) {
        if (tableFeatures == null) {
            tableFeatures = getCommandExecutor().execute(
                    new GetDistinctiveFeaturesTableCommand());
        }

        return tableFeatures.getValue(attributes.getAppearance());
    }

    @Override
    public final Integer getHealingRate(final AttributesHolder attributes,
            final DerivedAttributesHolder derived) {
        return getCommandExecutor().execute(
                new GetHealingRateCommand(attributes.getConstitution(),
                        attributes.getStrength()));
    }

    @Override
    public final Integer getHitPoints(final AttributesHolder attributes,
            final DerivedAttributesHolder derived) {
        return getCommandExecutor().execute(
                new GetHitPointsCommand(attributes.getConstitution(),
                        attributes.getSize()));
    }

    @Override
    public final Integer getKnockdown(final AttributesHolder attributes,
            final DerivedAttributesHolder derived) {
        return attributes.getSize();
    }

    @Override
    public final Integer getMajorWound(final AttributesHolder attributes,
            final DerivedAttributesHolder derived) {
        return attributes.getConstitution();
    }

    @Override
    public final Integer getMoveRate(final AttributesHolder attributes,
            final DerivedAttributesHolder derived) {
        return getCommandExecutor().execute(
                new GetMoveRateCommand(attributes.getDexterity(), attributes
                        .getStrength()));
    }

    @Override
    public final Integer getUnconcious(final AttributesHolder attributes,
            final DerivedAttributesHolder derived) {
        return getCommandExecutor().execute(
                new GetUnconciousCommand(derived.getHitPoints()));
    }

    @Override
    public final Integer getWeight(final AttributesHolder attributes,
            final DerivedAttributesHolder derived) {
        return getCommandExecutor().execute(
                new GetWeightCommand(attributes.getSize()));
    }

    private final CommandExecutor getCommandExecutor() {
        return comExec;
    }

}
