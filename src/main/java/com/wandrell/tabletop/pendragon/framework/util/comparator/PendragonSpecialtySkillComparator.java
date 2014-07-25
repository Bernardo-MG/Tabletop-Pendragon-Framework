package com.wandrell.tabletop.pendragon.framework.util.comparator;

import java.util.Comparator;

import com.wandrell.tabletop.pendragon.framework.service.PendragonLocalizationService;
import com.wandrell.tabletop.pendragon.valuehandler.PendragonSpecialtySkill;

public class PendragonSpecialtySkillComparator implements
	Comparator<PendragonSpecialtySkill> {

    private final PendragonLocalizationService serviceLocalization;

    public PendragonSpecialtySkillComparator(
	    final PendragonLocalizationService service) {
	super();
	this.serviceLocalization = service;
    }

    @Override
    public final int compare(final PendragonSpecialtySkill o1,
	    final PendragonSpecialtySkill o2) {
	final String s1, s2;

	s1 = getLocalizationService().getSpecialtySkillString(o1.getName());
	s2 = getLocalizationService().getSpecialtySkillString(o2.getName());

	return s1.compareTo(s2);
    }

    protected final PendragonLocalizationService getLocalizationService() {
	return serviceLocalization;
    }

}
