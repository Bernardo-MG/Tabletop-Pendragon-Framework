package com.wandrell.tabletop.testing.pendragon.test.integration.parser.chargen;

import java.io.Reader;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.model.character.stats.TraitsHolder;
import com.wandrell.tabletop.pendragon.model.chargen.region.RegionTemplate;
import com.wandrell.tabletop.pendragon.service.model.ModelConstructorService;
import com.wandrell.tabletop.pendragon.util.parser.yaml.chargen.RegionTemplateYAMLParser;
import com.wandrell.tabletop.testing.pendragon.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;

public final class ITParseRegionTemplateYAMLParser {

    private RegionTemplate region;

    public ITParseRegionTemplateYAMLParser() {
        super();
    }

    @BeforeClass
    public final void initialize() throws Exception {
        final Parser<Reader, RegionTemplate> parser;
        final ModelConstructorService modelService;

        modelService = TestServiceFactory.getInstance()
                .getModelConstructorService();

        parser = new RegionTemplateYAMLParser(modelService);

        region = parser.parse(ResourceUtils
                .getClassPathReader(TestModelFileConf.REGION));
    }

    @Test
    public final void testName() {
        Assert.assertEquals(region.getName(), "test_region");
    }

    @Test
    public final void testTraits() {
        final TraitsHolder traits;

        traits = region.getTraits();
        Assert.assertEquals(traits.getHonest(), (Integer) 2);
    }

}
