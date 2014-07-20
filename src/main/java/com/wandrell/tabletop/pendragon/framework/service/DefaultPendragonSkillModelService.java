package com.wandrell.tabletop.pendragon.framework.service;

import java.util.Collection;
import java.util.Collections;

import com.wandrell.framework.command.CommandExecutor;
import com.wandrell.tabletop.pendragon.framework.service.command.PendragonCombatSkillsCommand;
import com.wandrell.tabletop.pendragon.framework.service.command.PendragonCommonNonCombatSkillsCommand;
import com.wandrell.tabletop.pendragon.valuehandler.PendragonSkill;

public final class DefaultPendragonSkillModelService implements
	PendragonSkillModelService {

    private final CommandExecutor executor;
    private Collection<PendragonSkill> skillsCombat;
    private Collection<PendragonSkill> skillsCommonNonCombat;

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

    protected final CommandExecutor getExecutor() {
	return executor;
    }

}
