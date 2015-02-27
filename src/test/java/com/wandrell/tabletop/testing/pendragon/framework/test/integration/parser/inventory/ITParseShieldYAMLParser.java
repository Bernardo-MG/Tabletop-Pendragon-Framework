package com.wandrell.tabletop.testing.pendragon.framework.test.integration.parser.inventory;

import java.io.Reader;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.model.inventory.Shield;
import com.wandrell.tabletop.pendragon.service.ModelService;
import com.wandrell.tabletop.pendragon.util.parser.yaml.inventory.ShieldYAMLParser;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;

public final class ITParseShieldYAMLParser {

    private Shield shield;

    public ITParseShieldYAMLParser() {
        super();
    }

    @BeforeClass
    public final void initialize() throws Exception {
        final Parser<Reader, Shield> parser;
        final ModelService modelService;

        modelService = TestServiceFactory.getInstance().getModelService();

        parser = new ShieldYAMLParser(modelService);

        shield = parser.parse(ResourceUtils
                .getClassPathReader(TestModelFileConf.SHIELD));
    }

    @Test
    public final void testArmorValue() {
        Assert.assertEquals(shield.getArmorValue(), (Integer) 11);
    }

    @Test
    public final void testName() {
        Assert.assertEquals(shield.getName(), "test_shield");
    }

}
