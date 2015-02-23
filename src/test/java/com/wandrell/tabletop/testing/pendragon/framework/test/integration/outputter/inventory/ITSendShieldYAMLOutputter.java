package com.wandrell.tabletop.testing.pendragon.framework.test.integration.outputter.inventory;

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
import com.wandrell.tabletop.pendragon.model.inventory.Shield;
import com.wandrell.tabletop.pendragon.service.ModelService;
import com.wandrell.tabletop.pendragon.util.outputter.inventory.ShieldYAMLOutputter;
import com.wandrell.tabletop.pendragon.util.parser.inventory.ShieldYAMLParser;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;

public final class ITSendShieldYAMLOutputter {

    private static final Random     random        = new Random();
    private static final String     TEMPLATE_PATH = "target/test_write_Shield_";
    private final Outputter<Shield> outputter;

    {
        outputter = new ShieldYAMLOutputter();
    }

    public ITSendShieldYAMLOutputter() {
        super();
    }

    @Test
    public final void testWriteFile() throws Exception {
        final Shield shield;
        final Shield shieldOut;
        final Parser<Reader, Shield> parser;
        final ModelService modelService;
        final Path pathOut;

        modelService = TestServiceFactory.getInstance().getModelService();

        parser = new ShieldYAMLParser(modelService);

        shield = parser.parse(ResourceUtils
                .getClassPathReader(TestModelFileConf.SHIELD));

        pathOut = Paths.get(TEMPLATE_PATH + getRandomID() + ".yml")
                .toAbsolutePath();

        outputter.send(shield,
                new BufferedWriter(new FileWriter(pathOut.toFile())));

        shieldOut = parser.parse(new BufferedReader(new FileReader(pathOut
                .toFile())));

        Assert.assertEquals(shieldOut.getName(), shield.getName());
        Assert.assertEquals(shieldOut.getArmorValue(), shield.getArmorValue());
    }

    private final Integer getRandomID() {
        return random.nextInt(Integer.MAX_VALUE);
    }

}
