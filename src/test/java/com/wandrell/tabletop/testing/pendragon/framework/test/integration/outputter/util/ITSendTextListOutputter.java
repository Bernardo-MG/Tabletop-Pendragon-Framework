package com.wandrell.tabletop.testing.pendragon.framework.test.integration.outputter.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.pattern.outputter.Outputter;
import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.model.util.TextList;
import com.wandrell.tabletop.pendragon.service.ModelService;
import com.wandrell.tabletop.pendragon.util.outputter.util.TextListYAMLOutputter;
import com.wandrell.tabletop.pendragon.util.parser.yaml.util.TextListYAMLParser;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;

public final class ITSendTextListOutputter {

    private static final Random       random        = new Random();
    private static final String       TEMPLATE_PATH = "target/test_write_TextList_";
    private final Outputter<TextList> outputter;

    {
        outputter = new TextListYAMLOutputter();
    }

    public ITSendTextListOutputter() {
        super();
    }

    @Test
    public final void testWriteFile() throws Exception {
        final TextList list;
        final TextList listOut;
        final Parser<Reader, TextList> parser;
        final ModelService modelService;
        final Path pathOut;

        modelService = TestServiceFactory.getInstance().getModelService();

        parser = new TextListYAMLParser(modelService);

        list = parser.parse(ResourceUtils
                .getClassPathReader(TestModelFileConf.LIST));

        pathOut = Paths.get(TEMPLATE_PATH + getRandomID() + ".yml")
                .toAbsolutePath();

        outputter.send(list, new BufferedWriter(
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
