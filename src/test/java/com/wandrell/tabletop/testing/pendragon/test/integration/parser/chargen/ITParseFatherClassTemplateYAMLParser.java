package com.wandrell.tabletop.testing.pendragon.test.integration.parser.chargen;

import java.io.Reader;
import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.model.chargen.background.FatherClassTemplate;
import com.wandrell.tabletop.pendragon.service.model.ModelConstructorService;
import com.wandrell.tabletop.pendragon.util.parser.yaml.chargen.FatherClassTemplateYAMLParser;
import com.wandrell.tabletop.stats.valuebox.SkillBox;
import com.wandrell.tabletop.testing.pendragon.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;

public final class ITParseFatherClassTemplateYAMLParser {

    private FatherClassTemplate fatherClass;

    public ITParseFatherClassTemplateYAMLParser() {
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
                .getClassPathReader(TestModelFileConf.FATHER_CLASS));
    }

    @Test
    public final void testBaseDirectedTraits() {
        final Iterator<SkillBox> itr;
        SkillBox trait;

        Assert.assertEquals(fatherClass.getDirectedTraitsBase().size(), 1);

        itr = fatherClass.getDirectedTraitsBase().iterator();
        trait = itr.next();
        Assert.assertEquals(trait.getName(), "directed_trait_2");
        Assert.assertEquals(trait.getDescriptor(), "descriptor_2");
        Assert.assertEquals(trait.getValue(), (Integer) 10);
    }

    @Test
    public final void testDirectedTraits() {
        final Iterator<SkillBox> itr;
        SkillBox trait;

        Assert.assertEquals(fatherClass.getDirectedTraits().size(), 3);

        itr = fatherClass.getDirectedTraits().iterator();

        trait = itr.next();
        Assert.assertEquals(trait.getName(), "directed_trait_1");
        Assert.assertEquals(trait.getDescriptor(), "descriptor_1");
        Assert.assertEquals(trait.getValue(), (Integer) 5);

        trait = itr.next();
        Assert.assertEquals(trait.getName(), "directed_trait_2");
        Assert.assertEquals(trait.getDescriptor(), "descriptor_2");
        Assert.assertEquals(trait.getValue(), (Integer) 6);

        trait = itr.next();
        Assert.assertEquals(trait.getName(), "directed_trait_3");
        Assert.assertEquals(trait.getDescriptor(), "descriptor_3");
        Assert.assertEquals(trait.getValue(), (Integer) 7);
    }

    @Test
    public final void testMoney() {
        Assert.assertEquals(fatherClass.getMoney().getPrintableText(), "3d6+10");
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
        final Iterator<SkillBox> itr;
        SkillBox skill;

        Assert.assertEquals(fatherClass.getSkillsGroup().size(), 2);

        itr = fatherClass.getSkillsGroup().iterator();

        skill = itr.next();
        Assert.assertEquals(skill.getName(), "skill_1");
        Assert.assertEquals(skill.getDescriptor(), "");

        skill = itr.next();
        Assert.assertEquals(skill.getName(), "skill_2");
        Assert.assertEquals(skill.getDescriptor(), "descriptor_2");
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
        final Iterator<SkillBox> itr;
        SkillBox skill;

        Assert.assertEquals(fatherClass.getSpecialtySkills().size(), 1);

        itr = fatherClass.getSpecialtySkills().iterator();

        skill = itr.next();
        Assert.assertEquals(skill.getName(), "specialty_skill_1");
        Assert.assertEquals(skill.getValue(), (Integer) 12);
    }

}
