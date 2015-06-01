package com.wandrell.tabletop.pendragon.service.chargen;

import static com.google.common.base.Preconditions.checkNotNull;

import com.wandrell.pattern.command.CommandExecutor;
import com.wandrell.tabletop.dice.notation.DiceExpression;
import com.wandrell.tabletop.pendragon.service.chargen.command.GetDirectedTraitInitialRollCommand;

public final class DefaultCharGenDirectedTraitsService implements
        CharGenDirectedTraitsService {

    private final CommandExecutor comExec;
    private DiceExpression        initialDirTrait;

    public DefaultCharGenDirectedTraitsService(final CommandExecutor executor) {
        super();

        checkNotNull(executor, "Received a null pointer as command executor");

        comExec = executor;
    }

    @Override
    public final DiceExpression getInitialValue() {
        if (initialDirTrait == null) {
            initialDirTrait = getCommandExecutor().execute(
                    new GetDirectedTraitInitialRollCommand());
        }

        return initialDirTrait;
    }

    private final CommandExecutor getCommandExecutor() {
        return comExec;
    }

}
