package com.wandrell.tabletop.testing.pendragon.test.integration.outputter.util;

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
import com.wandrell.tabletop.pendragon.model.util.TextList;
import com.wandrell.tabletop.pendragon.service.model.ModelConstructorService;
import com.wandrell.tabletop.pendragon.util.parser.dictionary.util.TextListMapParser;
import com.wandrell.tabletop.pendragon.util.parser.yaml.util.TextListYAMLParser;
import com.wandrell.tabletop.testing.pendragon.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;

public final class ITSendTextListOutputter {

    private static final Random                         random        = new Random();
    private static final String                         TEMPLATE_PATH = "target/test_write_TextList_";
    private final Parser<TextList, Map<String, Object>> parserMap;

    {
        parserMap = new TextListMapParser();
    }

    public ITSendTextListOutputter() {
        super();
    }

    @Test
    public final void testWriteFile() throws Exception {
        final TextList list;
        final TextList listOut;
        final Parser<Reader, TextList> parser;
        final ModelConstructorService modelService;
        final Path pathOut;
        final Outputter<Object> outputter;

        outputter = new YAMLOutputter();

        modelService = TestServiceFactory.getInstance()
                .getModelConstructorService();

        parser = new TextListYAMLParser(modelService);

        list = parser.parse(ResourceUtils
                .getClassPathReader(TestModelFileConf.LIST));

        pathOut = Paths.get(TEMPLATE_PATH + getRandomID() + ".yml")
                .toAbsolutePath();

        outputter.output(parserMap.parse(list), new BufferedWriter(
                new FileWriter(pathOut.toFile())));

        listOut = parser.parse(new BufferedReader(new FileReader(pathOut
                .toFile())));

        Assert.assertEquals(listOut.getName(), list.getName());
        Assert.assertEquals(listOut.getValues(), list.getValues());
    }

    @Test
    public final void testWriteFile_Minimum() throws Exception {
        final TextList list;
        final TextList listOut;
        final Parser<Reader, TextList> parser;
        final ModelConstructorService modelService;
        final Path pathOut;
        final Outputter<Object> outputter;

        outputter = new YAMLOutputter();

        modelService = TestServiceFactory.getInstance()
                .getModelConstructorService();

        parser = new TextListYAMLParser(modelService);

        list = parser.parse(ResourceUtils
                .getClassPathReader(TestModelFileConf.LIST_MINIMUM));

        pathOut = Paths.get(TEMPLATE_PATH + getRandomID() + ".yml")
                .toAbsolutePath();

        outputter.output(parserMap.parse(list), new BufferedWriter(
                new FileWriter(pathOut.toFile())));

        listOut = parser.parse(new BufferedReader(new FileReader(pathOut
                .toFile())));

        Assert.assertEquals(listOut.getName(), list.getName());
        Assert.assertEquals(listOut.getValues(), list.getValues());
    }

    private final Integer getRandomID() {
        return random.nextInt(Integer.MAX_VALUE);
    }

}
