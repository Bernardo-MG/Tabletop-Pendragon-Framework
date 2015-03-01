package com.wandrell.tabletop.pendragon.service.chargen.command;

import com.wandrell.pattern.command.ReturnCommand;
import com.wandrell.tabletop.pendragon.model.stats.Skill;

public final class IsAbleAddIndividualDiffCommand implements
        ReturnCommand<Boolean> {

    private final Skill skill;

    public IsAbleAddIndividualDiffCommand(final Skill skill) {
        super();

        this.skill = skill;
    }

    @Override
    public final Boolean execute() {
        return ((skill.isCombatSkill()) || (skill.getValue() > 0));
    }

}
