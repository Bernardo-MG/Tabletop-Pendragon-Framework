package com.wandrell.tabletop.testing.pendragon.test.integration.outputter.inventory;

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
import com.wandrell.tabletop.pendragon.model.inventory.armor.Shield;
import com.wandrell.tabletop.pendragon.service.model.ModelConstructorService;
import com.wandrell.tabletop.pendragon.util.parser.dictionary.inventory.ShieldMapParser;
import com.wandrell.tabletop.pendragon.util.parser.yaml.inventory.ShieldYAMLParser;
import com.wandrell.tabletop.testing.pendragon.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;

public final class ITSendShieldYAMLOutputter {

    private static final Random                       random        = new Random();
    private static final String                       TEMPLATE_PATH = "target/test_write_Shield_";
    private final Parser<Shield, Map<String, Object>> parserMap;

    {
        parserMap = new ShieldMapParser();
    }

    public ITSendShieldYAMLOutputter() {
        super();
    }

    @Test
    public final void testWriteFile() throws Exception {
        final Shield shield;
        final Shield shieldOut;
        final Parser<Reader, Shield> parser;
        final ModelConstructorService modelService;
        final Path pathOut;
        final Outputter<Object> outputter;

        outputter = new YAMLOutputter();

        modelService = TestServiceFactory.getInstance()
                .getModelConstructorService();

        parser = new ShieldYAMLParser(modelService);

        shield = parser.parse(ResourceUtils
                .getClassPathReader(TestModelFileConf.SHIELD));

        pathOut = Paths.get(TEMPLATE_PATH + getRandomID() + ".yml")
                .toAbsolutePath();

        outputter.output(parserMap.parse(shield), new BufferedWriter(
                new FileWriter(pathOut.toFile())));

        shieldOut = parser.parse(new BufferedReader(new FileReader(pathOut
                .toFile())));

        Assert.assertEquals(shieldOut.getName(), shield.getName());
        Assert.assertEquals(shieldOut.getArmorValue(), shield.getArmorValue());
    }

    private final Integer getRandomID() {
        return random.nextInt(Integer.MAX_VALUE);
    }

}
