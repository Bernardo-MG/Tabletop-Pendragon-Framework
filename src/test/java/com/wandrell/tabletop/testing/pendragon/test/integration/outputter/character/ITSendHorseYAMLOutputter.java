package com.wandrell.tabletop.testing.pendragon.test.integration.outputter.character;

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
import com.wandrell.tabletop.testing.pendragon.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.conf.factory.TestServiceFactory;
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

        outputter.output(parserMap.parse(horse), new BufferedWriter(
                new FileWriter(pathOut.toFile())));

        horseOut = parser.parse(new BufferedReader(new FileReader(pathOut
                .toFile())));

        Assert.assertEquals(horse.getHorseType(), horseOut.getHorseType());

        Assert.assertEquals(horse.getNaturalArmor(), horseOut.getNaturalArmor());

        Assert.assertEquals(horse.getAttributes().getConstitution(), horseOut
                .getAttributes().getConstitution());
        Assert.assertEquals(horse.getAttributes().getDexterity(), horseOut
                .getAttributes().getDexterity());
        Assert.assertEquals(horse.getAttributes().getSize(), horseOut
                .getAttributes().getSize());
        Assert.assertEquals(horse.getAttributes().getStrength(), horseOut
                .getAttributes().getStrength());

        Assert.assertEquals(horse.getDerivedAttributes().getDamage(), horseOut
                .getDerivedAttributes().getDamage());
        Assert.assertEquals(horse.getDerivedAttributes().getMoveRate(),
                horseOut.getDerivedAttributes().getMoveRate());

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

        outputter.output(parserMap.parse(horse), new BufferedWriter(
                new FileWriter(pathOut.toFile())));

        horseOut = parser.parse(new BufferedReader(new FileReader(pathOut
                .toFile())));

        Assert.assertEquals(horse.getHorseType(), horseOut.getHorseType());

        Assert.assertEquals(horse.getNaturalArmor(), horseOut.getNaturalArmor());

        Assert.assertEquals(horse.getAttributes().getConstitution(), horseOut
                .getAttributes().getConstitution());
        Assert.assertEquals(horse.getAttributes().getDexterity(), horseOut
                .getAttributes().getDexterity());
        Assert.assertEquals(horse.getAttributes().getSize(), horseOut
                .getAttributes().getSize());
        Assert.assertEquals(horse.getAttributes().getStrength(), horseOut
                .getAttributes().getStrength());

        Assert.assertEquals(horse.getDerivedAttributes().getDamage(), horseOut
                .getDerivedAttributes().getDamage());
        Assert.assertEquals(horse.getDerivedAttributes().getMoveRate(),
                horseOut.getDerivedAttributes().getMoveRate());

        Assert.assertEquals(horse.isArmored(), horseOut.isArmored());
        Assert.assertEquals(horse.isCombatHorse(), horseOut.isCombatHorse());
        Assert.assertEquals(horse.isHuntingHorse(), horseOut.isHuntingHorse());
    }

    private final Integer getRandomID() {
        return random.nextInt(Integer.MAX_VALUE);
    }

}
