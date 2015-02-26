package com.wandrell.tabletop.testing.pendragon.framework.test.integration.outputter.chargen;

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
import com.wandrell.pattern.repository.Repository;
import com.wandrell.tabletop.pendragon.model.chargen.AdditionalBelongingsTable;
import com.wandrell.tabletop.pendragon.model.chargen.CultureCharacterTemplate;
import com.wandrell.tabletop.pendragon.model.chargen.CultureTemplate;
import com.wandrell.tabletop.pendragon.model.chargen.FamilyCharacteristicTemplate;
import com.wandrell.tabletop.pendragon.service.ModelService;
import com.wandrell.tabletop.pendragon.util.parser.dictionary.chargen.CultureTemplateMapParser;
import com.wandrell.tabletop.pendragon.util.parser.yaml.chargen.CultureYAMLParser;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;

public final class ITSendCultureTemplateYAMLOutputter {

    private static final Random                                random        = new Random();
    private static final String                                TEMPLATE_PATH = "target/test_write_CultureTemplate_";
    private final Parser<CultureTemplate, Map<String, Object>> parserMap;

    {
        parserMap = new CultureTemplateMapParser();
    }

    public ITSendCultureTemplateYAMLOutputter() {
        super();
    }

    @Test
    public final void testWriteFile_Minimum() throws Exception {
        final CultureTemplate culture;
        final CultureTemplate cultureOut;
        final Parser<Reader, CultureTemplate> parser;
        final TestServiceFactory factory;
        final ModelService modelService;
        final Repository<AdditionalBelongingsTable> belongingsRepository;
        final Repository<FamilyCharacteristicTemplate> characteristicRepository;
        final Path pathOut;
        final Outputter<Object> outputter;

        outputter = new YAMLOutputter();

        factory = TestServiceFactory.getInstance();

        modelService = factory.getModelService();

        belongingsRepository = factory.getAdditionalBelongingsTableRepository();
        characteristicRepository = factory
                .getFamilyCharacteristicTemplateRepository();

        parser = new CultureYAMLParser(modelService, characteristicRepository,
                belongingsRepository);

        culture = parser.parse(ResourceUtils
                .getClassPathReader(TestModelFileConf.CULTURE_MINIMUM));

        pathOut = Paths.get(TEMPLATE_PATH + getRandomID() + ".yml")
                .toAbsolutePath();

        outputter.send(parserMap.parse(culture), new BufferedWriter(
                new FileWriter(pathOut.toFile())));

        cultureOut = parser.parse(new BufferedReader(new FileReader(pathOut
                .toFile())));

        Assert.assertEquals(cultureOut.getName(), culture.getName());

        Assert.assertEquals(culture.getMaleFamilyCharacteristic().getName(),
                cultureOut.getMaleFamilyCharacteristic().getName());
        Assert.assertEquals(culture.getFemaleFamilyCharacteristic().getName(),
                cultureOut.getFemaleFamilyCharacteristic().getName());

        Assert.assertEquals(culture.getMaleInitialLuckTable().getName(),
                cultureOut.getMaleInitialLuckTable().getName());
        Assert.assertEquals(culture.getFemaleInitialLuckTable().getName(),
                cultureOut.getFemaleInitialLuckTable().getName());

        assertEqual(cultureOut.getMaleTemplate(), culture.getMaleTemplate());
        assertEqual(cultureOut.getFemaleTemplate(), culture.getFemaleTemplate());
    }

    @Test
    public final void testWriteFile() throws Exception {
        final CultureTemplate culture;
        final CultureTemplate cultureOut;
        final Parser<Reader, CultureTemplate> parser;
        final TestServiceFactory factory;
        final ModelService modelService;
        final Repository<AdditionalBelongingsTable> belongingsRepository;
        final Repository<FamilyCharacteristicTemplate> characteristicRepository;
        final Path pathOut;
        final Outputter<Object> outputter;

        outputter = new YAMLOutputter();

        factory = TestServiceFactory.getInstance();

        modelService = factory.getModelService();

        belongingsRepository = factory.getAdditionalBelongingsTableRepository();
        characteristicRepository = factory
                .getFamilyCharacteristicTemplateRepository();

        parser = new CultureYAMLParser(modelService, characteristicRepository,
                belongingsRepository);

        culture = parser.parse(ResourceUtils
                .getClassPathReader(TestModelFileConf.CULTURE));

        pathOut = Paths.get(TEMPLATE_PATH + getRandomID() + ".yml")
                .toAbsolutePath();

        outputter.send(parserMap.parse(culture), new BufferedWriter(
                new FileWriter(pathOut.toFile())));

        cultureOut = parser.parse(new BufferedReader(new FileReader(pathOut
                .toFile())));

        Assert.assertEquals(cultureOut.getName(), culture.getName());

        Assert.assertEquals(culture.getMaleFamilyCharacteristic().getName(),
                cultureOut.getMaleFamilyCharacteristic().getName());
        Assert.assertEquals(culture.getFemaleFamilyCharacteristic().getName(),
                cultureOut.getFemaleFamilyCharacteristic().getName());

        Assert.assertEquals(culture.getMaleInitialLuckTable().getName(),
                cultureOut.getMaleInitialLuckTable().getName());
        Assert.assertEquals(culture.getFemaleInitialLuckTable().getName(),
                cultureOut.getFemaleInitialLuckTable().getName());

        assertEqual(cultureOut.getMaleTemplate(), culture.getMaleTemplate());
        assertEqual(cultureOut.getFemaleTemplate(), culture.getFemaleTemplate());
    }

    private final void assertEqual(final CultureCharacterTemplate actual,
            final CultureCharacterTemplate expected) {
        Assert.assertEquals(actual.getAttributes(), expected.getAttributes());
        Assert.assertEquals(actual.getAttributesRandom(),
                expected.getAttributesRandom());
        Assert.assertEquals(actual.getDirectedTraits(),
                expected.getDirectedTraits());
        Assert.assertEquals(actual.getPassions(), expected.getPassions());
        Assert.assertEquals(actual.getSkills(), expected.getSkills());
        Assert.assertEquals(actual.getSpecialtySkills(),
                expected.getSpecialtySkills());
        Assert.assertEquals(actual.getTraits(), expected.getTraits());
    }

    private final Integer getRandomID() {
        return random.nextInt(Integer.MAX_VALUE);
    }

}
