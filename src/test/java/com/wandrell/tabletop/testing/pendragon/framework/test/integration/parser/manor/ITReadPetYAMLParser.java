package com.wandrell.tabletop.testing.pendragon.framework.test.integration.parser.manor;

import java.io.Reader;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.model.manor.AnimalYearResult;
import com.wandrell.tabletop.pendragon.model.manor.Pet;
import com.wandrell.tabletop.pendragon.service.ModelService;
import com.wandrell.tabletop.pendragon.util.parser.manor.PetYAMLParser;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;

public final class ITReadPetYAMLParser {

    private Pet pet;

    public ITReadPetYAMLParser() {
        super();
    }

    @BeforeClass
    public final void initialize() throws Exception {
        final Parser<Reader, Pet> parserDoc;
        final ModelService modelService;

        modelService = TestServiceFactory.getInstance().getModelService();

        parserDoc = new PetYAMLParser(modelService);

        pet = parserDoc.parse(ResourceUtils
                .getClassPathReader(TestModelFileConf.PET));
    }

    @Test
    public final void testName() {
        Assert.assertEquals(pet.getName(), "test_pet");
    }

    @Test
    public final void testYearResults() {
        AnimalYearResult result;

        Assert.assertEquals(pet.getAnnualCheckMap().getIntervals().size(), 4);

        result = pet.getAnnualCheckMap().getValue(1);
        Assert.assertEquals(pet.getAnnualCheckMap().getValue(5), result);
        Assert.assertEquals(result.getDescription(), "description_1");
        Assert.assertEquals(result.getPuppy(), "");
        Assert.assertEquals(result.getMoney().getDenarii(), (Integer) 0);
        Assert.assertEquals(result.getMoney().getLibra(), (Integer) 0);
        Assert.assertTrue(!result.isDying());

        result = pet.getAnnualCheckMap().getValue(6);
        Assert.assertEquals(pet.getAnnualCheckMap().getValue(10), result);
        Assert.assertEquals(result.getDescription(), "description_2");
        Assert.assertEquals(result.getPuppy(), "puppy_1");
        Assert.assertEquals(result.getMoney().getDenarii(), (Integer) 0);
        Assert.assertEquals(result.getMoney().getLibra(), (Integer) 0);
        Assert.assertTrue(!result.isDying());

        result = pet.getAnnualCheckMap().getValue(11);
        Assert.assertEquals(pet.getAnnualCheckMap().getValue(12), result);
        Assert.assertEquals(result.getDescription(), "description_3");
        Assert.assertEquals(result.getPuppy(), "");
        Assert.assertEquals(result.getMoney().getDenarii(), (Integer) 0);
        Assert.assertEquals(result.getMoney().getLibra(), (Integer) 0);
        Assert.assertTrue(result.isDying());

        result = pet.getAnnualCheckMap().getValue(13);
        Assert.assertEquals(pet.getAnnualCheckMap().getValue(20), result);
        Assert.assertEquals(result.getDescription(), "description_4");
        Assert.assertEquals(result.getPuppy(), "");
        Assert.assertEquals(result.getMoney().getLibra(), (Integer) 11);
        Assert.assertEquals(result.getMoney().getDenarii(), (Integer) 22);
        Assert.assertTrue(!result.isDying());
    }

}
