package com.wandrell.tabletop.testing.pendragon.test.unit.chargen;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.pattern.command.ReturnCommand;
import com.wandrell.tabletop.pendragon.model.character.stats.PendragonSkillBox;
import com.wandrell.tabletop.pendragon.service.chargen.command.IsAbleToGetSkillRaiseCommand;

public final class TestIsAbleAddIndividualDiffSkillCommand {

    public TestIsAbleAddIndividualDiffSkillCommand() {
        super();
    }

    @Test
    public final void testIsAble_Combat_Able() throws Exception {
        final PendragonSkillBox skill;
        final ReturnCommand<Boolean> command;

        skill = Mockito.mock(PendragonSkillBox.class);

        Mockito.when(skill.isCombatSkill()).thenReturn(true);
        Mockito.when(skill.getValue()).thenReturn(0);

        command = new IsAbleToGetSkillRaiseCommand(skill);

        command.execute();
        Assert.assertTrue(command.getResult());
    }

    @Test
    public final void testIsAble_Combat_AboveLimit_NotAble() throws Exception {
        final PendragonSkillBox skill;
        final ReturnCommand<Boolean> command;

        skill = Mockito.mock(PendragonSkillBox.class);

        Mockito.when(skill.isCombatSkill()).thenReturn(true);
        Mockito.when(skill.getValue()).thenReturn(20);

        command = new IsAbleToGetSkillRaiseCommand(skill);

        command.execute();
        Assert.assertTrue(!command.getResult());
    }

    @Test
    public final void testIsAble_Combat_OnLimit_NotAble() throws Exception {
        final PendragonSkillBox skill;
        final ReturnCommand<Boolean> command;

        skill = Mockito.mock(PendragonSkillBox.class);

        Mockito.when(skill.isCombatSkill()).thenReturn(true);
        Mockito.when(skill.getValue()).thenReturn(15);

        command = new IsAbleToGetSkillRaiseCommand(skill);

        command.execute();
        Assert.assertTrue(!command.getResult());
    }

    @Test
    public final void testIsAble_NotCombat_AboveLimit_NotAble()
            throws Exception {
        final PendragonSkillBox skill;
        final ReturnCommand<Boolean> command;

        skill = Mockito.mock(PendragonSkillBox.class);

        Mockito.when(skill.isCombatSkill()).thenReturn(false);
        Mockito.when(skill.getValue()).thenReturn(20);

        command = new IsAbleToGetSkillRaiseCommand(skill);

        command.execute();
        Assert.assertTrue(!command.getResult());
    }

    @Test
    public final void testIsAble_NotCombat_OnLimit_NotAble() throws Exception {
        final PendragonSkillBox skill;
        final ReturnCommand<Boolean> command;

        skill = Mockito.mock(PendragonSkillBox.class);

        Mockito.when(skill.isCombatSkill()).thenReturn(false);
        Mockito.when(skill.getValue()).thenReturn(15);

        command = new IsAbleToGetSkillRaiseCommand(skill);

        command.execute();
        Assert.assertTrue(!command.getResult());
    }

    @Test
    public final void testIsAble_NotCombat_Zero_NotAble() throws Exception {
        final PendragonSkillBox skill;
        final ReturnCommand<Boolean> command;

        skill = Mockito.mock(PendragonSkillBox.class);

        Mockito.when(skill.isCombatSkill()).thenReturn(false);
        Mockito.when(skill.getValue()).thenReturn(0);

        command = new IsAbleToGetSkillRaiseCommand(skill);

        command.execute();
        Assert.assertTrue(!command.getResult());
    }

}
