package com.wandrell.tabletop.pendragon.framework.util.comparator;

import java.util.Comparator;

import com.wandrell.tabletop.pendragon.character.background.culture.Culture;
import com.wandrell.tabletop.pendragon.framework.service.PendragonLocalizationService;

public class PendragonCultureComparator implements Comparator<Culture> {

    private final PendragonLocalizationService serviceLocalization;

    public PendragonCultureComparator(final PendragonLocalizationService service) {
	super();
	this.serviceLocalization = service;
    }

    @Override
    public final int compare(final Culture o1, final Culture o2) {
	final String s1, s2;

	s1 = getLocalizationService().getCultureString(o1.getName());
	s2 = getLocalizationService().getCultureString(o2.getName());

	return s1.compareTo(s2);
    }

    protected final PendragonLocalizationService getLocalizationService() {
	return serviceLocalization;
    }

}
