package com.wandrell.tabletop.pendragon.service.chargen.command;

import com.wandrell.pattern.command.ResultCommand;
import com.wandrell.tabletop.pendragon.model.character.stats.PendragonSkillBox;

public final class IsIndividualDifferencesSkillAbleToGetPointsCommand implements
        ResultCommand<Boolean> {

    private Boolean                 able;
    private final PendragonSkillBox skill;

    public IsIndividualDifferencesSkillAbleToGetPointsCommand(
            final PendragonSkillBox skill) {
        super();
        this.skill = skill;
    }

    @Override
    public final void execute() {
        able = !skill.isCombatSkill();
    }

    @Override
    public final Boolean getResult() {
        return able;
    }

}
