package com.wandrell.tabletop.pendragon.framework.util.iterator.skill;

import java.util.Iterator;

import com.wandrell.tabletop.pendragon.valuehandler.DefaultSkill;
import com.wandrell.util.iterator.AbstractFilteredIterator;

public class CombatSkillsIterator extends
	AbstractFilteredIterator<DefaultSkill> {

    public CombatSkillsIterator(final Iterator<DefaultSkill> iterator) {
	super(iterator);
    }

    @Override
    protected final Boolean isValid(final DefaultSkill value) {
	return value.isCombatSkill();
    }

}
