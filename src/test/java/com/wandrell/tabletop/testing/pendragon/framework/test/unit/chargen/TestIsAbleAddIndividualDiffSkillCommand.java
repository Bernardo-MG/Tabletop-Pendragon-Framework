package com.wandrell.tabletop.testing.pendragon.framework.test.unit.chargen;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.pattern.command.ReturnCommand;
import com.wandrell.tabletop.pendragon.model.stats.Skill;
import com.wandrell.tabletop.pendragon.service.chargen.command.IsAbleAddIndividualDiffSkillCommand;

public final class TestIsAbleAddIndividualDiffSkillCommand {

    public TestIsAbleAddIndividualDiffSkillCommand() {
        super();
    }

    @Test
    public final void testIsAble_Combat_Able() throws Exception {
        final Skill skill;
        final ReturnCommand<Boolean> command;

        skill = Mockito.mock(Skill.class);

        Mockito.when(skill.isCombatSkill()).thenReturn(true);
        Mockito.when(skill.getValue()).thenReturn(0);

        command = new IsAbleAddIndividualDiffSkillCommand(skill);

        Assert.assertTrue(command.execute());
    }

    @Test
    public final void testIsAble_Combat_AboveLimit_NotAble() throws Exception {
        final Skill skill;
        final ReturnCommand<Boolean> command;

        skill = Mockito.mock(Skill.class);

        Mockito.when(skill.isCombatSkill()).thenReturn(true);
        Mockito.when(skill.getValue()).thenReturn(20);

        command = new IsAbleAddIndividualDiffSkillCommand(skill);

        Assert.assertTrue(!command.execute());
    }

    @Test
    public final void testIsAble_Combat_OnLimit_NotAble() throws Exception {
        final Skill skill;
        final ReturnCommand<Boolean> command;

        skill = Mockito.mock(Skill.class);

        Mockito.when(skill.isCombatSkill()).thenReturn(true);
        Mockito.when(skill.getValue()).thenReturn(15);

        command = new IsAbleAddIndividualDiffSkillCommand(skill);

        Assert.assertTrue(!command.execute());
    }

    @Test
    public final void testIsAble_NotCombat_AboveLimit_NotAble()
            throws Exception {
        final Skill skill;
        final ReturnCommand<Boolean> command;

        skill = Mockito.mock(Skill.class);

        Mockito.when(skill.isCombatSkill()).thenReturn(false);
        Mockito.when(skill.getValue()).thenReturn(20);

        command = new IsAbleAddIndividualDiffSkillCommand(skill);

        Assert.assertTrue(!command.execute());
    }

    @Test
    public final void testIsAble_NotCombat_OnLimit_NotAble() throws Exception {
        final Skill skill;
        final ReturnCommand<Boolean> command;

        skill = Mockito.mock(Skill.class);

        Mockito.when(skill.isCombatSkill()).thenReturn(false);
        Mockito.when(skill.getValue()).thenReturn(15);

        command = new IsAbleAddIndividualDiffSkillCommand(skill);

        Assert.assertTrue(!command.execute());
    }

    @Test
    public final void testIsAble_NotCombat_Zero_NotAble() throws Exception {
        final Skill skill;
        final ReturnCommand<Boolean> command;

        skill = Mockito.mock(Skill.class);

        Mockito.when(skill.isCombatSkill()).thenReturn(false);
        Mockito.when(skill.getValue()).thenReturn(0);

        command = new IsAbleAddIndividualDiffSkillCommand(skill);

        Assert.assertTrue(!command.execute());
    }

}
