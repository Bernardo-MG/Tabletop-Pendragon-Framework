package com.wandrell.tabletop.pendragon.service.ruleset;

import static com.google.common.base.Preconditions.checkNotNull;

import com.wandrell.pattern.command.CommandExecutor;
import com.wandrell.tabletop.pendragon.service.ruleset.command.GetReligiousTraitTresholdCommand;

public class DefaultReligiousService implements ReligiousService {

    private final CommandExecutor comExec;
    private Integer               threshold;

    public DefaultReligiousService(final CommandExecutor executor) {
        super();

        checkNotNull(executor, "Received a null pointer as command executor");

        comExec = executor;
    }

    @Override
    public Integer getReligiousThreshold() {
        if (threshold == null) {
            threshold = getCommandExecutor().execute(
                    new GetReligiousTraitTresholdCommand());
        }

        return threshold;
    }

    private final CommandExecutor getCommandExecutor() {
        return comExec;
    }

}
