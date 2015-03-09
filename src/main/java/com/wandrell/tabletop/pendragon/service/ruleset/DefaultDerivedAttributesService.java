package com.wandrell.tabletop.pendragon.service.ruleset;

import static com.google.common.base.Preconditions.checkNotNull;

import com.wandrell.pattern.command.CommandExecutor;
import com.wandrell.tabletop.interval.IntervalTable;
import com.wandrell.tabletop.pendragon.model.character.PendragonBaseCharacter;
import com.wandrell.tabletop.pendragon.model.character.PendragonHumanCharacter;
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
    public final Integer getDamage(final PendragonBaseCharacter character) {
        return getCommandExecutor().execute(
                new GetDamageCommand(character.getSize(), character
                        .getStrength()));
    }

    @Override
    public final Integer
            getDexterityRoll(final PendragonBaseCharacter character) {
        return character.getDexterity();
    }

    @Override
    public final Integer getDistinctiveFeatures(
            final PendragonHumanCharacter character) {
        if (tableFeatures == null) {
            tableFeatures = getCommandExecutor().execute(
                    new GetDistinctiveFeaturesTableCommand());
        }

        return tableFeatures.getValue(character.getAppearance());
    }

    @Override
    public final Integer getHealingRate(final PendragonBaseCharacter character) {
        return getCommandExecutor().execute(
                new GetHealingRateCommand(character.getConstitution(),
                        character.getStrength()));
    }

    @Override
    public final Integer getHitPoints(final PendragonBaseCharacter character) {
        return getCommandExecutor().execute(
                new GetHitPointsCommand(character.getConstitution(), character
                        .getSize()));
    }

    @Override
    public final Integer getKnockdown(final PendragonBaseCharacter character) {
        return character.getSize();
    }

    @Override
    public final Integer getMajorWound(final PendragonBaseCharacter character) {
        return character.getConstitution();
    }

    @Override
    public final Integer getMoveRate(final PendragonBaseCharacter character) {
        return getCommandExecutor().execute(
                new GetMoveRateCommand(character.getDexterity(), character
                        .getStrength()));
    }

    @Override
    public final Integer getUnconcious(final PendragonBaseCharacter character) {
        return getCommandExecutor().execute(
                new GetUnconciousCommand(character.getHitPoints()));
    }

    @Override
    public final Integer getWeight(final PendragonBaseCharacter character) {
        return getCommandExecutor().execute(
                new GetWeightCommand(character.getSize()));
    }

    private final CommandExecutor getCommandExecutor() {
        return comExec;
    }

}
