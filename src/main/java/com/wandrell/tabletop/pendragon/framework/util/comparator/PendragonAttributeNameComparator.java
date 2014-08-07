package com.wandrell.tabletop.pendragon.framework.util.comparator;

import java.util.Comparator;

import com.wandrell.tabletop.pendragon.framework.service.PendragonLocalizationService;

public class PendragonAttributeNameComparator implements Comparator<String> {

    private final PendragonLocalizationService serviceLocalization;

    public PendragonAttributeNameComparator(
            final PendragonLocalizationService service) {
        super();
        this.serviceLocalization = service;
    }

    @Override
    public final int compare(final String o1, final String o2) {
        final String s1, s2;

        s1 = getLocalizationService().getAttributeString(o1);
        s2 = getLocalizationService().getAttributeString(o2);

        return s1.compareTo(s2);
    }

    protected final PendragonLocalizationService getLocalizationService() {
        return serviceLocalization;
    }

}
