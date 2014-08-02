package com.wandrell.tabletop.pendragon.framework.util.iterator.skill;

import java.util.Iterator;

import com.wandrell.tabletop.pendragon.valuehandler.DefaultTrait;
import com.wandrell.util.iterator.AbstractFilteredIterator;

public class GoodTraitsIterator extends AbstractFilteredIterator<DefaultTrait> {

    public GoodTraitsIterator(final Iterator<DefaultTrait> iterator) {
	super(iterator);
    }

    @Override
    protected final Boolean isValid(final DefaultTrait value) {
	return value.isGoodTrait();
    }

}
