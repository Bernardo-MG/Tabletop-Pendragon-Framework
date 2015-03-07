package com.wandrell.tabletop.pendragon.service.chargen;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;

import com.wandrell.pattern.command.CommandExecutor;
import com.wandrell.tabletop.dice.Dice;
import com.wandrell.tabletop.pendragon.service.chargen.command.GetAttributePointsCommand;
import com.wandrell.tabletop.pendragon.service.chargen.command.GetKnightStartingGloryCommand;
import com.wandrell.tabletop.pendragon.service.chargen.command.GetLandlordPassionsCommand;
import com.wandrell.tabletop.valuebox.SkillBox;

public final class DefaultCharGenRulesetService implements
        CharGenRulesetService {

    private Integer               attributes;
    private final CommandExecutor comExec;
    private Dice                  gloryKnight;
    private Collection<SkillBox>  landlord;

    public DefaultCharGenRulesetService(final CommandExecutor executor) {
        super();

        checkNotNull(executor, "Received a null pointer as command executor");

        comExec = executor;
    }

    @Override
    public final Integer getAttributesPoints() {
        if (attributes == null) {
            attributes = getCommandExecutor().execute(
                    new GetAttributePointsCommand());
        }

        return attributes;
    }

    @Override
    public final Dice getKnightStartingGlory() {
        if (gloryKnight == null) {
            gloryKnight = getCommandExecutor().execute(
                    new GetKnightStartingGloryCommand());
        }

        return gloryKnight;
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
