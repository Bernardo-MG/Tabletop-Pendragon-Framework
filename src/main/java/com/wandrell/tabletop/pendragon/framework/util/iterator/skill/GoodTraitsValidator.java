package com.wandrell.tabletop.pendragon.framework.util.iterator.skill;

import com.wandrell.tabletop.pendragon.valuehandler.DefaultTrait;
import com.wandrell.util.iterator.FilteredIterator.Validator;

public final class GoodTraitsValidator implements Validator<DefaultTrait> {

    public GoodTraitsValidator() {
        super();
    }

    @Override
    public final Boolean isValid(final DefaultTrait value) {
        return value.isGoodTrait();
    }

}
