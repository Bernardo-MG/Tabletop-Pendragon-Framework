package com.wandrell.tabletop.testing.pendragon.framework.test.integration.parser.stats;

import java.io.Reader;

import org.jdom2.Document;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.pattern.parser.xml.XMLFileParser;
import com.wandrell.tabletop.pendragon.model.stats.SpecialtySkill;
import com.wandrell.tabletop.pendragon.service.ModelService;
import com.wandrell.tabletop.pendragon.util.parser.xml.input.stats.SpecialtySkillDocumentParser;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;

public final class ITReadSpecialtySkillDocumentParser {

    private SpecialtySkill skill;

    public ITReadSpecialtySkillDocumentParser() {
        super();
    }

    @BeforeClass
    public final void initialize() throws Exception {
        final Parser<Reader, Document> parserFile;
        final Parser<Document, SpecialtySkill> parserDoc;
        final ModelService modelService;

        modelService = TestServiceFactory.getInstance().getModelService();

        parserDoc = new SpecialtySkillDocumentParser(modelService);
        parserFile = new XMLFileParser();

        skill = parserDoc.parse(parserFile.parse(ResourceUtils
                .getClassPathReader(TestModelFileConf.SPECIALTY_SKILL)));
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
