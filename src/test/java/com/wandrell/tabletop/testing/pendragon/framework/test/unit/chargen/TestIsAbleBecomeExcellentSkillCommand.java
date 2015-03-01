package com.wandrell.tabletop.testing.pendragon.framework.test.unit.chargen;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.pattern.command.ReturnCommand;
import com.wandrell.tabletop.character.Gender;
import com.wandrell.tabletop.pendragon.model.stats.Skill;
import com.wandrell.tabletop.pendragon.service.chargen.command.IsAbleBecomeExcellentSkillCommand;

public final class TestIsAbleBecomeExcellentSkillCommand {

    public TestIsAbleBecomeExcellentSkillCommand() {
        super();
    }

    @Test
    public final void testIsAble_Female_CombatIn_Able() throws Exception {
        final Skill skill;
        final ReturnCommand<Boolean> command;

        skill = Mockito.mock(Skill.class);

        Mockito.when(skill.isCombatSkill()).thenReturn(true);
        Mockito.when(skill.getName()).thenReturn("dagger");

        command = new IsAbleBecomeExcellentSkillCommand(skill, Gender.FEMALE);

        Assert.assertTrue(command.execute());
    }

    @Test
    public final void testIsAble_Female_CombatOut_NotAble() throws Exception {
        final Skill skill;
        final ReturnCommand<Boolean> command;

        skill = Mockito.mock(Skill.class);

        Mockito.when(skill.isCombatSkill()).thenReturn(true);
        Mockito.when(skill.getName()).thenReturn("");

        command = new IsAbleBecomeExcellentSkillCommand(skill, Gender.FEMALE);

        Assert.assertTrue(!command.execute());
    }

    @Test
    public final void testIsAble_Female_NotCombat_Able() throws Exception {
        final Skill skill;
        final ReturnCommand<Boolean> command;

        skill = Mockito.mock(Skill.class);

        Mockito.when(skill.isCombatSkill()).thenReturn(false);

        command = new IsAbleBecomeExcellentSkillCommand(skill, Gender.FEMALE);

        Assert.assertTrue(command.execute());
    }

    @Test
    public final void testIsAble_Male_Able() throws Exception {
        final Skill skill;
        final ReturnCommand<Boolean> command;

        skill = Mockito.mock(Skill.class);

        command = new IsAbleBecomeExcellentSkillCommand(skill, Gender.MALE);

        Assert.assertTrue(command.execute());
    }

}
