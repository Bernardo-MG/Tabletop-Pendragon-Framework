package com.wandrell.tabletop.pendragon.service.chargen;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;

import com.wandrell.pattern.command.CommandExecutor;
import com.wandrell.tabletop.dice.Dice;
import com.wandrell.tabletop.pendragon.service.chargen.command.GetDirectedTraitInitialValueCommand;
import com.wandrell.tabletop.pendragon.service.chargen.command.GetFemaleValorousModifierCommand;
import com.wandrell.tabletop.pendragon.service.chargen.command.GetHomelandPassionRollCommand;
import com.wandrell.tabletop.pendragon.service.chargen.command.GetKnightStartingGloryCommand;
import com.wandrell.tabletop.pendragon.service.chargen.command.GetLandlordPassionsCommand;
import com.wandrell.tabletop.pendragon.service.chargen.command.GetPassionMaxCommand;
import com.wandrell.tabletop.pendragon.service.chargen.command.GetPassionPointsCommand;
import com.wandrell.tabletop.pendragon.service.chargen.command.GetReligiousTraitBonusCommand;
import com.wandrell.tabletop.pendragon.service.chargen.command.GetTraitMaxCommand;
import com.wandrell.tabletop.pendragon.service.chargen.command.GetTraitPointsCommand;
import com.wandrell.tabletop.valuebox.SkillBox;

public final class DefaultCharGenCommonRulesetService implements
        CharGenCommonRulesetService {

    private final CommandExecutor comExec;
    private Dice                  femaleValorousMod;
    private Dice                  gloryKnight;
    private Dice                  homeland;
    private Dice                  initialDirTrait;
    private Collection<SkillBox>  landlord;
    private Integer               passionMax;
    private Integer               passionPoints;
    private Integer               religious;
    private Integer               traitMax;
    private Integer               traitPoints;

    public DefaultCharGenCommonRulesetService(final CommandExecutor executor) {
        super();

        checkNotNull(executor, "Received a null pointer as command executor");

        comExec = executor;
    }

    @Override
    public final Dice getDirectedTraitInitialValue() {
        if (initialDirTrait == null) {
            initialDirTrait = getCommandExecutor().execute(
                    new GetDirectedTraitInitialValueCommand());
        }

        return initialDirTrait;
    }

    @Override
    public final Dice getFemaleValorousReduction() {
        // TODO: This should adapt to the dice sign
        // This means, instead of giving a dice to substract, the dice should
        // return a negative value
        if (femaleValorousMod == null) {
            femaleValorousMod = getCommandExecutor().execute(
                    new GetFemaleValorousModifierCommand());
        }

        return femaleValorousMod;
    }

    @Override
    public final Dice getHomelandPassionRoll() {
        if (homeland == null) {
            homeland = getCommandExecutor().execute(
                    new GetHomelandPassionRollCommand());
        }

        return homeland;
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

    @Override
    public final Integer getPassionMax() {
        if (passionMax == null) {
            passionMax = getCommandExecutor().execute(
                    new GetPassionMaxCommand());
        }

        return passionMax;
    }

    @Override
    public final Integer getPassionPoints() {
        if (passionPoints == null) {
            passionPoints = getCommandExecutor().execute(
                    new GetPassionPointsCommand());
        }

        return passionPoints;
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

    @Override
    public final Integer getTraitPoints() {
        if (traitPoints == null) {
            traitPoints = getCommandExecutor().execute(
                    new GetTraitPointsCommand());
        }

        return traitPoints;
    }

    private final CommandExecutor getCommandExecutor() {
        return comExec;
    }

}
