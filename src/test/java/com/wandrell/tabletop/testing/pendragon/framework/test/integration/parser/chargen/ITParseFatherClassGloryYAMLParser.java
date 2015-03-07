package com.wandrell.tabletop.testing.pendragon.framework.test.integration.parser.chargen;

import java.io.Reader;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.model.glory.FatherClassGlory;
import com.wandrell.tabletop.pendragon.service.model.ModelConstructorService;
import com.wandrell.tabletop.pendragon.util.parser.yaml.chargen.FatherClassGloryYAMLParser;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;

public final class ITParseFatherClassGloryYAMLParser {

    private FatherClassGlory glory;

    public ITParseFatherClassGloryYAMLParser() {
        super();
    }

    @BeforeClass
    public final void initialize() throws Exception {
        final Parser<Reader, FatherClassGlory> parser;
        final ModelConstructorService modelService;

        modelService = TestServiceFactory.getInstance()
                .getModelConstructorService();

        parser = new FatherClassGloryYAMLParser(modelService);

        glory = parser.parse(ResourceUtils
                .getClassPathReader(TestModelFileConf.FATHER_CLASS_GLORY));
    }

    @Test
    public final void testAnnualGlory() {
        Assert.assertEquals(glory.getYearlyGlory(), (Integer) 10);
    }

    @Test
    public final void testBaseGlory() {
        Assert.assertEquals(glory.getBaseGlory(), (Integer) 100);
    }

    @Test
    public final void testName() {
        Assert.assertEquals(glory.getFatherClass(), "test_father_glory");
    }

}
