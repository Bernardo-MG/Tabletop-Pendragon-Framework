package com.wandrell.tabletop.pendragon.framework.service;

import java.util.Collection;
import java.util.Collections;

import com.wandrell.tabletop.pendragon.framework.service.command.PendragonAttributeNamesCommand;
import com.wandrell.tabletop.pendragon.framework.service.command.PendragonCombatSkillsCommand;
import com.wandrell.tabletop.pendragon.framework.service.command.PendragonCommonNonCombatSkillsCommand;
import com.wandrell.tabletop.pendragon.framework.service.command.PendragonDirectedTraitsCommand;
import com.wandrell.tabletop.pendragon.framework.service.command.PendragonInitialPassionsCommand;
import com.wandrell.tabletop.pendragon.framework.service.command.PendragonRepeatablePassionsCommand;
import com.wandrell.tabletop.pendragon.framework.service.command.PendragonRepeatableSkillsCommand;
import com.wandrell.tabletop.pendragon.framework.service.command.PendragonTraitNamesCommand;
import com.wandrell.tabletop.pendragon.valuehandler.DirectedTrait;
import com.wandrell.tabletop.pendragon.valuehandler.Passion;
import com.wandrell.tabletop.pendragon.valuehandler.Skill;
import com.wandrell.util.command.CommandExecutor;

public final class DefaultPendragonModelService implements
        PendragonModelService {

    private Collection<String>        attributesNames;
    private Collection<DirectedTrait> directedTraits;
    private final CommandExecutor     executor;
    private Collection<Passion>       passionsInitial;
    private Collection<Passion>       passionsRepeat;
    private Collection<Skill>         skillsCombat;
    private Collection<Skill>         skillsCommonNonCombat;
    private Collection<Skill>         skillsRepeatable;
    private Collection<String>        traitsNames;

    public DefaultPendragonModelService(final CommandExecutor executor) {
        super();
        this.executor = executor;
    }

    @Override
    public final Collection<String> getAttributeNames() {
        if (attributesNames == null) {
            attributesNames = loadAttributeNames();
        }

        return Collections.unmodifiableCollection(attributesNames);
    }

    @Override
    public final Collection<Skill> getCombatSkills() {
        if (skillsCombat == null) {
            skillsCombat = loadCombatSkills();
        }

        return Collections.unmodifiableCollection(skillsCombat);
    }

    @Override
    public final Collection<Skill> getCommonNonCombatSkills() {
        if (skillsCommonNonCombat == null) {
            skillsCommonNonCombat = getExecutor().execute(
                    new PendragonCommonNonCombatSkillsCommand());
        }

        return Collections.unmodifiableCollection(skillsCommonNonCombat);
    }

    @Override
    public final Collection<DirectedTrait> getDirectedTraits() {
        if (directedTraits == null) {
            directedTraits = loadDirectedTraits();
        }

        return Collections.unmodifiableCollection(directedTraits);
    }

    @Override
    public final Collection<Passion> getInitialPassions() {
        if (passionsInitial == null) {
            passionsInitial = loadInitialPassions();
        }

        return Collections.unmodifiableCollection(passionsInitial);
    }

    @Override
    public final Collection<Skill> getRepeatableNonCombatSkills() {
        if (skillsRepeatable == null) {
            skillsRepeatable = getExecutor().execute(
                    new PendragonRepeatableSkillsCommand());
        }

        return Collections.unmodifiableCollection(skillsRepeatable);
    }

    @Override
    public final Collection<Passion> getRepeatablePassions() {
        if (passionsRepeat == null) {
            passionsRepeat = loadRepeatablePassions();
        }

        return Collections.unmodifiableCollection(passionsRepeat);
    }

    @Override
    public final Collection<String> getTraitNames() {
        if (traitsNames == null) {
            traitsNames = loadTraitNames();
        }

        return Collections.unmodifiableCollection(traitsNames);
    }

    private final Collection<String> loadAttributeNames() {
        return getExecutor().execute(new PendragonAttributeNamesCommand());
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

    private final Collection<String> loadTraitNames() {
        return getExecutor().execute(new PendragonTraitNamesCommand());
    }

    protected final CommandExecutor getExecutor() {
        return executor;
    }

}
