package com.wandrell.tabletop.testing.pendragon.framework.test.integration.file.read.manor;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.tabletop.business.model.pendragon.manor.AnimalYearResult;
import com.wandrell.tabletop.business.model.pendragon.manor.Pet;
import com.wandrell.tabletop.business.service.pendragon.ModelService;
import com.wandrell.tabletop.business.util.parser.xml.pendragon.input.manor.PetDocumentInputProcessor;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;
import com.wandrell.util.parser.InputParser;
import com.wandrell.util.parser.xml.input.JDOMDocumentInputProcessor;
import com.wandrell.util.parser.xml.input.JDOMStAXInputParser;

public final class ITReadPetDocumentInputProcessor {

    private Pet pet;

    public ITReadPetDocumentInputProcessor() {
        super();
    }

    @BeforeClass
    public final void initialize() throws Exception {
        final InputParser<Pet> parser;
        final JDOMDocumentInputProcessor<Pet> processor;
        final ModelService modelService;

        modelService = TestServiceFactory.getInstance().getModelService();

        processor = new PetDocumentInputProcessor(modelService);
        parser = new JDOMStAXInputParser<Pet>(processor);

        pet = parser.read(ResourceUtils
                .getClassPathInputStream(TestModelFileConf.PET));
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
