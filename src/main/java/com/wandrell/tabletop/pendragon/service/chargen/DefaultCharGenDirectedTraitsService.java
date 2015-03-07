package com.wandrell.tabletop.pendragon.service.chargen;

import static com.google.common.base.Preconditions.checkNotNull;

import com.wandrell.pattern.command.CommandExecutor;
import com.wandrell.tabletop.dice.Dice;
import com.wandrell.tabletop.pendragon.service.chargen.command.GetDirectedTraitInitialValueCommand;

public final class DefaultCharGenDirectedTraitsService implements
        CharGenDirectedTraitsService {

    private final CommandExecutor comExec;
    private Dice                  initialDirTrait;

    public DefaultCharGenDirectedTraitsService(final CommandExecutor executor) {
        super();

        checkNotNull(executor, "Received a null pointer as command executor");

        comExec = executor;
    }

    @Override
    public final Dice getInitialValue() {
        if (initialDirTrait == null) {
            initialDirTrait = getCommandExecutor().execute(
                    new GetDirectedTraitInitialValueCommand());
        }

        return initialDirTrait;
    }

    private final CommandExecutor getCommandExecutor() {
        return comExec;
    }

}
