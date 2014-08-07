package com.wandrell.tabletop.pendragon.framework.util.iterator.skill;

import com.wandrell.tabletop.pendragon.valuehandler.DefaultSkill;
import com.wandrell.util.iterator.FilteredIterator.Validator;

public final class NotCombatSkillsValidator implements Validator<DefaultSkill> {

    public NotCombatSkillsValidator() {
        super();
    }

    @Override
    public final Boolean isValid(final DefaultSkill value) {
        return !value.isCombatSkill();
    }

}
