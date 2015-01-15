package com.wandrell.tabletop.testing.pendragon.framework.test.integration.file.read;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.tabletop.business.model.pendragon.stats.SpecialtySkill;
import com.wandrell.tabletop.business.service.pendragon.ModelService;
import com.wandrell.tabletop.business.util.parser.xml.pendragon.input.stats.SpecialtySkillDocumentInputProcessor;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;
import com.wandrell.util.parser.InputParser;
import com.wandrell.util.parser.xml.input.JDOMDocumentInputProcessor;
import com.wandrell.util.parser.xml.input.JDOMStAXInputParser;

public final class ITReadSpecialtySkillDocumentInputProcessor {

    private SpecialtySkill skill;

    public ITReadSpecialtySkillDocumentInputProcessor() {
        super();
    }

    @BeforeClass
    public final void initialize() throws Exception {
        final InputParser<SpecialtySkill> parser;
        final JDOMDocumentInputProcessor<SpecialtySkill> processor;
        final ModelService modelService;

        modelService = TestServiceFactory.getInstance().getModelService();

        processor = new SpecialtySkillDocumentInputProcessor(modelService);
        parser = new JDOMStAXInputParser<SpecialtySkill>(processor);

        skill = parser.read(ResourceUtils
                .getClassPathInputStream(TestModelFileConf.SPECIALTY_SKILL));
    }

    @Test
    public final void testName() {
        Assert.assertEquals(skill.getName(), "test_specialty_skill");
    }

    @Test
    public final void testSkills() {
        Assert.assertEquals(skill.getSurrogatedSkills().size(), 2);

        Assert.assertTrue(skill.getSurrogatedSkills().contains("skill_1"));
        Assert.assertTrue(skill.getSurrogatedSkills().contains("skill_2"));
    }

}
