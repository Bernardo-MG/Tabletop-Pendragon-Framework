package com.wandrell.tabletop.pendragon.service.model;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;

import com.wandrell.pattern.command.CommandExecutor;
import com.wandrell.tabletop.pendragon.model.character.stats.PendragonSkillBox;
import com.wandrell.tabletop.pendragon.service.model.command.GetInitialDirectedTraitsCommand;
import com.wandrell.tabletop.pendragon.service.model.command.GetInitialPassionsCommand;
import com.wandrell.tabletop.pendragon.service.model.command.GetInitialSkillsCommand;
import com.wandrell.tabletop.stats.valuebox.SkillBox;

public final class InitialStatsService implements StatsService {

    private final CommandExecutor         comExec;
    private Collection<SkillBox>          passions;
    private Collection<PendragonSkillBox> skills;
    private Collection<SkillBox>          traits;

    public InitialStatsService(final CommandExecutor executor) {
        super();

        checkNotNull(executor, "Received a null pointer as command executor");

        comExec = executor;
    }

    @Override
    public final Collection<SkillBox> getDirectedTraits() {
        if (traits == null) {
            traits = getCommandExecutor().execute(
                    new GetInitialDirectedTraitsCommand());
        }

        return traits;
    }

    @Override
    public final Collection<SkillBox> getPassions() {
        if (passions == null) {
            passions = getCommandExecutor().execute(
                    new GetInitialPassionsCommand());
        }

        return passions;
    }

    @Override
    public final Collection<PendragonSkillBox> getSkills() {
        if (skills == null) {
            skills = getCommandExecutor()
                    .execute(new GetInitialSkillsCommand());
        }

        return skills;
    }

    private final CommandExecutor getCommandExecutor() {
        return comExec;
    }

}
