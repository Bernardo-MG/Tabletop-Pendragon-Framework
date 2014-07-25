package com.wandrell.tabletop.pendragon.framework.service;

import java.util.Collection;
import java.util.Collections;

import com.wandrell.framework.command.CommandExecutor;
import com.wandrell.tabletop.pendragon.framework.service.command.PendragonInitialPassionsCommand;
import com.wandrell.tabletop.pendragon.valuehandler.PendragonPassion;

public final class DefaultPendragonPassionModelService implements
	PendragonPassionModelService {

    private final CommandExecutor executor;
    private Collection<PendragonPassion> initialPassions;

    public DefaultPendragonPassionModelService(final CommandExecutor executor) {
	super();
	this.executor = executor;
    }

    @Override
    public final Collection<PendragonPassion> getInitialPassions() {
	if (initialPassions == null) {
	    initialPassions = loadInitialPassions();
	}

	return Collections.unmodifiableCollection(initialPassions);
    }

    private final Collection<PendragonPassion> loadInitialPassions() {
	return getExecutor().execute(new PendragonInitialPassionsCommand());
    }

    protected final CommandExecutor getExecutor() {
	return executor;
    }

}
