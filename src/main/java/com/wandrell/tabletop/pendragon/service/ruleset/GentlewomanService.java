package com.wandrell.tabletop.pendragon.service.ruleset;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;

import com.wandrell.pattern.command.CommandExecutor;
import com.wandrell.tabletop.pendragon.service.ruleset.command.GetGentlewomanTraitTresholdCommand;
import com.wandrell.tabletop.pendragon.service.ruleset.command.GetGentlewomanTraitsCommand;

public class GentlewomanService implements TraitsAchievementService {

    private final CommandExecutor comExec;
    private Integer               threshold;
    private Collection<String>    traits;

    public GentlewomanService(final CommandExecutor executor) {
        super();

        checkNotNull(executor, "Received a null pointer as command executor");

        comExec = executor;
    }

    @Override
    public final Integer getTraitThreshold() {
        if (threshold == null) {
            threshold = getCommandExecutor().execute(
                    new GetGentlewomanTraitTresholdCommand());
        }

        return threshold;
    }

    @Override
    public final Boolean isBonusTrait(final String trait) {
        if (traits == null) {
            traits = getCommandExecutor().execute(
                    new GetGentlewomanTraitsCommand());
        }

        return traits.contains(trait);
    }

    private final CommandExecutor getCommandExecutor() {
        return comExec;
    }

}
