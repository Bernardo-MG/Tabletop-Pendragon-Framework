package com.wandrell.tabletop.pendragon.framework.util.comparator;

import java.util.Comparator;

import com.wandrell.tabletop.pendragon.framework.service.PendragonLocalizationService;
import com.wandrell.tabletop.pendragon.valuehandler.DirectedTrait;

public class PendragonDirectedTraitComparator implements
	Comparator<DirectedTrait> {

    private final PendragonLocalizationService serviceLocalization;

    public PendragonDirectedTraitComparator(
	    final PendragonLocalizationService service) {
	super();
	this.serviceLocalization = service;
    }

    @Override
    public final int compare(final DirectedTrait o1, final DirectedTrait o2) {
	final String s1, s2;

	s1 = getLocalizationService().getDirectedTraitString(o1.getName());
	s2 = getLocalizationService().getDirectedTraitString(o2.getName());

	return s1.compareTo(s2);
    }

    protected final PendragonLocalizationService getLocalizationService() {
	return serviceLocalization;
    }

}
