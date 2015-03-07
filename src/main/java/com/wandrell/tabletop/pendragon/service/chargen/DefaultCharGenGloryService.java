package com.wandrell.tabletop.pendragon.service.chargen;

import static com.google.common.base.Preconditions.checkNotNull;

import com.wandrell.pattern.command.CommandExecutor;
import com.wandrell.tabletop.dice.Dice;
import com.wandrell.tabletop.pendragon.service.chargen.command.GetKnightStartingGloryCommand;

public final class DefaultCharGenGloryService implements
        CharGenGloryService {

    private final CommandExecutor comExec;
    private Dice                  gloryKnight;

    public DefaultCharGenGloryService(final CommandExecutor executor) {
        super();

        checkNotNull(executor, "Received a null pointer as command executor");

        comExec = executor;
    }

    @Override
    public final Dice getKnightStartingGlory() {
        if (gloryKnight == null) {
            gloryKnight = getCommandExecutor().execute(
                    new GetKnightStartingGloryCommand());
        }

        return gloryKnight;
    }

    private final CommandExecutor getCommandExecutor() {
        return comExec;
    }

}
