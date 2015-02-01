package com.wandrell.tabletop.testing.pendragon.framework.test.integration.file.read.glory;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.tabletop.business.model.pendragon.glory.FatherClassGlory;
import com.wandrell.tabletop.business.service.pendragon.ModelService;
import com.wandrell.tabletop.business.util.parser.xml.pendragon.input.glory.FatherClassGloryDocumentDecoder;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;
import com.wandrell.util.parser.InputParser;
import com.wandrell.util.parser.xml.input.JDOMDocumentDecoder;
import com.wandrell.util.parser.xml.input.StAXInputParser;

public final class ITReadFatherClassGloryDocumentDecoder {

    private FatherClassGlory glory;

    public ITReadFatherClassGloryDocumentDecoder() {
        super();
    }

    @BeforeClass
    public final void initialize() throws Exception {
        final InputParser<FatherClassGlory> parser;
        final JDOMDocumentDecoder<FatherClassGlory> processor;
        final ModelService modelService;

        modelService = TestServiceFactory.getInstance().getModelService();

        processor = new FatherClassGloryDocumentDecoder(modelService);
        parser = new StAXInputParser<FatherClassGlory>(processor);

        glory = parser.read(ResourceUtils
                .getClassPathInputStream(TestModelFileConf.FATHER_CLASS_GLORY));
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
