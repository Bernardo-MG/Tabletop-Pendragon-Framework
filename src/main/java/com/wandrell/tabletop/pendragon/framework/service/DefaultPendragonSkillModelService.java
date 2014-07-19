package com.wandrell.tabletop.pendragon.framework.service;

import java.util.Collection;
import java.util.Collections;

import com.wandrell.framework.command.CommandExecutor;
import com.wandrell.tabletop.pendragon.framework.service.command.PendragonBaseSkillsCommand;
import com.wandrell.tabletop.pendragon.valuehandler.PendragonSkill;

public final class DefaultPendragonSkillModelService implements
	PendragonSkillModelService {

    private final CommandExecutor executor;
    private Collection<PendragonSkill> skillsBase;

    public DefaultPendragonSkillModelService(final CommandExecutor executor) {
	super();
	this.executor = executor;
    }

    @Override
    public final Collection<PendragonSkill> getBaseSkills() {
	if (skillsBase == null) {
	    skillsBase = getExecutor()
		    .execute(new PendragonBaseSkillsCommand());
	}

	return Collections.unmodifiableCollection(skillsBase);
    }

    protected final CommandExecutor getExecutor() {
	return executor;
    }

}
