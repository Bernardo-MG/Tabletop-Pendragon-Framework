package com.wandrell.tabletop.testing.pendragon.framework.test.integration.parser.util;

import java.io.Reader;

import org.jdom2.Document;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.pattern.parser.xml.XMLFileParser;
import com.wandrell.tabletop.business.model.pendragon.util.TextList;
import com.wandrell.tabletop.business.service.pendragon.ModelService;
import com.wandrell.tabletop.business.util.parser.xml.pendragon.input.util.TextListDocumentParser;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;

public final class ITReadTextListDocumentParser {

    private TextList list;

    public ITReadTextListDocumentParser() {
        super();
    }

    @BeforeClass
    public final void initialize() throws Exception {
        final Parser<Reader, Document> parserFile;
        final Parser<Document, TextList> parserDoc;
        final ModelService modelService;

        modelService = TestServiceFactory.getInstance().getModelService();

        parserDoc = new TextListDocumentParser(modelService);
        parserFile = new XMLFileParser();

        list = parserDoc.parse(parserFile.parse(ResourceUtils
                .getClassPathReader(TestModelFileConf.LIST)));
    }

    @Test
    public final void testName() {
        Assert.assertEquals(list.getName(), "test_list");
    }

    @Test
    public final void testValues() {
        Assert.assertEquals(list.getValues().size(), 3);

        Assert.assertTrue(list.getValues().contains("value_1"));
        Assert.assertTrue(list.getValues().contains("value_2"));
        Assert.assertTrue(list.getValues().contains("value_3"));
    }

}
