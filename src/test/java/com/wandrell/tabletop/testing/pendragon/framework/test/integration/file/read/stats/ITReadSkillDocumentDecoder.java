package com.wandrell.tabletop.testing.pendragon.framework.test.integration.file.read.stats;

import java.util.Collection;
import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.pattern.parser.InputParser;
import com.wandrell.pattern.parser.xml.XMLValidationType;
import com.wandrell.pattern.parser.xml.input.JDOMDocumentDecoder;
import com.wandrell.pattern.parser.xml.input.SAXInputParser;
import com.wandrell.tabletop.business.model.pendragon.stats.Skill;
import com.wandrell.tabletop.business.service.pendragon.ModelService;
import com.wandrell.tabletop.business.util.parser.xml.pendragon.input.stats.SkillDocumentDecoder;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;

public final class ITReadSkillDocumentDecoder {

    private Collection<Skill> skills;

    public ITReadSkillDocumentDecoder() {
        super();
    }

    @BeforeClass
    public final void initialize() throws Exception {
        final InputParser<Collection<Skill>> parser;
        final JDOMDocumentDecoder<Collection<Skill>> processor;
        final ModelService modelService;

        modelService = TestServiceFactory.getInstance().getModelService();

        processor = new SkillDocumentDecoder(modelService);
        parser = new SAXInputParser<Collection<Skill>>(
                XMLValidationType.XSD,
                ResourceUtils
                        .getClassPathInputStream(TestModelFileConf.SKILLS_VALIDATION),
                processor);

        skills = parser.read(ResourceUtils
                .getClassPathInputStream(TestModelFileConf.SKILLS));
    }

    @Test
    public final void testSkills() {
        final Iterator<Skill> itr;
        Skill skill;

        Assert.assertEquals(skills.size(), 3);

        itr = skills.iterator();

        skill = itr.next();
        Assert.assertEquals(skill.getName(), "skill_1");
        Assert.assertEquals(skill.getDescriptor(), null);

        Assert.assertEquals(skill.isDescribed(), (Boolean) false);

        Assert.assertEquals(skill.isCombatSkill(), (Boolean) false);
        Assert.assertEquals(skill.isCourtlySkill(), (Boolean) false);
        Assert.assertEquals(skill.isKnightlySkill(), (Boolean) false);
        Assert.assertEquals(skill.isKnowledgeSkill(), (Boolean) false);

        skill = itr.next();
        Assert.assertEquals(skill.getName(), "skill_2");
        Assert.assertEquals(skill.getDescriptor(), null);

        Assert.assertEquals(skill.isDescribed(), (Boolean) false);

        Assert.assertEquals(skill.isCombatSkill(), (Boolean) true);
        Assert.assertEquals(skill.isCourtlySkill(), (Boolean) false);
        Assert.assertEquals(skill.isKnightlySkill(), (Boolean) true);
        Assert.assertEquals(skill.isKnowledgeSkill(), (Boolean) false);

        skill = itr.next();
        Assert.assertEquals(skill.getName(), "skill_3");
        Assert.assertEquals(skill.getDescriptor(), "descriptor_3");

        Assert.assertEquals(skill.isDescribed(), (Boolean) true);

        Assert.assertEquals(skill.isCombatSkill(), (Boolean) false);
        Assert.assertEquals(skill.isCourtlySkill(), (Boolean) true);
        Assert.assertEquals(skill.isKnightlySkill(), (Boolean) true);
        Assert.assertEquals(skill.isKnowledgeSkill(), (Boolean) true);
    }

}
