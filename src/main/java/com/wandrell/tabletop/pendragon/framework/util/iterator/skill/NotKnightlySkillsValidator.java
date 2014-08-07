package com.wandrell.tabletop.pendragon.framework.util.iterator.skill;

import com.wandrell.tabletop.pendragon.valuehandler.DefaultSkill;
import com.wandrell.util.iterator.FilteredIterator.Validator;

public final class NotKnightlySkillsValidator implements
        Validator<DefaultSkill> {

    public NotKnightlySkillsValidator() {
        super();
    }

    @Override
    public final Boolean isValid(final DefaultSkill value) {
        return !value.isKnightlySkill();
    }

}
