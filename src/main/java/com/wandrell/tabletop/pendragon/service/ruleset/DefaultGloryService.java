package com.wandrell.tabletop.pendragon.service.ruleset;

import static com.google.common.base.Preconditions.checkNotNull;

import com.wandrell.pattern.command.CommandExecutor;
import com.wandrell.tabletop.pendragon.service.ruleset.command.GetKnightedGloryCommand;

public final class DefaultGloryService implements GloryService {

    private final CommandExecutor comExec;
    private Integer               knighted;

    public DefaultGloryService(final CommandExecutor executor) {
        super();

        checkNotNull(executor, "Received a null pointer as command executor");

        comExec = executor;
    }

    @Override
    public final Integer getKnightedGlory() {
        if (knighted == null) {
            knighted = getCommandExecutor().execute(
                    new GetKnightedGloryCommand());
        }
        return knighted;
    }

    private final CommandExecutor getCommandExecutor() {
        return comExec;
    }

}
