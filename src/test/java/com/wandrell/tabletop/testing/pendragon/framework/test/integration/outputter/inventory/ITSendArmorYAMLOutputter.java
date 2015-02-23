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
import com.wandrell.tabletop.pendragon.model.inventory.Armor;
import com.wandrell.tabletop.pendragon.service.ModelService;
import com.wandrell.tabletop.pendragon.util.outputter.inventory.ArmorYAMLOutputter;
import com.wandrell.tabletop.pendragon.util.parser.yaml.inventory.ArmorYAMLParser;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;

public final class ITSendArmorYAMLOutputter {

    private static final Random    random        = new Random();
    private static final String    TEMPLATE_PATH = "target/test_write_Armor_";
    private final Outputter<Armor> outputter;

    {
        outputter = new ArmorYAMLOutputter();
    }

    public ITSendArmorYAMLOutputter() {
        super();
    }

    @Test
    public final void testWriteFile() throws Exception {
        final Armor armor;
        final Armor armorOut;
        final Parser<Reader, Armor> parser;
        final ModelService modelService;
        final Path pathOut;

        modelService = TestServiceFactory.getInstance().getModelService();

        parser = new ArmorYAMLParser(modelService);

        armor = parser.parse(ResourceUtils
                .getClassPathReader(TestModelFileConf.ARMOR));

        pathOut = Paths.get(TEMPLATE_PATH + getRandomID() + ".yml")
                .toAbsolutePath();

        outputter.send(armor,
                new BufferedWriter(new FileWriter(pathOut.toFile())));

        armorOut = parser.parse(new BufferedReader(new FileReader(pathOut
                .toFile())));

        Assert.assertEquals(armorOut.getName(), armor.getName());
        Assert.assertEquals(armorOut.getArmorType(), armor.getArmorType());
        Assert.assertEquals(armorOut.isHeavyLoad(), armor.isHeavyLoad());
        Assert.assertEquals(armorOut.getArmorValue(), armor.getArmorValue());
        Assert.assertEquals(armorOut.getDexterityModifier(),
                armor.getDexterityModifier());
    }

    private final Integer getRandomID() {
        return random.nextInt(Integer.MAX_VALUE);
    }

}
