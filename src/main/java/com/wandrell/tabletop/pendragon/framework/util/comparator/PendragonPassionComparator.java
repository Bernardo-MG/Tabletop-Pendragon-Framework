package com.wandrell.tabletop.pendragon.framework.util.comparator;

import java.util.Comparator;

import com.wandrell.tabletop.pendragon.framework.service.PendragonLocalizationService;
import com.wandrell.tabletop.pendragon.valuehandler.Passion;

public class PendragonPassionComparator implements Comparator<Passion> {

    private final PendragonLocalizationService serviceLocalization;

    public PendragonPassionComparator(final PendragonLocalizationService service) {
	super();
	this.serviceLocalization = service;
    }

    @Override
    public final int compare(final Passion o1, final Passion o2) {
	final String s1, s2;

	s1 = getLocalizationService().getPassionString(o1.getName());
	s2 = getLocalizationService().getPassionString(o2.getName());

	return s1.compareTo(s2);
    }

    protected final PendragonLocalizationService getLocalizationService() {
	return serviceLocalization;
    }

}
