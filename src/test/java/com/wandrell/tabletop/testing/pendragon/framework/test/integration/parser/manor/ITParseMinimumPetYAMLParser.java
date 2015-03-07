package com.wandrell.tabletop.testing.pendragon.framework.test.integration.parser.manor;

import java.io.Reader;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.model.manor.Pet;
import com.wandrell.tabletop.pendragon.service.model.ModelConstructorService;
import com.wandrell.tabletop.pendragon.util.parser.yaml.manor.PetYAMLParser;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;

public final class ITParseMinimumPetYAMLParser {

    private Pet pet;

    public ITParseMinimumPetYAMLParser() {
        super();
    }

    @BeforeClass
    public final void initialize() throws Exception {
        final Parser<Reader, Pet> parser;
        final ModelConstructorService modelService;

        modelService = TestServiceFactory.getInstance()
                .getModelConstructorService();

        parser = new PetYAMLParser(modelService);

        pet = parser.parse(ResourceUtils
                .getClassPathReader(TestModelFileConf.PET_MINIMUM));
    }

    @Test
    public final void testName() {
        Assert.assertEquals(pet.getName(), "test_pet");
    }

    @Test
    public final void testYearResults() {
        Assert.assertTrue(pet.getAnnualCheckMap().getIntervals().isEmpty());
    }

}
