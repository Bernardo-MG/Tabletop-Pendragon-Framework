package com.wandrell.tabletop.pendragon.service.chargen;

import static com.google.common.base.Preconditions.checkNotNull;

import com.wandrell.pattern.command.CommandExecutor;
import com.wandrell.tabletop.pendragon.service.chargen.command.GetPassionMaxCommand;
import com.wandrell.tabletop.pendragon.service.chargen.command.GetReligiousTraitBonusCommand;
import com.wandrell.tabletop.pendragon.service.chargen.command.GetTraitMaxCommand;

public final class DefaultCharGenCommonService implements
        CharGenCommonService {

    private final CommandExecutor comExec;
    private Integer               passionMax;
    private Integer               religious;
    private Integer               traitMax;

    public DefaultCharGenCommonService(final CommandExecutor executor) {
        super();

        checkNotNull(executor, "Received a null pointer as command executor");

        comExec = executor;
    }

    @Override
    public final Integer getPassionMax() {
        if (passionMax == null) {
            passionMax = getCommandExecutor().execute(
                    new GetPassionMaxCommand());
        }

        return passionMax;
    }

    @Override
    public final Integer getReligiousTraitBonus() {
        if (religious == null) {
            religious = getCommandExecutor().execute(
                    new GetReligiousTraitBonusCommand());
        }

        return religious;
    }

    @Override
    public final Integer getTraitMax() {
        if (traitMax == null) {
            traitMax = getCommandExecutor().execute(new GetTraitMaxCommand());
        }

        return traitMax;
    }

    private final CommandExecutor getCommandExecutor() {
        return comExec;
    }

}
