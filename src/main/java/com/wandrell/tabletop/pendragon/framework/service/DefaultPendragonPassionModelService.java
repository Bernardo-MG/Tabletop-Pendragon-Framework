package com.wandrell.tabletop.pendragon.framework.service;

import java.util.Collection;
import java.util.Collections;

import com.wandrell.framework.command.CommandExecutor;
import com.wandrell.tabletop.pendragon.framework.service.command.PendragonInitialPassionsCommand;
import com.wandrell.tabletop.pendragon.framework.service.command.PendragonRepeatablePassionsCommand;
import com.wandrell.tabletop.pendragon.valuehandler.PendragonPassion;

public final class DefaultPendragonPassionModelService implements
	PendragonPassionModelService {

    private final CommandExecutor executor;
    private Collection<PendragonPassion> passionsInitial;
    private Collection<PendragonPassion> passionsRepeat;

    public DefaultPendragonPassionModelService(final CommandExecutor executor) {
	super();
	this.executor = executor;
    }

    @Override
    public final Collection<PendragonPassion> getInitialPassions() {
	if (passionsInitial == null) {
	    passionsInitial = loadInitialPassions();
	}

	return Collections.unmodifiableCollection(passionsInitial);
    }

    @Override
    public final Collection<PendragonPassion> getRepeatablePassions() {
	if (passionsRepeat == null) {
	    passionsRepeat = loadRepeatablePassions();
	}

	return Collections.unmodifiableCollection(passionsRepeat);
    }

    private final Collection<PendragonPassion> loadInitialPassions() {
	return getExecutor().execute(new PendragonInitialPassionsCommand());
    }

    private final Collection<PendragonPassion> loadRepeatablePassions() {
	return getExecutor().execute(new PendragonRepeatablePassionsCommand());
    }

    protected final CommandExecutor getExecutor() {
	return executor;
    }

}
