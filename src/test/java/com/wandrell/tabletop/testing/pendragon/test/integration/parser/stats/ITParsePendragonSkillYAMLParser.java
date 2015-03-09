package com.wandrell.tabletop.testing.pendragon.test.integration.parser.stats;

import java.io.Reader;
import java.util.Collection;
import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.model.stats.PendragonSkillBox;
import com.wandrell.tabletop.pendragon.service.model.StatConstructorService;
import com.wandrell.tabletop.pendragon.util.parser.yaml.stats.PendragonSkillYAMLParser;
import com.wandrell.tabletop.testing.pendragon.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;

public final class ITParsePendragonSkillYAMLParser {

    private Collection<PendragonSkillBox> skills;

    public ITParsePendragonSkillYAMLParser() {
        super();
    }

    @BeforeClass
    public final void initialize() throws Exception {
        final Parser<Reader, Collection<PendragonSkillBox>> parser;
        final StatConstructorService statService;

        statService = TestServiceFactory.getInstance()
                .getStatConstructorService();

        parser = new PendragonSkillYAMLParser(statService);

        skills = parser.parse(ResourceUtils
                .getClassPathReader(TestModelFileConf.SKILLS));
    }

    @Test
    public final void testSkills() {
        final Iterator<PendragonSkillBox> itr;
        PendragonSkillBox skill;

        Assert.assertEquals(skills.size(), 3);

        itr = skills.iterator();

        skill = itr.next();
        Assert.assertEquals(skill.getName(), "skill_1");
        Assert.assertEquals(skill.getDescriptor(), "");

        Assert.assertEquals(skill.isDescribed(), (Boolean) false);

        Assert.assertEquals(skill.isCombatSkill(), (Boolean) false);
        Assert.assertEquals(skill.isCourtlySkill(), (Boolean) false);
        Assert.assertEquals(skill.isKnightlySkill(), (Boolean) false);
        Assert.assertEquals(skill.isKnowledgeSkill(), (Boolean) false);

        skill = itr.next();
        Assert.assertEquals(skill.getName(), "skill_2");
        Assert.assertEquals(skill.getDescriptor(), "");

        Assert.assertEquals(skill.isDescribed(), (Boolean) false);

        Assert.assertEquals(skill.isCombatSkill(), (Boolean) true);
        Assert.assertEquals(skill.isCourtlySkill(), (Boolean) false);
        Assert.assertEquals(skill.isKnightlySkill(), (Boolean) true);
        Assert.assertEquals(skill.isKnowledgeSkill(), (Boolean) false);

        skill = itr.next();
        Assert.assertEquals(skill.getName(), "skill_3");
        Assert.assertEquals(skill.getDescriptor(), "descriptor_3");

        Assert.assertEquals(skill.isCombatSkill(), (Boolean) false);
        Assert.assertEquals(skill.isCourtlySkill(), (Boolean) true);
        Assert.assertEquals(skill.isKnightlySkill(), (Boolean) true);
        Assert.assertEquals(skill.isKnowledgeSkill(), (Boolean) true);
    }

}
