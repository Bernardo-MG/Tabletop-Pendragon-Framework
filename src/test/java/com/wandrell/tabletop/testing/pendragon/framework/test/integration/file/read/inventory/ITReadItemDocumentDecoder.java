package com.wandrell.tabletop.testing.pendragon.framework.test.integration.file.read.inventory;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.pattern.parser.InputParser;
import com.wandrell.pattern.parser.xml.input.JDOMDocumentDecoder;
import com.wandrell.pattern.parser.xml.input.StAXInputParser;
import com.wandrell.tabletop.business.model.pendragon.inventory.Item;
import com.wandrell.tabletop.business.service.pendragon.ModelService;
import com.wandrell.tabletop.business.util.parser.xml.pendragon.input.inventory.ItemDocumentDecoder;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;

public final class ITReadItemDocumentDecoder {

    private Item item;

    public ITReadItemDocumentDecoder() {
        super();
    }

    @BeforeClass
    public final void initialize() throws Exception {
        final InputParser<Item> parser;
        final JDOMDocumentDecoder<Item> processor;
        final ModelService modelService;

        modelService = TestServiceFactory.getInstance().getModelService();

        processor = new ItemDocumentDecoder(modelService);
        parser = new StAXInputParser<Item>(processor);

        item = parser.read(ResourceUtils
                .getClassPathInputStream(TestModelFileConf.ITEM));
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
