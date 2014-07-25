package com.wandrell.tabletop.pendragon.framework.service;

import java.util.Collection;
import java.util.Collections;

import com.wandrell.framework.command.CommandExecutor;
import com.wandrell.tabletop.pendragon.framework.service.command.PendragonTraitNamesCommand;

public final class DefaultPendragonTraitModelService implements
	PendragonTraitModelService {

    private final CommandExecutor executor;
    private Collection<String> names;

    public DefaultPendragonTraitModelService(final CommandExecutor executor) {
	super();
	this.executor = executor;
    }

    @Override
    public final Collection<String> getTraitNames() {
	if (names == null) {
	    names = loadTraitNames();
	}

	return Collections.unmodifiableCollection(names);
    }

    private final Collection<String> loadTraitNames() {
	return getExecutor().execute(new PendragonTraitNamesCommand());
    }

    protected final CommandExecutor getExecutor() {
	return executor;
    }

}
