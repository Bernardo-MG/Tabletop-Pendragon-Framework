package com.wandrell.tabletop.pendragon.service.chargen;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;

import com.wandrell.pattern.command.CommandExecutor;
import com.wandrell.tabletop.dice.Dice;
import com.wandrell.tabletop.pendragon.service.chargen.command.GetCommonPassionRollCommand;
import com.wandrell.tabletop.pendragon.service.chargen.command.GetHomelandPassionRollCommand;
import com.wandrell.tabletop.pendragon.service.chargen.command.GetLandlordPassionRollCommand;
import com.wandrell.tabletop.pendragon.service.chargen.command.GetLandlordPassionsCommand;
import com.wandrell.tabletop.pendragon.service.chargen.command.GetPassionIndividualDifferencesPointsCommand;
import com.wandrell.tabletop.valuebox.SkillBox;

public final class DefaultCharGenPassionService implements
        CharGenPassionService {

    private final CommandExecutor comExec;
    private Dice                  common;
    private Dice                  homeland;
    private Collection<SkillBox>  landlord;
    private Integer               passionPoints;

    public DefaultCharGenPassionService(final CommandExecutor executor) {
        super();

        checkNotNull(executor, "Received a null pointer as command executor");

        comExec = executor;
    }

    @Override
    public final Dice getCommonPassionRoll() {
        if (common == null) {
            common = getCommandExecutor().execute(
                    new GetCommonPassionRollCommand());
        }

        return common;
    }

    @Override
    public final Dice getHomelandValue() {
        if (homeland == null) {
            homeland = getCommandExecutor().execute(
                    new GetHomelandPassionRollCommand());
        }

        return homeland;
    }

    @Override
    public final Integer getIndividualDifferencesPoints() {
        if (passionPoints == null) {
            passionPoints = getCommandExecutor().execute(
                    new GetPassionIndividualDifferencesPointsCommand());
        }

        return passionPoints;
    }

    @Override
    public final Dice getLandlordPassionRoll(final String passion,
            final String descriptor) {
        return getCommandExecutor().execute(
                new GetLandlordPassionRollCommand(passion, descriptor));
    }

    @Override
    public final Collection<SkillBox> getLandlordPassions() {
        if (landlord == null) {
            landlord = getCommandExecutor().execute(
                    new GetLandlordPassionsCommand());
        }

        return landlord;
    }

    private final CommandExecutor getCommandExecutor() {
        return comExec;
    }

}
