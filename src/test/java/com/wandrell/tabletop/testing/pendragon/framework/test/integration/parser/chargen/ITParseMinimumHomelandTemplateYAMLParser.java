package com.wandrell.tabletop.testing.pendragon.framework.test.integration.parser.chargen;

import java.io.Reader;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.model.chargen.HomelandTemplate;
import com.wandrell.tabletop.pendragon.service.model.ModelService;
import com.wandrell.tabletop.pendragon.util.parser.yaml.chargen.HomelandTemplateYAMLParser;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;

public final class ITParseMinimumHomelandTemplateYAMLParser {

    private HomelandTemplate homeland;

    public ITParseMinimumHomelandTemplateYAMLParser() {
        super();
    }

    @BeforeClass
    public final void initialize() throws Exception {
        final Parser<Reader, HomelandTemplate> parser;
        final ModelService modelService;

        modelService = TestServiceFactory.getInstance().getModelService();

        parser = new HomelandTemplateYAMLParser(modelService);

        homeland = parser.parse(ResourceUtils
                .getClassPathReader(TestModelFileConf.HOMELAND_MINIMUM));
    }

    @Test
    public final void testDirectedTraits() {
        Assert.assertTrue(homeland.getDirectedTraits().isEmpty());
    }

    @Test
    public final void testName() {
        Assert.assertEquals(homeland.getName(), "test_homeland");
    }

    @Test
    public final void testPassions() {
        Assert.assertTrue(homeland.getPassions().isEmpty());
    }

    @Test
    public final void testSkills() {
        Assert.assertTrue(homeland.getSkills().isEmpty());

    }

    @Test
    public final void testSpecialtySkills() {
        Assert.assertTrue(homeland.getSpecialtySkills().isEmpty());
    }

}
