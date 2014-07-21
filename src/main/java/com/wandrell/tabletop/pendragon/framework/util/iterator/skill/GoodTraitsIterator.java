package com.wandrell.tabletop.pendragon.framework.util.iterator.skill;

import java.util.Iterator;

import com.wandrell.tabletop.pendragon.valuehandler.DefaultPendragonTrait;
import com.wandrell.util.iterator.AbstractFilteredIterator;

public class GoodTraitsIterator extends
	AbstractFilteredIterator<DefaultPendragonTrait> {

    public GoodTraitsIterator(final Iterator<DefaultPendragonTrait> iterator) {
	super(iterator);
    }

    @Override
    protected final Boolean isValid(final DefaultPendragonTrait value) {
	return value.isGoodTrait();
    }

}
