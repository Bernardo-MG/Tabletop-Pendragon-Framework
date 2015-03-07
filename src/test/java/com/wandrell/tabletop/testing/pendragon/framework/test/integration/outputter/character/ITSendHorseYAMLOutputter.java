package com.wandrell.tabletop.testing.pendragon.framework.test.integration.outputter.character;

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
import com.wandrell.tabletop.pendragon.model.character.Horse;
import com.wandrell.tabletop.pendragon.service.model.ModelConstructorService;
import com.wandrell.tabletop.pendragon.util.parser.dictionary.character.HorseMapParser;
import com.wandrell.tabletop.pendragon.util.parser.yaml.character.HorseYAMLParser;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;

public final class ITSendHorseYAMLOutputter {

    private static final Random                      random        = new Random();
    private static final String                      TEMPLATE_PATH = "target/test_write_Horse_";
    private final Parser<Horse, Map<String, Object>> parserMap;

    {
        parserMap = new HorseMapParser();
    }

    public ITSendHorseYAMLOutputter() {
        super();
    }

    @Test
    public final void testWriteFile() throws Exception {
        final Horse horse;
        final Horse horseOut;
        final Parser<Reader, Horse> parser;
        final ModelConstructorService modelService;
        final Path pathOut;
        final Outputter<Object> outputter;

        outputter = new YAMLOutputter();

        modelService = TestServiceFactory.getInstance()
                .getModelConstructorService();

        parser = new HorseYAMLParser(modelService);

        horse = parser.parse(ResourceUtils
                .getClassPathReader(TestModelFileConf.HORSE));

        pathOut = Paths.get(TEMPLATE_PATH + getRandomID() + ".yml")
                .toAbsolutePath();

        outputter.send(parserMap.parse(horse), new BufferedWriter(
                new FileWriter(pathOut.toFile())));

        horseOut = parser.parse(new BufferedReader(new FileReader(pathOut
                .toFile())));

        Assert.assertEquals(horse.getHorseType(), horseOut.getHorseType());

        Assert.assertEquals(horse.getNaturalArmor(), horseOut.getNaturalArmor());

        Assert.assertEquals(horse.getConstitution(), horseOut.getConstitution());
        Assert.assertEquals(horse.getDexterity(), horseOut.getDexterity());
        Assert.assertEquals(horse.getSize(), horseOut.getSize());
        Assert.assertEquals(horse.getStrength(), horseOut.getStrength());

        Assert.assertEquals(horse.getDamage(), horseOut.getDamage());
        Assert.assertEquals(horse.getMovementRate(), horseOut.getMovementRate());

        Assert.assertEquals(horse.isArmored(), horseOut.isArmored());
        Assert.assertEquals(horse.isCombatHorse(), horseOut.isCombatHorse());
        Assert.assertEquals(horse.isHuntingHorse(), horseOut.isHuntingHorse());
    }

    @Test
    public final void testWriteFile_Minimum() throws Exception {
        final Horse horse;
        final Horse horseOut;
        final Parser<Reader, Horse> parser;
        final ModelConstructorService modelService;
        final Path pathOut;
        final Outputter<Object> outputter;

        outputter = new YAMLOutputter();

        modelService = TestServiceFactory.getInstance()
                .getModelConstructorService();

        parser = new HorseYAMLParser(modelService);

        horse = parser.parse(ResourceUtils
                .getClassPathReader(TestModelFileConf.HORSE_MINIMUM));

        pathOut = Paths.get(TEMPLATE_PATH + getRandomID() + ".yml")
                .toAbsolutePath();

        outputter.send(parserMap.parse(horse), new BufferedWriter(
                new FileWriter(pathOut.toFile())));

        horseOut = parser.parse(new BufferedReader(new FileReader(pathOut
                .toFile())));

        Assert.assertEquals(horse.getHorseType(), horseOut.getHorseType());

        Assert.assertEquals(horse.getNaturalArmor(), horseOut.getNaturalArmor());

        Assert.assertEquals(horse.getConstitution(), horseOut.getConstitution());
        Assert.assertEquals(horse.getDexterity(), horseOut.getDexterity());
        Assert.assertEquals(horse.getSize(), horseOut.getSize());
        Assert.assertEquals(horse.getStrength(), horseOut.getStrength());

        Assert.assertEquals(horse.getDamage(), horseOut.getDamage());
        Assert.assertEquals(horse.getMovementRate(), horseOut.getMovementRate());

        Assert.assertEquals(horse.isArmored(), horseOut.isArmored());
        Assert.assertEquals(horse.isCombatHorse(), horseOut.isCombatHorse());
        Assert.assertEquals(horse.isHuntingHorse(), horseOut.isHuntingHorse());
    }

    private final Integer getRandomID() {
        return random.nextInt(Integer.MAX_VALUE);
    }

}
