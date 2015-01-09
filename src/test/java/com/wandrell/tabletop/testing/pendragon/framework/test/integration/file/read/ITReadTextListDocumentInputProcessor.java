package com.wandrell.tabletop.testing.pendragon.framework.test.integration.file.read;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.tabletop.business.model.pendragon.util.TextList;
import com.wandrell.tabletop.business.service.pendragon.ModelService;
import com.wandrell.tabletop.business.util.parser.xml.pendragon.input.interval.TextListDocumentInputProcessor;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.FileConf;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;
import com.wandrell.util.parser.InputParser;
import com.wandrell.util.parser.xml.input.JDOMDocumentInputProcessor;
import com.wandrell.util.parser.xml.input.JDOMStAXInputParser;

public final class ITReadTextListDocumentInputProcessor {

    private TextList list;

    public ITReadTextListDocumentInputProcessor() {
        super();
    }

    @BeforeClass
    public final void initialize() throws Exception {
        final InputParser<TextList> parser;
        final JDOMDocumentInputProcessor<TextList> processor;
        final ModelService modelService;

        modelService = TestServiceFactory.getInstance().getModelService();

        processor = new TextListDocumentInputProcessor(modelService);
        parser = new JDOMStAXInputParser<TextList>(processor);

        list = parser
                .read(ResourceUtils.getClassPathInputStream(FileConf.LIST));
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
