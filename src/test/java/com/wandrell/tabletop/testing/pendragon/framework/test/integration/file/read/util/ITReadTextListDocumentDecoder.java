package com.wandrell.tabletop.testing.pendragon.framework.test.integration.file.read.util;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.pattern.parser.InputParser;
import com.wandrell.pattern.parser.xml.input.JDOMDocumentDecoder;
import com.wandrell.pattern.parser.xml.input.StAXInputParser;
import com.wandrell.tabletop.business.model.pendragon.util.TextList;
import com.wandrell.tabletop.business.service.pendragon.ModelService;
import com.wandrell.tabletop.business.util.parser.xml.pendragon.input.util.TextListDocumentDecoder;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;

public final class ITReadTextListDocumentDecoder {

    private TextList list;

    public ITReadTextListDocumentDecoder() {
        super();
    }

    @BeforeClass
    public final void initialize() throws Exception {
        final InputParser<TextList> parser;
        final JDOMDocumentDecoder<TextList> processor;
        final ModelService modelService;

        modelService = TestServiceFactory.getInstance().getModelService();

        processor = new TextListDocumentDecoder(modelService);
        parser = new StAXInputParser<TextList>(processor);

        list = parser.read(ResourceUtils
                .getClassPathInputStream(TestModelFileConf.LIST));
    }

    @Test
    public final void testName() {
        Assert.assertEquals(list.getName(), "test_list");
    }

    @Test
    public final void testValues() {
        Assert.assertEquals(list.getValues().size(), 3);

        Assert.assertTrue(list.getValues().contains("value_1"));
        Assert.assertTrue(list.getValues().contains("value_2"));
        Assert.assertTrue(list.getValues().contains("value_3"));
    }

}
