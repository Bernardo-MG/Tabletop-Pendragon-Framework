package com.wandrell.tabletop.testing.pendragon.test.integration.outputter.chargen;

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
import com.wandrell.tabletop.pendragon.model.chargen.region.RegionTemplate;
import com.wandrell.tabletop.pendragon.service.model.ModelConstructorService;
import com.wandrell.tabletop.pendragon.util.parser.dictionary.chargen.RegionTemplateMapParser;
import com.wandrell.tabletop.pendragon.util.parser.yaml.chargen.RegionTemplateYAMLParser;
import com.wandrell.tabletop.testing.pendragon.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;

public final class ITSendRegionTemplateYAMLOutputter {

    private static final Random                               random        = new Random();
    private static final String                               TEMPLATE_PATH = "target/test_write_HomelandTemplate_";
    private final Parser<RegionTemplate, Map<String, Object>> parserMap;

    {
        parserMap = new RegionTemplateMapParser();
    }

    public ITSendRegionTemplateYAMLOutputter() {
        super();
    }

    @Test
    public final void testWriteFile() throws Exception {
        final RegionTemplate region;
        final RegionTemplate regionOut;
        final Parser<Reader, RegionTemplate> parser;
        final ModelConstructorService modelService;
        final Path pathOut;
        final Outputter<Object> outputter;

        outputter = new YAMLOutputter();

        modelService = TestServiceFactory.getInstance()
                .getModelConstructorService();

        parser = new RegionTemplateYAMLParser(modelService);

        region = parser.parse(ResourceUtils
                .getClassPathReader(TestModelFileConf.REGION));

        pathOut = Paths.get(TEMPLATE_PATH + getRandomID() + ".yml")
                .toAbsolutePath();

        outputter.send(parserMap.parse(region), new BufferedWriter(
                new FileWriter(pathOut.toFile())));

        regionOut = parser.parse(new BufferedReader(new FileReader(pathOut
                .toFile())));

        Assert.assertEquals(regionOut.getName(), region.getName());
        Assert.assertEquals(regionOut.getTraits(), region.getTraits());
    }

    @Test
    public final void testWriteFile_Minimum() throws Exception {
        final RegionTemplate region;
        final RegionTemplate regionOut;
        final Parser<Reader, RegionTemplate> parser;
        final ModelConstructorService modelService;
        final Path pathOut;
        final Outputter<Object> outputter;

        outputter = new YAMLOutputter();

        modelService = TestServiceFactory.getInstance()
                .getModelConstructorService();

        parser = new RegionTemplateYAMLParser(modelService);

        region = parser.parse(ResourceUtils
                .getClassPathReader(TestModelFileConf.HOMELAND_MINIMUM));

        pathOut = Paths.get(TEMPLATE_PATH + getRandomID() + ".yml")
                .toAbsolutePath();

        outputter.send(parserMap.parse(region), new BufferedWriter(
                new FileWriter(pathOut.toFile())));

        regionOut = parser.parse(new BufferedReader(new FileReader(pathOut
                .toFile())));

        Assert.assertEquals(regionOut.getName(), region.getName());
        Assert.assertEquals(regionOut.getTraits(), region.getTraits());
    }

    private final Integer getRandomID() {
        return random.nextInt(Integer.MAX_VALUE);
    }

}
