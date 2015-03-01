package com.wandrell.tabletop.pendragon.service.ruleset;

import static com.google.common.base.Preconditions.checkNotNull;

import com.wandrell.pattern.command.CommandExecutor;
import com.wandrell.tabletop.interval.IntervalTable;
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
    public final Integer getDamage(final Integer appearance,
            final Integer constitution, final Integer dexterity,
            final Integer size, final Integer strength) {
        return getCommandExecutor().execute(
                new GetDamageCommand(size, strength));
    }

    @Override
    public final Integer getDistinctiveFeatures(final Integer appearance,
            final Integer constitution, final Integer dexterity,
            final Integer size, final Integer strength) {
        if (tableFeatures == null) {
            tableFeatures = getCommandExecutor().execute(
                    new GetDistinctiveFeaturesTableCommand());
        }

        return tableFeatures.getValue(appearance);
    }

    @Override
    public final Integer getHealingRate(final Integer appearance,
            final Integer constitution, final Integer dexterity,
            final Integer size, final Integer strength) {
        return getCommandExecutor().execute(
                new GetHealingRateCommand(constitution, strength));
    }

    @Override
    public final Integer getHitPoints(final Integer appearance,
            final Integer constitution, final Integer dexterity,
            final Integer size, final Integer strength) {
        return getCommandExecutor().execute(
                new GetHitPointsCommand(constitution, size));
    }

    @Override
    public final Integer getKnockdown(final Integer appearance,
            final Integer constitution, final Integer dexterity,
            final Integer size, final Integer strength) {
        return size;
    }

    @Override
    public final Integer getMajorWound(final Integer appearance,
            final Integer constitution, final Integer dexterity,
            final Integer size, final Integer strength) {
        return constitution;
    }

    @Override
    public final Integer getMoveRate(final Integer appearance,
            final Integer constitution, final Integer dexterity,
            final Integer size, final Integer strength) {
        return getCommandExecutor().execute(
                new GetMoveRateCommand(dexterity, strength));
    }

    @Override
    public final Integer getUnconcious(final Integer appearance,
            final Integer constitution, final Integer dexterity,
            final Integer size, final Integer strength) {
        return getCommandExecutor().execute(
                new GetUnconciousCommand(constitution, size));
    }

    @Override
    public final Integer getWeight(final Integer appearance,
            final Integer constitution, final Integer dexterity,
            final Integer size, final Integer strength) {
        return getCommandExecutor().execute(new GetWeightCommand(size));
    }

    private final CommandExecutor getCommandExecutor() {
        return comExec;
    }

}
