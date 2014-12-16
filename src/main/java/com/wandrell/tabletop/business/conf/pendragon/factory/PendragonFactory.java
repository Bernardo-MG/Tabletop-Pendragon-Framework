package com.wandrell.tabletop.business.conf.pendragon.factory;

import java.util.Collection;

import com.wandrell.tabletop.business.model.pendragon.stats.DirectedTrait;
import com.wandrell.tabletop.business.model.pendragon.stats.Passion;
import com.wandrell.tabletop.business.model.pendragon.stats.Skill;
import com.wandrell.tabletop.business.model.pendragon.stats.SpecialtySkill;

public final class PendragonFactory {

    private static PendragonFactory instance;

    public static final synchronized PendragonFactory getInstance() {
        if (instance == null) {
            instance = new PendragonFactory();
        }

        return instance;
    }

    private PendragonFactory() {
        super();
    }

    public final DirectedTrait getDirectedTrait(final String name) {
        return null;
    }

    public final Passion getPassion(final String name, final String descriptor) {
        return null;
    }

    public final Skill getSkill(final String name, final Boolean combat,
            final Boolean court, final Boolean knight, final Boolean knowledge,
            final Boolean repeat) {
        return null;
    }

    public final SpecialtySkill getSpecialtySkill(final String name,
            final Collection<String> skills) {
        return null;
    }

}
