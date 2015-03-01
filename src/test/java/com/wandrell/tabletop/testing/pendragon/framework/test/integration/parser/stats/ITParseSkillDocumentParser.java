package com.wandrell.tabletop.testing.pendragon.framework.test.integration.parser.stats;

import java.io.Reader;
import java.util.Collection;
import java.util.Iterator;

import org.jdom2.Document;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.pattern.conf.XMLValidationType;
import com.wandrell.pattern.parser.Parser;
import com.wandrell.pattern.parser.xml.ValidatedXMLFileParser;
import com.wandrell.tabletop.pendragon.model.stats.Skill;
import com.wandrell.tabletop.pendragon.service.model.ModelService;
import com.wandrell.tabletop.pendragon.util.parser.xml.stats.SkillDocumentParser;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;

public final class ITParseSkillDocumentParser {

    private Collection<Skill> skills;

    public ITParseSkillDocumentParser() {
        super();
    }

    @BeforeClass
    public final void initialize() throws Exception {
        final Parser<Reader, Document> parserFile;
        final Parser<Document, Collection<Skill>> parserDoc;
        final ModelService modelService;

        modelService = TestServiceFactory.getInstance().getModelService();

        parserDoc = new SkillDocumentParser(modelService);
        parserFile = new ValidatedXMLFileParser(
                XMLValidationType.XSD,
                ResourceUtils
                        .getClassPathReader(TestModelFileConf.SKILLS_VALIDATION));

        skills = parserDoc.parse(parserFile.parse(ResourceUtils
                .getClassPathReader(TestModelFileConf.SKILLS)));
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
