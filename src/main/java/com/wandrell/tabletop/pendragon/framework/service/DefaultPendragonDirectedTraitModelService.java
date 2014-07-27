package com.wandrell.tabletop.pendragon.framework.service;

import java.util.Collection;
import java.util.Collections;

import com.wandrell.framework.command.CommandExecutor;
import com.wandrell.tabletop.pendragon.framework.service.command.PendragonDirectedTraitsCommand;
import com.wandrell.tabletop.pendragon.valuehandler.PendragonDirectedTrait;

public final class DefaultPendragonDirectedTraitModelService implements
	PendragonDirectedTraitModelService {

    private Collection<PendragonDirectedTrait> directedTraits;
    private final CommandExecutor executor;

    public DefaultPendragonDirectedTraitModelService(
	    final CommandExecutor executor) {
	super();
	this.executor = executor;
    }

    @Override
    public final Collection<PendragonDirectedTrait> getDirectedTraits() {
	if (directedTraits == null) {
	    directedTraits = loadDirectedTraits();
	}

	return Collections.unmodifiableCollection(directedTraits);
    }

    private final Collection<PendragonDirectedTrait> loadDirectedTraits() {
	return getExecutor().execute(new PendragonDirectedTraitsCommand());
    }

    protected final CommandExecutor getExecutor() {
	return executor;
    }

}
