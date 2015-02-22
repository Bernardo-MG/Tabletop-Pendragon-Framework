package com.wandrell.tabletop.testing.pendragon.framework.test.integration.parser.inventory;

import java.io.Reader;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.model.inventory.Armor;
import com.wandrell.tabletop.pendragon.model.inventory.ArmorType;
import com.wandrell.tabletop.pendragon.service.ModelService;
import com.wandrell.tabletop.pendragon.util.parser.inventory.ArmorYAMLParser;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;

public final class ITParseArmorYAMLParser {

    private Armor armor;

    public ITParseArmorYAMLParser() {
        super();
    }

    @BeforeClass
    public final void initialize() throws Exception {
        final Parser<Reader, Armor> parser;
        final ModelService modelService;

        modelService = TestServiceFactory.getInstance().getModelService();

        parser = new ArmorYAMLParser(modelService);

        armor = parser.parse(ResourceUtils
                .getClassPathReader(TestModelFileConf.ARMOR));
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
