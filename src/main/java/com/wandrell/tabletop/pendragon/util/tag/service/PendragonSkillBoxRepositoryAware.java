package com.wandrell.tabletop.pendragon.util.tag.service;

import com.google.common.base.Predicate;
import com.wandrell.pattern.repository.QueryableRepository;
import com.wandrell.tabletop.pendragon.model.character.stats.PendragonSkillBox;

public interface PendragonSkillBoxRepositoryAware {

    public
            void
            setPendragonSkillRepository(
                    final QueryableRepository<PendragonSkillBox, Predicate<PendragonSkillBox>> repository);

}
