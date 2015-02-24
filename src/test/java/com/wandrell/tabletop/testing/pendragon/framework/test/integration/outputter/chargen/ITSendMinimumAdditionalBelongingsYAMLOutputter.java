package com.wandrell.tabletop.testing.pendragon.framework.test.integration.outputter.chargen;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.pattern.outputter.Outputter;
import com.wandrell.pattern.outputter.yaml.YAMLOutputter;
import com.wandrell.pattern.parser.Parser;
import com.wandrell.pattern.repository.Repository;
import com.wandrell.tabletop.interval.Interval;
import com.wandrell.tabletop.pendragon.model.character.Horse;
import com.wandrell.tabletop.pendragon.model.chargen.AdditionalBelongings;
import com.wandrell.tabletop.pendragon.model.chargen.AdditionalBelongingsTable;
import com.wandrell.tabletop.pendragon.model.inventory.Item;
import com.wandrell.tabletop.pendragon.model.inventory.Shield;
import com.wandrell.tabletop.pendragon.model.inventory.Weapon;
import com.wandrell.tabletop.pendragon.model.manor.Pet;
import com.wandrell.tabletop.pendragon.service.ModelService;
import com.wandrell.tabletop.pendragon.util.parser.dictionary.chargen.AdditionalBelongingsMapParser;
import com.wandrell.tabletop.pendragon.util.parser.yaml.chargen.AdditionalBelongingsTableYAMLParser;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;

public final class ITSendMinimumAdditionalBelongingsYAMLOutputter {

    private static final Random                                          random        = new Random();
    private static final String                                          TEMPLATE_PATH = "target/test_write_AdditionalBelongingsTable_";
    private final Parser<AdditionalBelongingsTable, Map<String, Object>> parserMap;

    {
        parserMap = new AdditionalBelongingsMapParser();
    }

    public ITSendMinimumAdditionalBelongingsYAMLOutputter() {
        super();
    }

    @Test
    public final void testWriteFile() throws Exception {
        final AdditionalBelongingsTable table;
        final AdditionalBelongingsTable tableOut;
        final Parser<Reader, AdditionalBelongingsTable> parser;
        final TestServiceFactory factory;
        final ModelService modelService;
        final Repository<Horse> horseRepository;
        final Repository<Item> itemRepository;
        final Repository<Pet> petRepository;
        final Repository<Shield> shieldRepository;
        final Repository<Weapon> weaponRepository;
        final Path pathOut;
        final Outputter<Object> outputter;

        outputter = new YAMLOutputter();

        factory = TestServiceFactory.getInstance();

        modelService = factory.getModelService();

        horseRepository = factory.getHorseRepository();
        itemRepository = factory.getItemRepository();
        petRepository = factory.getPetRepository();
        shieldRepository = factory.getShieldRepository();
        weaponRepository = factory.getWeaponRepository();

        parser = new AdditionalBelongingsTableYAMLParser(modelService,
                horseRepository, itemRepository, petRepository,
                shieldRepository, weaponRepository);

        table = parser
                .parse(ResourceUtils
                        .getClassPathReader(TestModelFileConf.ADDITIONAL_BELONGINGS_MINIMUM));

        pathOut = Paths.get(TEMPLATE_PATH + getRandomID() + ".yml")
                .toAbsolutePath();

        outputter.send(parserMap.parse(table), new BufferedWriter(
                new FileWriter(pathOut.toFile())));

        tableOut = parser.parse(new BufferedReader(new FileReader(pathOut
                .toFile())));

        testEquals(table, tableOut);
    }

    private final Integer getRandomID() {
        return random.nextInt(Integer.MAX_VALUE);
    }

    private final void testEquals(final AdditionalBelongingsTable tableIn,
            final AdditionalBelongingsTable tableOut) {
        final Iterator<Entry<Interval, AdditionalBelongings>> itrIn;
        final Iterator<Entry<Interval, AdditionalBelongings>> itrOut;
        Entry<Interval, AdditionalBelongings> entryIn;
        Entry<Interval, AdditionalBelongings> entryOut;

        Assert.assertEquals(tableIn.getName(), tableOut.getName());

        itrIn = tableIn.getIntervals().entrySet().iterator();
        itrOut = tableOut.getIntervals().entrySet().iterator();

        while (itrIn.hasNext()) {
            entryIn = itrIn.next();
            entryOut = itrOut.next();

            Assert.assertEquals(entryIn.getKey(), entryOut.getKey());

            Assert.assertEquals(entryIn.getValue().getMoneyName(), entryOut
                    .getValue().getMoneyName());

            Assert.assertEquals(entryIn.getValue().getHorses(), entryOut
                    .getValue().getHorses());
            Assert.assertEquals(entryIn.getValue().getItems(), entryOut
                    .getValue().getItems());
            Assert.assertEquals(entryIn.getValue().getPets(), entryOut
                    .getValue().getPets());
            Assert.assertEquals(entryIn.getValue().getRerolls(), entryOut
                    .getValue().getRerolls());
            Assert.assertEquals(entryIn.getValue().getShields(), entryOut
                    .getValue().getShields());
            Assert.assertEquals(entryIn.getValue().getRerollTable(), entryOut
                    .getValue().getRerollTable());
            Assert.assertEquals(entryIn.getValue().getShields(), entryOut
                    .getValue().getShields());
            Assert.assertEquals(entryIn.getValue().getWeapons(), entryOut
                    .getValue().getWeapons());

            Assert.assertEquals(entryIn.getValue().getMoney().getLibra(),
                    entryOut.getValue().getMoney().getLibra());
            Assert.assertEquals(entryIn.getValue().getMoney().getDenarii(),
                    entryOut.getValue().getMoney().getDenarii());
        }
    }

}
