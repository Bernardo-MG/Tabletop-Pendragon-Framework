package com.wandrell.tabletop.testing.pendragon.framework.test.integration.outputter.manor;

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
import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.interval.Interval;
import com.wandrell.tabletop.pendragon.model.manor.AnimalYearResult;
import com.wandrell.tabletop.pendragon.model.manor.Pet;
import com.wandrell.tabletop.pendragon.service.ModelService;
import com.wandrell.tabletop.pendragon.util.outputter.manor.PetYAMLOutputter;
import com.wandrell.tabletop.pendragon.util.parser.yaml.manor.PetYAMLParser;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;

public final class ITSendPetYAMLOutputter {

    private static final Random  random        = new Random();
    private static final String  TEMPLATE_PATH = "target/test_write_Pet_";
    private final Outputter<Pet> outputter;

    {
        outputter = new PetYAMLOutputter();
    }

    public ITSendPetYAMLOutputter() {
        super();
    }

    @Test
    public final void testWriteFile() throws Exception {
        final Pet pet;
        final Pet petOut;
        final Parser<Reader, Pet> parser;
        final ModelService modelService;
        final Path pathOut;

        modelService = TestServiceFactory.getInstance().getModelService();

        parser = new PetYAMLParser(modelService);

        pet = parser.parse(ResourceUtils
                .getClassPathReader(TestModelFileConf.PET));

        pathOut = Paths.get(TEMPLATE_PATH + getRandomID() + ".yml")
                .toAbsolutePath();

        outputter.send(pet,
                new BufferedWriter(new FileWriter(pathOut.toFile())));

        petOut = parser.parse(new BufferedReader(new FileReader(pathOut
                .toFile())));

        Assert.assertEquals(petOut.getName(), pet.getName());

        assertEquals(petOut.getAnnualCheckMap().getIntervals(), pet
                .getAnnualCheckMap().getIntervals());
    }

    private final void assertEquals(
            final Map<Interval, AnimalYearResult> actual,
            final Map<Interval, AnimalYearResult> expected) {
        final Iterator<Entry<Interval, AnimalYearResult>> itrActual;
        final Iterator<Entry<Interval, AnimalYearResult>> itrExpected;
        Entry<Interval, AnimalYearResult> entryActual;
        Entry<Interval, AnimalYearResult> entryExpected;

        itrActual = actual.entrySet().iterator();
        itrExpected = expected.entrySet().iterator();

        while (itrActual.hasNext()) {
            entryActual = itrActual.next();
            entryExpected = itrExpected.next();

            Assert.assertEquals(entryActual.getKey(), entryExpected.getKey());

            Assert.assertEquals(entryActual.getValue().getDescription(),
                    entryExpected.getValue().getDescription());
            Assert.assertEquals(entryActual.getValue().getPuppy(),
                    entryExpected.getValue().getPuppy());
            Assert.assertEquals(entryActual.getValue().getMoney().getLibra(),
                    entryExpected.getValue().getMoney().getLibra());
            Assert.assertEquals(entryActual.getValue().getMoney().getDenarii(),
                    entryExpected.getValue().getMoney().getDenarii());
        }
    }

    private final Integer getRandomID() {
        return random.nextInt(Integer.MAX_VALUE);
    }

}
