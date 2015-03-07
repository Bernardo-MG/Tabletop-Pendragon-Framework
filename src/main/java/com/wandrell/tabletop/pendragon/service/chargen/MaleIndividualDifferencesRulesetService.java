package com.wandrell.tabletop.pendragon.service.chargen;

import static com.google.common.base.Preconditions.checkNotNull;

import com.wandrell.pattern.command.CommandExecutor;
import com.wandrell.tabletop.character.Gender;
import com.wandrell.tabletop.pendragon.model.stats.PendragonSkillBox;
import com.wandrell.tabletop.pendragon.service.chargen.command.GetExcellentSkillValueCommand;
import com.wandrell.tabletop.pendragon.service.chargen.command.GetExtraSkillMaxPicksCommand;
import com.wandrell.tabletop.pendragon.service.chargen.command.GetExtraSkillValueCommand;
import com.wandrell.tabletop.pendragon.service.chargen.command.GetFamousTraitValueCommand;
import com.wandrell.tabletop.pendragon.service.chargen.command.GetIndividualDifferencesRaisesCommand;
import com.wandrell.tabletop.pendragon.service.chargen.command.GetIndividualDifferencesSkillPointsCommand;
import com.wandrell.tabletop.pendragon.service.chargen.command.GetNoCombatSkillPointsCommand;
import com.wandrell.tabletop.pendragon.service.chargen.command.GetSkillRaiseValueCommand;
import com.wandrell.tabletop.pendragon.service.chargen.command.IsAbleToBecomeExcellentSkillCommand;
import com.wandrell.tabletop.pendragon.service.chargen.command.IsAbleToBecomeExtraSkillCommand;
import com.wandrell.tabletop.pendragon.service.chargen.command.IsAbleToGetSkillRaiseCommand;
import com.wandrell.tabletop.pendragon.service.chargen.command.IsIndividualDifferencesSkillAbleToGetPointsCommand;

public final class MaleIndividualDifferencesRulesetService implements
        IndividualDifferencesRulesetService {

    private final CommandExecutor comExec;
    private Integer               excellent;
    private Integer               extra;
    private Integer               extraPicks;
    private Integer               famousTrait;
    private Integer               noCombat;
    private Integer               raise;
    private Integer               raises;
    private Integer               skillPoints;

    public MaleIndividualDifferencesRulesetService(
            final CommandExecutor executor) {
        super();

        checkNotNull(executor, "Received a null pointer as command executor");

        comExec = executor;
    }

    @Override
    public final Integer getExcellentSkillValue() {
        if (excellent == null) {
            excellent = getCommandExecutor().execute(
                    new GetExcellentSkillValueCommand());
        }

        return excellent;
    }

    @Override
    public final Integer getExtraSkillMaxPicks() {
        if (extraPicks == null) {
            extraPicks = getCommandExecutor().execute(
                    new GetExtraSkillMaxPicksCommand());
        }

        return extraPicks;
    }

    @Override
    public final Integer getExtraSkillValue() {
        if (extra == null) {
            extra = getCommandExecutor().execute(
                    new GetExtraSkillValueCommand());
        }

        return extra;
    }

    @Override
    public final Integer getFamousTraitValue() {
        if (famousTrait == null) {
            famousTrait = getCommandExecutor().execute(
                    new GetFamousTraitValueCommand());
        }

        return famousTrait;
    }

    @Override
    public final Integer getNoCombatSkillsPoints() {
        if (noCombat == null) {
            noCombat = getCommandExecutor().execute(
                    new GetNoCombatSkillPointsCommand());
        }

        return noCombat;
    }

    @Override
    public final Integer getRaises() {
        if (raises == null) {
            raises = getCommandExecutor().execute(
                    new GetIndividualDifferencesRaisesCommand());
        }

        return raises;
    }

    @Override
    public final Integer getSkillPoints() {
        if (skillPoints == null) {
            skillPoints = getCommandExecutor().execute(
                    new GetIndividualDifferencesSkillPointsCommand());
        }

        return skillPoints;
    }

    @Override
    public final Integer getSkillRaiseValue() {
        if (raise == null) {
            raise = getCommandExecutor().execute(
                    new GetSkillRaiseValueCommand());
        }

        return raise;
    }

    @Override
    public final Boolean isAbleToBecomeExcellentSkill(
            final PendragonSkillBox skill) {
        return getCommandExecutor().execute(
                new IsAbleToBecomeExcellentSkillCommand(skill, Gender.MALE));
    }

    @Override
    public final Boolean
            isAbleToBecomeExtraSkill(final PendragonSkillBox skill) {
        return getCommandExecutor().execute(
                new IsAbleToBecomeExtraSkillCommand(skill, Gender.MALE));
    }

    @Override
    public final Boolean isAbleToGetPoints(final PendragonSkillBox skill) {
        return getCommandExecutor().execute(
                new IsIndividualDifferencesSkillAbleToGetPointsCommand(skill));
    }

    @Override
    public final Boolean isAbleToGetSkillRaise(final PendragonSkillBox skill) {
        return getCommandExecutor().execute(
                new IsAbleToGetSkillRaiseCommand(skill));
    }

    private final CommandExecutor getCommandExecutor() {
        return comExec;
    }

}
