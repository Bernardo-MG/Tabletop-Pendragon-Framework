package com.wandrell.tabletop.testing.pendragon.framework.test.integration.file.read.chargen;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.pattern.parser.InputParser;
import com.wandrell.pattern.parser.xml.input.JDOMDocumentDecoder;
import com.wandrell.pattern.parser.xml.input.StAXInputParser;
import com.wandrell.tabletop.business.model.pendragon.chargen.HomelandTemplate;
import com.wandrell.tabletop.business.model.skill.DefaultNameAndDescriptor;
import com.wandrell.tabletop.business.model.skill.NameAndDescriptor;
import com.wandrell.tabletop.business.service.pendragon.ModelService;
import com.wandrell.tabletop.business.util.parser.xml.pendragon.input.chargen.HomelandTemplateDocumentDecoder;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;

public final class ITReadHomelandTemplateDocumentDecoder {

    private HomelandTemplate homeland;
    private ModelService     modelService;

    public ITReadHomelandTemplateDocumentDecoder() {
        super();
    }

    @BeforeClass
    public final void initialize() throws Exception {
        final InputParser<HomelandTemplate> parser;
        final JDOMDocumentDecoder<HomelandTemplate> decoder;

        modelService = TestServiceFactory.getInstance().getModelService();

        decoder = new HomelandTemplateDocumentDecoder(modelService);
        parser = new StAXInputParser<HomelandTemplate>(decoder);

        homeland = parser.read(ResourceUtils
                .getClassPathInputStream(TestModelFileConf.HOMELAND));
    }

    @Test
    public final void testDirectedTraits() {
        NameAndDescriptor skill;

        Assert.assertEquals(homeland.getDirectedTraits().size(), 1);

        skill = new DefaultNameAndDescriptor("directed_1",
                "descriptor_directed_1");
        Assert.assertTrue(homeland.getDirectedTraits().contains(skill));
    }

    @Test
    public final void testName() {
        Assert.assertEquals(homeland.getHomeland(), "test_homeland");
    }

    @Test
    public final void testPassions() {
        NameAndDescriptor skill;

        Assert.assertEquals(homeland.getPassions().size(), 1);

        skill = new DefaultNameAndDescriptor("passion_1",
                "descriptor_passion_1");
        Assert.assertTrue(homeland.getPassions().contains(skill));
    }

    @Test
    public final void testSkills() {
        NameAndDescriptor skill;

        Assert.assertEquals(homeland.getSkills().size(), 2);

        skill = new DefaultNameAndDescriptor("skill_1", "");
        Assert.assertTrue(homeland.getSkills().containsKey(skill));
        Assert.assertEquals(homeland.getSkills().get(skill), (Integer) 3);

        skill = new DefaultNameAndDescriptor("skill_2", "descriptor_skill_2");
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
