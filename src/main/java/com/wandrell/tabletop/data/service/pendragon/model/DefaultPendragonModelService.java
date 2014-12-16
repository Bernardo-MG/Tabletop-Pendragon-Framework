package com.wandrell.tabletop.data.service.pendragon.model;

import java.util.Collection;
import java.util.Collections;

import com.wandrell.tabletop.business.model.pendragon.stats.DirectedTrait;
import com.wandrell.tabletop.business.model.pendragon.stats.Passion;
import com.wandrell.tabletop.business.model.pendragon.stats.Skill;
import com.wandrell.tabletop.business.service.pendragon.StatsService;
import com.wandrell.tabletop.data.service.pendragon.model.command.PendragonCombatSkillsCommand;
import com.wandrell.tabletop.data.service.pendragon.model.command.PendragonCommonNonCombatSkillsCommand;
import com.wandrell.tabletop.data.service.pendragon.model.command.PendragonDirectedTraitsCommand;
import com.wandrell.tabletop.data.service.pendragon.model.command.PendragonInitialPassionsCommand;
import com.wandrell.tabletop.data.service.pendragon.model.command.PendragonRepeatablePassionsCommand;
import com.wandrell.tabletop.data.service.pendragon.model.command.PendragonRepeatableSkillsCommand;
import com.wandrell.util.command.CommandExecutor;

public final class DefaultPendragonModelService implements StatsService {

    private Collection<DirectedTrait> directedTraits;
    private final CommandExecutor     executor;
    private Collection<Passion>       passionsInitial;
    private Collection<Passion>       passionsRepeat;
    private Collection<Skill>         skillsCombat;
    private Collection<Skill>         skillsCommonNonCombat;
    private Collection<Skill>         skillsRepeatable;

    public DefaultPendragonModelService(final CommandExecutor executor) {
        super();
        this.executor = executor;
    }

    @Override
    public final Collection<Skill> getCombatSkills() {
        if (skillsCombat == null) {
            skillsCombat = loadCombatSkills();
        }

        return Collections.unmodifiableCollection(skillsCombat);
    }

    @Override
    public final Collection<DirectedTrait> getDirectedTraits() {
        if (directedTraits == null) {
            directedTraits = loadDirectedTraits();
        }

        return Collections.unmodifiableCollection(directedTraits);
    }

    @Override
    public final Collection<Passion> getPassions() {
        if (passionsInitial == null) {
            passionsInitial = loadInitialPassions();
        }

        return Collections.unmodifiableCollection(passionsInitial);
    }

    @Override
    public final Collection<Passion> getRepeatablePassions() {
        if (passionsRepeat == null) {
            passionsRepeat = loadRepeatablePassions();
        }

        return Collections.unmodifiableCollection(passionsRepeat);
    }

    @Override
    public final Collection<Skill> getRepeatableSkills() {
        if (skillsRepeatable == null) {
            skillsRepeatable = getExecutor().execute(
                    new PendragonRepeatableSkillsCommand());
        }

        return Collections.unmodifiableCollection(skillsRepeatable);
    }

    @Override
    public final Collection<Skill> getSkills() {
        if (skillsCommonNonCombat == null) {
            skillsCommonNonCombat = getExecutor().execute(
                    new PendragonCommonNonCombatSkillsCommand());
        }

        return Collections.unmodifiableCollection(skillsCommonNonCombat);
    }

    private final Collection<Skill> loadCombatSkills() {
        return getExecutor().execute(new PendragonCombatSkillsCommand());
    }

    private final Collection<DirectedTrait> loadDirectedTraits() {
        return getExecutor().execute(new PendragonDirectedTraitsCommand());
    }

    private final Collection<Passion> loadInitialPassions() {
        return getExecutor().execute(new PendragonInitialPassionsCommand());
    }

    private final Collection<Passion> loadRepeatablePassions() {
        return getExecutor().execute(new PendragonRepeatablePassionsCommand());
    }

    protected final CommandExecutor getExecutor() {
        return executor;
    }

}
