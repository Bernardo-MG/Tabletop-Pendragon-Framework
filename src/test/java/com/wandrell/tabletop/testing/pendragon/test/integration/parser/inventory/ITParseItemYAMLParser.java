package com.wandrell.tabletop.testing.pendragon.test.integration.parser.inventory;

import java.io.Reader;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.model.inventory.Item;
import com.wandrell.tabletop.pendragon.service.model.ModelConstructorService;
import com.wandrell.tabletop.pendragon.util.parser.yaml.inventory.ItemYAMLParser;
import com.wandrell.tabletop.testing.pendragon.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;

public final class ITParseItemYAMLParser {

    private Item item;

    public ITParseItemYAMLParser() {
        super();
    }

    @BeforeClass
    public final void initialize() throws Exception {
        final Parser<Reader, Item> parser;
        final ModelConstructorService modelService;

        modelService = TestServiceFactory.getInstance()
                .getModelConstructorService();

        parser = new ItemYAMLParser(modelService);

        item = parser.parse(ResourceUtils
                .getClassPathReader(TestModelFileConf.ITEM));
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
