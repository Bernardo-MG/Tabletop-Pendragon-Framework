package com.wandrell.tabletop.pendragon.framework.util.comparator;

import java.util.Comparator;

import com.wandrell.tabletop.pendragon.framework.service.PendragonLocalizationService;
import com.wandrell.tabletop.pendragon.valuehandler.PendragonSkill;

public class PendragonSkillComparator implements Comparator<PendragonSkill> {

    private final PendragonLocalizationService serviceLocalization;

    public PendragonSkillComparator(final PendragonLocalizationService service) {
	super();
	this.serviceLocalization = service;
    }

    @Override
    public final int compare(final PendragonSkill o1, final PendragonSkill o2) {
	final String s1, s2;

	s1 = getLocalizationService().getSkillString(o1.getName());
	s2 = getLocalizationService().getSkillString(o2.getName());

	return s1.compareTo(s2);
    }

    protected final PendragonLocalizationService getLocalizationService() {
	return serviceLocalization;
    }

}
