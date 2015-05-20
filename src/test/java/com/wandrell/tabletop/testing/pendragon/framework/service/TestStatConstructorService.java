package com.wandrell.tabletop.testing.pendragon.framework.service;

import java.util.Collection;

import org.mockito.Mockito;

import com.wandrell.tabletop.pendragon.model.character.stats.PendragonSkillBox;
import com.wandrell.tabletop.pendragon.model.character.stats.SpecialtySkillBox;
import com.wandrell.tabletop.pendragon.service.model.StatConstructorService;
import com.wandrell.tabletop.stats.valuebox.SkillBox;

public final class TestStatConstructorService implements StatConstructorService {

    public TestStatConstructorService() {
        super();
    }

    @Override
    public final SkillBox getDirectedTrait(final String name,
            final String descriptor, final Integer value) {
        final SkillBox trait;

        trait = Mockito.mock(SkillBox.class);

        Mockito.when(trait.getName()).thenReturn(name);
        Mockito.when(trait.getDescriptor()).thenReturn(descriptor);
        Mockito.when(trait.getValue()).thenReturn(value);

        return trait;
    }

    @Override
    public final SkillBox getPassion(final String name,
            final String descriptor, final Integer value) {
        final SkillBox passion;

        passion = Mockito.mock(SkillBox.class);

        Mockito.when(passion.getName()).thenReturn(name);
        Mockito.when(passion.getDescriptor()).thenReturn(descriptor);
        Mockito.when(passion.getValue()).thenReturn(value);

        return passion;
    }

    @Override
    public final PendragonSkillBox getSkill(final String name,
            final String descriptor, final Integer value,
            final Boolean combatSkill, final Boolean knightlySkill,
            final Boolean knowledgeSkill, final Boolean courtlySkill) {
        final PendragonSkillBox skill;

        skill = Mockito.mock(PendragonSkillBox.class);

        Mockito.when(skill.getName()).thenReturn(name);
        Mockito.when(skill.getDescriptor()).thenReturn(descriptor);
        Mockito.when(skill.getValue()).thenReturn(value);

        Mockito.when(skill.isCombatSkill()).thenReturn(combatSkill);
        Mockito.when(skill.isKnightlySkill()).thenReturn(knightlySkill);
        Mockito.when(skill.isKnowledgeSkill()).thenReturn(knowledgeSkill);
        Mockito.when(skill.isCourtlySkill()).thenReturn(courtlySkill);

        return skill;
    }

    @Override
    public final SpecialtySkillBox getSpecialtySkill(final String name,
            final Integer value, final Collection<String> skills) {
        final SpecialtySkillBox skill;

        skill = Mockito.mock(SpecialtySkillBox.class);

        Mockito.when(skill.getName()).thenReturn(name);
        Mockito.when(skill.getSurrogatedSkills()).thenReturn(skills);
        Mockito.when(skill.getValue()).thenReturn(value);

        return skill;
    }

}
