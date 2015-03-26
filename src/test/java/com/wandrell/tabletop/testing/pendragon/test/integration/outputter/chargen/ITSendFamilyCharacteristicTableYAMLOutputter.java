package com.wandrell.tabletop.testing.pendragon.test.integration.outputter.chargen;

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
import com.wandrell.tabletop.interval.Interval;
import com.wandrell.tabletop.pendragon.model.chargen.background.FamilyCharacteristicTable;
import com.wandrell.tabletop.pendragon.model.chargen.background.FamilyCharacteristicTemplate;
import com.wandrell.tabletop.pendragon.service.model.ModelConstructorService;
import com.wandrell.tabletop.pendragon.util.parser.dictionary.chargen.FamilyCharacteristicTableMapParser;
import com.wandrell.tabletop.pendragon.util.parser.yaml.chargen.FamilyCharacteristicTableYAMLParser;
import com.wandrell.tabletop.testing.pendragon.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;

public final class ITSendFamilyCharacteristicTableYAMLOutputter {

    private static final Random                                          random        = new Random();
    private static final String                                          TEMPLATE_PATH = "target/test_write_FamilyCharacteristicTable_";
    private final Parser<FamilyCharacteristicTable, Map<String, Object>> parserMap;

    {
        parserMap = new FamilyCharacteristicTableMapParser();
    }

    public ITSendFamilyCharacteristicTableYAMLOutputter() {
        super();
    }

    @Test
    public final void testWriteFile() throws Exception {
        final FamilyCharacteristicTable table;
        final FamilyCharacteristicTable tableOut;
        final Parser<Reader, FamilyCharacteristicTable> parser;
        final ModelConstructorService modelService;
        final Path pathOut;
        final Outputter<Object> outputter;

        outputter = new YAMLOutputter();

        modelService = TestServiceFactory.getInstance()
                .getModelConstructorService();

        parser = new FamilyCharacteristicTableYAMLParser(modelService);

        table = parser.parse(ResourceUtils
                .getClassPathReader(TestModelFileConf.FAMILY_CHARACTERISTIC));

        pathOut = Paths.get(TEMPLATE_PATH + getRandomID() + ".yml")
                .toAbsolutePath();

        outputter.send(parserMap.parse(table), new BufferedWriter(
                new FileWriter(pathOut.toFile())));

        tableOut = parser.parse(new BufferedReader(new FileReader(pathOut
                .toFile())));

        Assert.assertEquals(tableOut.getName(), table.getName());

        assertEquals(tableOut.getIntervals(), table.getIntervals());
    }

    @Test
    public final void testWriteFile_Minimum() throws Exception {
        final FamilyCharacteristicTable table;
        final FamilyCharacteristicTable tableOut;
        final Parser<Reader, FamilyCharacteristicTable> parser;
        final ModelConstructorService modelService;
        final Path pathOut;
        final Outputter<Object> outputter;

        outputter = new YAMLOutputter();

        modelService = TestServiceFactory.getInstance()
                .getModelConstructorService();

        parser = new FamilyCharacteristicTableYAMLParser(modelService);

        table = parser
                .parse(ResourceUtils
                        .getClassPathReader(TestModelFileConf.FAMILY_CHARACTERISTIC_MINIMUM));

        pathOut = Paths.get(TEMPLATE_PATH + getRandomID() + ".yml")
                .toAbsolutePath();

        outputter.send(parserMap.parse(table), new BufferedWriter(
                new FileWriter(pathOut.toFile())));

        tableOut = parser.parse(new BufferedReader(new FileReader(pathOut
                .toFile())));

        Assert.assertEquals(tableOut.getName(), table.getName());

        assertEquals(tableOut.getIntervals(), table.getIntervals());
    }

    private final void assertEquals(
            final Map<Interval, FamilyCharacteristicTemplate> actual,
            final Map<Interval, FamilyCharacteristicTemplate> expected) {
        final Iterator<Entry<Interval, FamilyCharacteristicTemplate>> itrActual;
        final Iterator<Entry<Interval, FamilyCharacteristicTemplate>> itrExpected;
        Entry<Interval, FamilyCharacteristicTemplate> entryActual;
        Entry<Interval, FamilyCharacteristicTemplate> entryExpected;

        itrActual = actual.entrySet().iterator();
        itrExpected = expected.entrySet().iterator();

        while (itrActual.hasNext()) {
            entryActual = itrActual.next();
            entryExpected = itrExpected.next();

            Assert.assertEquals(entryActual.getKey(), entryExpected.getKey());
            Assert.assertEquals(entryActual.getValue().getAttributes(),
                    entryExpected.getValue().getAttributes());
            Assert.assertEquals(entryActual.getValue().getSkills(),
                    entryExpected.getValue().getSkills());
        }
    }

    private final Integer getRandomID() {
        return random.nextInt(Integer.MAX_VALUE);
    }

}
