package com.wandrell.tabletop.testing.pendragon.framework.test.integration.file.read.inventory;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.tabletop.business.model.pendragon.inventory.Shield;
import com.wandrell.tabletop.business.service.pendragon.ModelService;
import com.wandrell.tabletop.business.util.parser.xml.pendragon.input.inventory.ShieldDocumentDecoder;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;
import com.wandrell.util.parser.InputParser;
import com.wandrell.util.parser.xml.input.JDOMDocumentDecoder;
import com.wandrell.util.parser.xml.input.StAXInputParser;

public final class ITReadShieldDocumentDecoder {

    private Shield shield;

    public ITReadShieldDocumentDecoder() {
        super();
    }

    @BeforeClass
    public final void initialize() throws Exception {
        final InputParser<Shield> parser;
        final JDOMDocumentDecoder<Shield> processor;
        final ModelService modelService;

        modelService = TestServiceFactory.getInstance().getModelService();

        processor = new ShieldDocumentDecoder(modelService);
        parser = new StAXInputParser<Shield>(processor);

        shield = parser.read(ResourceUtils
                .getClassPathInputStream(TestModelFileConf.SHIELD));
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
