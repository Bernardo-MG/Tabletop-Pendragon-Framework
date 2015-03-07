package com.wandrell.tabletop.pendragon.util.tag.service;

import com.wandrell.pattern.repository.Repository;
import com.wandrell.tabletop.pendragon.model.stats.PendragonSkillBox;

public interface PendragonSkillBoxRepositoryAware {

    public void setPendragonSkillRepository(
            final Repository<PendragonSkillBox> repository);

}
