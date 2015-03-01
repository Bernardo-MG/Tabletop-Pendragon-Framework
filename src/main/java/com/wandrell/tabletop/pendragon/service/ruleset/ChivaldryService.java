package com.wandrell.tabletop.pendragon.service.ruleset;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;

import com.wandrell.pattern.command.CommandExecutor;
import com.wandrell.tabletop.pendragon.service.ruleset.command.GetChivaldryTraitTresholdCommand;
import com.wandrell.tabletop.pendragon.service.ruleset.command.GetChivaldryTraitsCommand;

public class ChivaldryService implements TraitsAchievementService {

    private final CommandExecutor comExec;
    private Integer               threshold;
    private Collection<String>    traits;

    public ChivaldryService(final CommandExecutor executor) {
        super();

        checkNotNull(executor, "Received a null pointer as command executor");

        comExec = executor;
    }

    @Override
    public final Integer getTraitThreshold() {
        if (threshold == null) {
            threshold = getCommandExecutor().execute(
                    new GetChivaldryTraitTresholdCommand());
        }

        return threshold;
    }

    @Override
    public final Boolean isBonusTrait(final String trait) {
        if (traits == null) {
            traits = getCommandExecutor().execute(
                    new GetChivaldryTraitsCommand());
        }

        return traits.contains(trait);
    }

    private final CommandExecutor getCommandExecutor() {
        return comExec;
    }

}
