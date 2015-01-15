package com.wandrell.tabletop.testing.pendragon.framework.test.integration.file.read.inventory;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.tabletop.business.model.pendragon.inventory.Armor;
import com.wandrell.tabletop.business.model.pendragon.inventory.ArmorType;
import com.wandrell.tabletop.business.service.pendragon.ModelService;
import com.wandrell.tabletop.business.util.parser.xml.pendragon.input.inventory.ArmorDocumentInputProcessor;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;
import com.wandrell.util.parser.InputParser;
import com.wandrell.util.parser.xml.input.JDOMDocumentInputProcessor;
import com.wandrell.util.parser.xml.input.JDOMStAXInputParser;

public final class ITReadArmorDocumentInputProcessor {

    private Armor armor;

    public ITReadArmorDocumentInputProcessor() {
        super();
    }

    @BeforeClass
    public final void initialize() throws Exception {
        final InputParser<Armor> parser;
        final JDOMDocumentInputProcessor<Armor> processor;
        final ModelService modelService;

        modelService = TestServiceFactory.getInstance().getModelService();

        processor = new ArmorDocumentInputProcessor(modelService);
        parser = new JDOMStAXInputParser<Armor>(processor);

        armor = parser.read(ResourceUtils
                .getClassPathInputStream(TestModelFileConf.ARMOR));
    }

    @Test
    public final void testArmorValue() {
        Assert.assertEquals(armor.getArmorValue(), (Integer) 11);
    }

    @Test
    public final void testDexterityModifier() {
        Assert.assertEquals(armor.getDexterityModifier(), (Integer) (0 - 22));
    }

    @Test
    public final void testHeavyLoad() {
        Assert.assertTrue(armor.isHeavyLoad());
    }

    @Test
    public final void testName() {
        Assert.assertEquals(armor.getName(), "test_armor");
    }

    @Test
    public final void testType() {
        Assert.assertEquals(armor.getArmorType(), ArmorType.LEATHER);
    }

}
