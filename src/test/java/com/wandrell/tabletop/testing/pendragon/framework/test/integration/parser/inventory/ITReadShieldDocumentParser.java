package com.wandrell.tabletop.testing.pendragon.framework.test.integration.parser.inventory;

import java.io.Reader;

import org.jdom2.Document;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.pattern.parser.xml.XMLFileParser;
import com.wandrell.tabletop.pendragon.model.inventory.Shield;
import com.wandrell.tabletop.pendragon.service.ModelService;
import com.wandrell.tabletop.pendragon.util.parser.xml.input.inventory.ShieldDocumentParser;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;

public final class ITReadShieldDocumentParser {

    private Shield shield;

    public ITReadShieldDocumentParser() {
        super();
    }

    @BeforeClass
    public final void initialize() throws Exception {
        final Parser<Reader, Document> parserFile;
        final Parser<Document, Shield> parserDoc;
        final ModelService modelService;

        modelService = TestServiceFactory.getInstance().getModelService();

        parserDoc = new ShieldDocumentParser(modelService);
        parserFile = new XMLFileParser();

        shield = parserDoc.parse(parserFile.parse(ResourceUtils
                .getClassPathReader(TestModelFileConf.SHIELD)));
    }

    @Test
    public final void testArmorValue() {
        Assert.assertEquals(shield.getArmorValue(), (Integer) 11);
    }

    @Test
    public final void testName() {
        Assert.assertEquals(shield.getName(), "test_shield");
    }

}
