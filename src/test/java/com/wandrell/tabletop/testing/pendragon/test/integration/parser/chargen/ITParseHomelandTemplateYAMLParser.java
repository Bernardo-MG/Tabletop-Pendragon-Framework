package com.wandrell.tabletop.testing.pendragon.test.integration.parser.chargen;

import java.io.Reader;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.pattern.repository.Repository;
import com.wandrell.tabletop.pendragon.model.chargen.region.HomelandTemplate;
import com.wandrell.tabletop.pendragon.model.chargen.region.RegionTemplate;
import com.wandrell.tabletop.pendragon.service.model.ModelConstructorService;
import com.wandrell.tabletop.pendragon.util.parser.yaml.chargen.HomelandTemplateYAMLParser;
import com.wandrell.tabletop.skill.DefaultSkillName;
import com.wandrell.tabletop.skill.SkillName;
import com.wandrell.tabletop.testing.pendragon.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;

public final class ITParseHomelandTemplateYAMLParser {

    private HomelandTemplate homeland;

    public ITParseHomelandTemplateYAMLParser() {
        super();
    }

    @BeforeClass
    public final void initialize() throws Exception {
        final Parser<Reader, HomelandTemplate> parser;
        final ModelConstructorService modelService;
        final Repository<RegionTemplate> regionRepo;

        modelService = TestServiceFactory.getInstance()
                .getModelConstructorService();
        regionRepo = TestServiceFactory.getInstance().getRegionRepository();

        parser = new HomelandTemplateYAMLParser(modelService, regionRepo);

        homeland = parser.parse(ResourceUtils
                .getClassPathReader(TestModelFileConf.HOMELAND));
    }

    @Test
    public final void testDirectedTraits() {
        SkillName skill;

        Assert.assertEquals(homeland.getDirectedTraits().size(), 1);

        skill = new DefaultSkillName("directed_1", "descriptor_directed_1");
        Assert.assertTrue(homeland.getDirectedTraits().contains(skill));
    }

    @Test
    public final void testName() {
        Assert.assertEquals(homeland.getName(), "test_homeland");
    }

    @Test
    public final void testPassions() {
        SkillName skill;

        Assert.assertEquals(homeland.getPassions().size(), 1);

        skill = new DefaultSkillName("passion_1", "descriptor_passion_1");
        Assert.assertTrue(homeland.getPassions().contains(skill));
    }

    @Test
    public final void testRegion() {
        Assert.assertEquals(homeland.getRegion().getName(), "test_region_1");
    }

    @Test
    public final void testSkills() {
        SkillName skill;

        Assert.assertEquals(homeland.getSkills().size(), 2);

        skill = new DefaultSkillName("skill_1", "");
        Assert.assertTrue(homeland.getSkills().containsKey(skill));
        Assert.assertEquals(homeland.getSkills().get(skill), (Integer) 3);

        skill = new DefaultSkillName("skill_2", "descriptor_skill_2");
        Assert.assertTrue(homeland.getSkills().containsKey(skill));
        Assert.assertEquals(homeland.getSkills().get(skill), (Integer) 4);

    }

    @Test
    public final void testSpecialtySkills() {
        Assert.assertEquals(homeland.getSpecialtySkills().size(), 1);

        Assert.assertTrue(homeland.getSpecialtySkills().containsKey(
                "specialty_skill_1"));

        Assert.assertEquals(
                homeland.getSpecialtySkills().get("specialty_skill_1"),
                (Integer) 5);
    }

}
