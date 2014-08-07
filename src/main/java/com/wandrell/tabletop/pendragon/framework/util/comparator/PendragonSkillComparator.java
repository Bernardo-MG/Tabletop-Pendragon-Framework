package com.wandrell.tabletop.pendragon.framework.util.comparator;

import java.util.Comparator;

import com.wandrell.tabletop.pendragon.framework.service.PendragonLocalizationService;
import com.wandrell.tabletop.pendragon.valuehandler.Skill;

public class PendragonSkillComparator implements Comparator<Skill> {

    private final PendragonLocalizationService serviceLocalization;

    public PendragonSkillComparator(final PendragonLocalizationService service) {
        super();
        this.serviceLocalization = service;
    }

    @Override
    public final int compare(final Skill o1, final Skill o2) {
        final String s1, s2;

        s1 = getLocalizationService().getSkillString(o1.getName());
        s2 = getLocalizationService().getSkillString(o2.getName());

        return s1.compareTo(s2);
    }

    protected final PendragonLocalizationService getLocalizationService() {
        return serviceLocalization;
    }

}
