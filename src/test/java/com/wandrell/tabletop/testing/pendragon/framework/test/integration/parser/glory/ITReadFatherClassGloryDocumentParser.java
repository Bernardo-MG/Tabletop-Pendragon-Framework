package com.wandrell.tabletop.testing.pendragon.framework.test.integration.parser.glory;

import java.io.Reader;

import org.jdom2.Document;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.pattern.parser.xml.XMLFileParser;
import com.wandrell.tabletop.pendragon.model.glory.FatherClassGlory;
import com.wandrell.tabletop.pendragon.service.ModelService;
import com.wandrell.tabletop.pendragon.util.parser.xml.input.chargen.FatherClassGloryDocumentParser;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;

public final class ITReadFatherClassGloryDocumentParser {

    private FatherClassGlory glory;

    public ITReadFatherClassGloryDocumentParser() {
        super();
    }

    @BeforeClass
    public final void initialize() throws Exception {
        final Parser<Reader, Document> parserFile;
        final Parser<Document, FatherClassGlory> parserDoc;
        final ModelService modelService;

        modelService = TestServiceFactory.getInstance().getModelService();

        parserDoc = new FatherClassGloryDocumentParser(modelService);
        parserFile = new XMLFileParser();

        glory = parserDoc.parse(parserFile.parse(ResourceUtils
                .getClassPathReader(TestModelFileConf.FATHER_CLASS_GLORY)));
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
