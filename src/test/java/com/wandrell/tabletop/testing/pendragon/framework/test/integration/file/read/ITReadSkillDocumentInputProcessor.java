package com.wandrell.tabletop.testing.pendragon.framework.test.integration.file.read;

import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.tabletop.business.model.pendragon.stats.Skill;
import com.wandrell.tabletop.business.service.pendragon.ModelService;
import com.wandrell.tabletop.business.util.parser.xml.pendragon.input.stats.SkillDocumentInputProcessor;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.FileConf;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;
import com.wandrell.util.parser.InputParser;
import com.wandrell.util.parser.xml.XMLValidationType;
import com.wandrell.util.parser.xml.input.JDOMDocumentInputProcessor;
import com.wandrell.util.parser.xml.input.JDOMSAXInputParser;

public final class ITReadSkillDocumentInputProcessor {

    private Collection<Skill> skills;

    public ITReadSkillDocumentInputProcessor() {
        super();
    }

    @BeforeClass
    public final void initialize() throws Exception {
        final InputParser<Collection<Skill>> parser;
        final JDOMDocumentInputProcessor<Collection<Skill>> processor;
        final ModelService modelService;
        final Collection<InputStream> validationStreams;

        modelService = TestServiceFactory.getInstance().getModelService();

        validationStreams = new LinkedList<>();
        validationStreams.add(ResourceUtils
                .getClassPathInputStream(FileConf.SKILLS_VALIDATION));

        processor = new SkillDocumentInputProcessor(modelService);
        parser = new JDOMSAXInputParser<Collection<Skill>>(
                XMLValidationType.XSD, validationStreams, processor);

        skills = parser.read(ResourceUtils
                .getClassPathInputStream(FileConf.SKILLS));
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
        Assert.assertEquals(skill.isCombatSkill(), (Boolean) false);
        Assert.assertEquals(skill.isCourtlySkill(), (Boolean) false);
        Assert.assertEquals(skill.isKnightlySkill(), (Boolean) false);
        Assert.assertEquals(skill.isKnowledgeSkill(), (Boolean) false);
        Assert.assertEquals(skill.isRepeatable(), (Boolean) false);

        skill = itr.next();
        Assert.assertEquals(skill.getName(), "skill_2");
        Assert.assertEquals(skill.getDescriptor(), null);
        Assert.assertEquals(skill.isCombatSkill(), (Boolean) true);
        Assert.assertEquals(skill.isCourtlySkill(), (Boolean) false);
        Assert.assertEquals(skill.isKnightlySkill(), (Boolean) true);
        Assert.assertEquals(skill.isKnowledgeSkill(), (Boolean) false);
        Assert.assertEquals(skill.isRepeatable(), (Boolean) false);

        skill = itr.next();
        Assert.assertEquals(skill.getName(), "skill_3");
        Assert.assertEquals(skill.getDescriptor(), "descriptor_3");
        Assert.assertEquals(skill.isCombatSkill(), (Boolean) false);
        Assert.assertEquals(skill.isCourtlySkill(), (Boolean) true);
        Assert.assertEquals(skill.isKnightlySkill(), (Boolean) true);
        Assert.assertEquals(skill.isKnowledgeSkill(), (Boolean) true);
        Assert.assertEquals(skill.isRepeatable(), (Boolean) true);
    }

}
