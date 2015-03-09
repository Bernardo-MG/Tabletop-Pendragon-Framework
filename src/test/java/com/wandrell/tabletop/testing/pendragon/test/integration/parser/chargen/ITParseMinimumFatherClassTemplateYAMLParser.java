package com.wandrell.tabletop.testing.pendragon.test.integration.parser.chargen;

import java.io.Reader;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.model.chargen.FatherClassTemplate;
import com.wandrell.tabletop.pendragon.service.model.ModelConstructorService;
import com.wandrell.tabletop.pendragon.util.parser.yaml.chargen.FatherClassTemplateYAMLParser;
import com.wandrell.tabletop.testing.pendragon.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;

public final class ITParseMinimumFatherClassTemplateYAMLParser {

    private FatherClassTemplate fatherClass;

    public ITParseMinimumFatherClassTemplateYAMLParser() {
        super();
    }

    @BeforeClass
    public final void initialize() throws Exception {
        final Parser<Reader, FatherClassTemplate> parser;
        final ModelConstructorService modelService;

        modelService = TestServiceFactory.getInstance()
                .getModelConstructorService();

        parser = new FatherClassTemplateYAMLParser(modelService);

        fatherClass = parser.parse(ResourceUtils
                .getClassPathReader(TestModelFileConf.FATHER_CLASS_MINIMUM));
    }

    @Test
    public final void testBaseDirectedTraits() {
        Assert.assertTrue(fatherClass.getDirectedTraitsBase().isEmpty());
    }

    @Test
    public final void testDirectedTraits() {
        Assert.assertTrue(fatherClass.getDirectedTraits().isEmpty());
    }

    @Test
    public final void testMoney() {
        Assert.assertEquals(fatherClass.getMoney().getTextValue(), "0d1");
    }

    @Test
    public final void testName() {
        Assert.assertEquals(fatherClass.getName(), "test_father_class");
    }

    @Test
    public final void testNonCombatSkillPoints() {
        Assert.assertEquals(fatherClass.getNonCombatSkillBonus(), (Integer) 6);
    }

    @Test
    public final void testSkillPoints() {
        Assert.assertEquals(fatherClass.getSkillsPoints(), (Integer) 5);
    }

    @Test
    public final void testSkillsGroup() {
        Assert.assertTrue(fatherClass.getSkillsGroup().isEmpty());
    }

    @Test
    public final void testSkillsGroupBonusPoints() {
        Assert.assertEquals(fatherClass.getSkillsGroupBonusPoints(),
                (Integer) 7);
    }

    @Test
    public final void testSkillsGroupDividePoints() {
        Assert.assertEquals(fatherClass.getSkillsGroupDividePoints(),
                (Integer) 8);
    }

    @Test
    public final void testSpecialtySkills() {
        Assert.assertTrue(fatherClass.getSpecialtySkills().isEmpty());
    }

}
