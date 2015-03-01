package com.wandrell.tabletop.pendragon.service.chargen;

import static com.google.common.base.Preconditions.checkNotNull;

import com.wandrell.pattern.command.CommandExecutor;
import com.wandrell.tabletop.character.Gender;
import com.wandrell.tabletop.pendragon.model.stats.Skill;
import com.wandrell.tabletop.pendragon.service.chargen.command.IsAbleAddIndividualDiffSkillCommand;
import com.wandrell.tabletop.pendragon.service.chargen.command.IsAbleBecomeExcellentSkillCommand;
import com.wandrell.tabletop.pendragon.service.chargen.command.IsAbleBecomeExtraSkillCommand;

public final class DefaultCharGenRulesetService implements
        CharGenRulesetService {

    private final CommandExecutor comExec;

    public DefaultCharGenRulesetService(final CommandExecutor executor) {
        super();

        checkNotNull(executor, "Received a null pointer as command executor");

        comExec = executor;
    }

    @Override
    public final Boolean canAddIndividualDifferenceSkill(final Skill skill,
            final Gender gender) {
        return getCommandExecutor().execute(
                new IsAbleAddIndividualDiffSkillCommand(skill));
    }

    @Override
    public final Boolean canBecomeExcellentSkill(final Skill skill,
            final Gender gender) {
        return getCommandExecutor().execute(
                new IsAbleBecomeExcellentSkillCommand(skill, gender));
    }

    @Override
    public final Boolean canBecomeExtraSkill(final Skill skill,
            final Gender gender) {
        return getCommandExecutor().execute(
                new IsAbleBecomeExtraSkillCommand(skill, gender));
    }

    private final CommandExecutor getCommandExecutor() {
        return comExec;
    }

}
