package com.wandrell.tabletop.pendragon.framework.service;

import java.util.Collection;
import java.util.Collections;

import com.wandrell.framework.command.CommandExecutor;
import com.wandrell.tabletop.pendragon.framework.service.command.PendragonCombatSkillsCommand;
import com.wandrell.tabletop.pendragon.framework.service.command.PendragonCommonNonCombatSkillsCommand;
import com.wandrell.tabletop.pendragon.framework.service.command.PendragonRepeatableSkillsCommand;
import com.wandrell.tabletop.pendragon.valuehandler.PendragonSkill;

public final class DefaultPendragonSkillModelService implements
	PendragonSkillModelService {

    private final CommandExecutor executor;
    private Collection<PendragonSkill> skillsCombat;
    private Collection<PendragonSkill> skillsCommonNonCombat;
    private Collection<PendragonSkill> skillsRepeatable;

    public DefaultPendragonSkillModelService(final CommandExecutor executor) {
	super();
	this.executor = executor;
    }

    @Override
    public final Collection<PendragonSkill> getCombatSkills() {
	if (skillsCombat == null) {
	    skillsCombat = getExecutor().execute(
		    new PendragonCombatSkillsCommand());
	}

	return Collections.unmodifiableCollection(skillsCombat);
    }

    @Override
    public final Collection<PendragonSkill> getCommonNonCombatSkills() {
	if (skillsCommonNonCombat == null) {
	    skillsCommonNonCombat = getExecutor().execute(
		    new PendragonCommonNonCombatSkillsCommand());
	}

	return Collections.unmodifiableCollection(skillsCommonNonCombat);
    }

    @Override
    public final Collection<PendragonSkill> getRepeatableNonCombatSkills() {
	if (skillsRepeatable == null) {
	    skillsRepeatable = getExecutor().execute(
		    new PendragonRepeatableSkillsCommand());
	}

	return Collections.unmodifiableCollection(skillsRepeatable);
    }

    protected final CommandExecutor getExecutor() {
	return executor;
    }

}
