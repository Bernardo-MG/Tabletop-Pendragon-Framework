package com.wandrell.tabletop.testing.pendragon.test.integration.parser.chargen;

import java.io.Reader;
import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Predicate;
import com.wandrell.pattern.parser.Parser;
import com.wandrell.pattern.repository.FilteredRepository;
import com.wandrell.tabletop.pendragon.model.chargen.region.HomelandTemplate;
import com.wandrell.tabletop.pendragon.model.chargen.region.RegionTemplate;
import com.wandrell.tabletop.pendragon.service.model.ModelConstructorService;
import com.wandrell.tabletop.pendragon.util.parser.yaml.chargen.HomelandTemplateYAMLParser;
import com.wandrell.tabletop.stats.valuebox.SkillBox;
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
        final FilteredRepository<RegionTemplate, Predicate<RegionTemplate>> regionRepo;

        modelService = TestServiceFactory.getInstance()
                .getModelConstructorService();
        regionRepo = TestServiceFactory.getInstance().getRegionRepository();

        parser = new HomelandTemplateYAMLParser(modelService, regionRepo);

        homeland = parser.parse(ResourceUtils
                .getClassPathReader(TestModelFileConf.HOMELAND));
    }

    @Test
    public final void testDirectedTraits() {
        final Iterator<SkillBox> itr;
        SkillBox skill;

        Assert.assertEquals(homeland.getDirectedTraits().size(), 1);

        itr = homeland.getDirectedTraits().iterator();

        skill = itr.next();
        Assert.assertEquals(skill.getName(), "directed_1");
        Assert.assertEquals(skill.getDescriptor(), "descriptor_directed_1");
    }

    @Test
    public final void testName() {
        Assert.assertEquals(homeland.getName(), "test_homeland");
    }

    @Test
    public final void testPassions() {
        final Iterator<SkillBox> itr;
        SkillBox skill;

        Assert.assertEquals(homeland.getPassions().size(), 1);

        itr = homeland.getPassions().iterator();

        skill = itr.next();
        Assert.assertEquals(skill.getName(), "passion_1");
        Assert.assertEquals(skill.getDescriptor(), "descriptor_passion_1");
    }

    @Test
    public final void testRegion() {
        Assert.assertEquals(homeland.getRegion().getName(), "test_region_1");
    }

    @Test
    public final void testSkills() {
        final Iterator<SkillBox> itr;
        SkillBox skill;

        Assert.assertEquals(homeland.getSkills().size(), 2);

        itr = homeland.getSkills().iterator();

        skill = itr.next();
        Assert.assertEquals(skill.getName(), "skill_1");
        Assert.assertEquals(skill.getDescriptor(), "");
        Assert.assertEquals(skill.getValue(), (Integer) 3);

        skill = itr.next();
        Assert.assertEquals(skill.getName(), "skill_2");
        Assert.assertEquals(skill.getDescriptor(), "descriptor_skill_2");
        Assert.assertEquals(skill.getValue(), (Integer) 4);

    }

    @Test
    public final void testSpecialtySkills() {
        final Iterator<SkillBox> itr;
        SkillBox skill;

        Assert.assertEquals(homeland.getSpecialtySkills().size(), 1);

        itr = homeland.getSpecialtySkills().iterator();

        skill = itr.next();
        Assert.assertEquals(skill.getName(), "specialty_skill_1");
        Assert.assertEquals(skill.getValue(), (Integer) 5);
    }

}
