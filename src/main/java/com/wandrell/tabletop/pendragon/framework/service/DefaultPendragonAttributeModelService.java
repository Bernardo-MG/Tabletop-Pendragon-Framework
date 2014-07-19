package com.wandrell.tabletop.pendragon.framework.service;

import java.util.Collection;
import java.util.Collections;

import com.wandrell.framework.command.CommandExecutor;
import com.wandrell.tabletop.pendragon.framework.service.command.PendragonAttributeNamesCommand;

public final class DefaultPendragonAttributeModelService implements
	PendragonAttributeModelService {

    private final CommandExecutor executor;
    private Collection<String> names;

    public DefaultPendragonAttributeModelService(final CommandExecutor executor) {
	super();
	this.executor = executor;
    }

    @Override
    public final Collection<String> getAttributeNames() {
	if (names == null) {
	    names = getExecutor().execute(new PendragonAttributeNamesCommand());
	}

	return Collections.unmodifiableCollection(names);
    }

    protected final CommandExecutor getExecutor() {
	return executor;
    }

}
