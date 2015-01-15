package com.wandrell.tabletop.testing.pendragon.framework.test.integration.file.read.inventory;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.tabletop.business.model.pendragon.inventory.Shield;
import com.wandrell.tabletop.business.service.pendragon.ModelService;
import com.wandrell.tabletop.business.util.parser.xml.pendragon.input.inventory.ShieldDocumentInputProcessor;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;
import com.wandrell.util.parser.InputParser;
import com.wandrell.util.parser.xml.input.JDOMDocumentInputProcessor;
import com.wandrell.util.parser.xml.input.JDOMStAXInputParser;

public final class ITReadShieldDocumentInputProcessor {

    private Shield shield;

    public ITReadShieldDocumentInputProcessor() {
        super();
    }

    @BeforeClass
    public final void initialize() throws Exception {
        final InputParser<Shield> parser;
        final JDOMDocumentInputProcessor<Shield> processor;
        final ModelService modelService;

        modelService = TestServiceFactory.getInstance().getModelService();

        processor = new ShieldDocumentInputProcessor(modelService);
        parser = new JDOMStAXInputParser<Shield>(processor);

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
