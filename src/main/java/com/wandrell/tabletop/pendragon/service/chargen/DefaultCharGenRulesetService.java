package com.wandrell.tabletop.pendragon.service.chargen;

import static com.google.common.base.Preconditions.checkNotNull;

import com.wandrell.pattern.command.CommandExecutor;
import com.wandrell.tabletop.character.Gender;
import com.wandrell.tabletop.pendragon.model.stats.Skill;
import com.wandrell.tabletop.pendragon.service.chargen.command.IsAbleAddIndividualDiffCommand;
import com.wandrell.tabletop.pendragon.service.chargen.command.IsAbleBecomeExcellentCommand;
import com.wandrell.tabletop.pendragon.service.chargen.command.IsAbleBecomeExtraCommand;

public final class DefaultCharGenRulesetService implements
        CharGenRulesetService {

    private final CommandExecutor comExec;

    public DefaultCharGenRulesetService(final CommandExecutor executor) {
        super();

        checkNotNull(executor, "Received a null pointer as command executor");

        comExec = executor;
    }

    @Override
    public final Boolean canAddIndividualDifference(final Skill skill,
            final Gender gender) {
        return getCommandExecutor().execute(
                new IsAbleAddIndividualDiffCommand(skill));
    }

    @Override
    public final Boolean canBecomeExcellentSkill(final Skill skill,
            final Gender gender) {
        return getCommandExecutor().execute(
                new IsAbleBecomeExcellentCommand(skill, gender));
    }

    @Override
    public final Boolean canBecomeExtraSkill(final Skill skill,
            final Gender gender) {
        return getCommandExecutor().execute(
                new IsAbleBecomeExtraCommand(skill, gender));
    }

    private final CommandExecutor getCommandExecutor() {
        return comExec;
    }

}
