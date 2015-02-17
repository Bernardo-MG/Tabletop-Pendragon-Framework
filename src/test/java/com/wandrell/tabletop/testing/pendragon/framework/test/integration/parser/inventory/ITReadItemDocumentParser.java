package com.wandrell.tabletop.testing.pendragon.framework.test.integration.parser.inventory;

import java.io.Reader;

import org.jdom2.Document;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.pattern.parser.xml.XMLFileParser;
import com.wandrell.tabletop.pendragon.model.inventory.Item;
import com.wandrell.tabletop.pendragon.service.ModelService;
import com.wandrell.tabletop.pendragon.util.parser.xml.input.inventory.ItemDocumentParser;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;

public final class ITReadItemDocumentParser {

    private Item item;

    public ITReadItemDocumentParser() {
        super();
    }

    @BeforeClass
    public final void initialize() throws Exception {
        final Parser<Reader, Document> parserFile;
        final Parser<Document, Item> parserDoc;
        final ModelService modelService;

        modelService = TestServiceFactory.getInstance().getModelService();

        parserDoc = new ItemDocumentParser(modelService);
        parserFile = new XMLFileParser();

        item = parserDoc.parse(parserFile.parse(ResourceUtils
                .getClassPathReader(TestModelFileConf.ITEM)));
    }

    @Test
    public final void testDescription() {
        Assert.assertEquals(item.getDescription(), "description_1");
    }

    @Test
    public final void testName() {
        Assert.assertEquals(item.getName(), "test_item");
    }

}
