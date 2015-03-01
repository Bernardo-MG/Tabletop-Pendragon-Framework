package com.wandrell.tabletop.testing.pendragon.framework.test.integration.outputter.inventory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.pattern.outputter.Outputter;
import com.wandrell.pattern.outputter.yaml.YAMLOutputter;
import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.model.inventory.Item;
import com.wandrell.tabletop.pendragon.service.model.ModelService;
import com.wandrell.tabletop.pendragon.util.parser.dictionary.inventory.ItemMapParser;
import com.wandrell.tabletop.pendragon.util.parser.yaml.inventory.ItemYAMLParser;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;

public final class ITSendItemYAMLOutputter {

    private static final Random                     random        = new Random();
    private static final String                     TEMPLATE_PATH = "target/test_write_Item_";
    private final Parser<Item, Map<String, Object>> parserMap;

    {
        parserMap = new ItemMapParser();
    }

    public ITSendItemYAMLOutputter() {
        super();
    }

    @Test
    public final void testWriteFile() throws Exception {
        final Item item;
        final Item itemOut;
        final Parser<Reader, Item> parser;
        final ModelService modelService;
        final Path pathOut;
        final Outputter<Object> outputter;

        outputter = new YAMLOutputter();

        modelService = TestServiceFactory.getInstance().getModelService();

        parser = new ItemYAMLParser(modelService);

        item = parser.parse(ResourceUtils
                .getClassPathReader(TestModelFileConf.ITEM));

        pathOut = Paths.get(TEMPLATE_PATH + getRandomID() + ".yml")
                .toAbsolutePath();

        outputter.send(parserMap.parse(item), new BufferedWriter(
                new FileWriter(pathOut.toFile())));

        itemOut = parser.parse(new BufferedReader(new FileReader(pathOut
                .toFile())));

        Assert.assertEquals(item.getName(), itemOut.getName());
        Assert.assertEquals(item.getDescription(), itemOut.getDescription());
    }

    private final Integer getRandomID() {
        return random.nextInt(Integer.MAX_VALUE);
    }

}
