package com.wandrell.tabletop.pendragon.framework.util.comparator;

import java.util.Comparator;

import com.wandrell.tabletop.pendragon.framework.service.PendragonLocalizationService;
import com.wandrell.tabletop.pendragon.valuehandler.SpecialtySkill;

public class PendragonSpecialtySkillComparator implements
	Comparator<SpecialtySkill> {

    private final PendragonLocalizationService serviceLocalization;

    public PendragonSpecialtySkillComparator(
	    final PendragonLocalizationService service) {
	super();
	this.serviceLocalization = service;
    }

    @Override
    public final int compare(final SpecialtySkill o1, final SpecialtySkill o2) {
	final String s1, s2;

	s1 = getLocalizationService().getSpecialtySkillString(o1.getName());
	s2 = getLocalizationService().getSpecialtySkillString(o2.getName());

	return s1.compareTo(s2);
    }

    protected final PendragonLocalizationService getLocalizationService() {
	return serviceLocalization;
    }

}
