package com.wandrell.tabletop.testing.pendragon.framework.test.integration.file.read;

import java.util.Collection;
import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.tabletop.business.model.pendragon.stats.DirectedTrait;
import com.wandrell.tabletop.business.service.pendragon.ModelService;
import com.wandrell.tabletop.business.util.parser.xml.pendragon.input.stats.DirectedTraitDocumentInputProcessor;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;
import com.wandrell.util.parser.InputParser;
import com.wandrell.util.parser.xml.input.JDOMDocumentInputProcessor;
import com.wandrell.util.parser.xml.input.JDOMStAXInputParser;

public final class ITReadDirectedTraitDocumentInputProcessor {

    private Collection<DirectedTrait> traits;

    public ITReadDirectedTraitDocumentInputProcessor() {
        super();
    }

    @BeforeClass
    public final void initialize() throws Exception {
        final InputParser<Collection<DirectedTrait>> parser;
        final JDOMDocumentInputProcessor<Collection<DirectedTrait>> processor;
        final ModelService modelService;

        modelService = TestServiceFactory.getInstance().getModelService();

        processor = new DirectedTraitDocumentInputProcessor(modelService);
        parser = new JDOMStAXInputParser<Collection<DirectedTrait>>(processor);

        traits = parser.read(ResourceUtils
                .getClassPathInputStream(TestModelFileConf.DIRECTED_TRAITS));
    }

    @Test
    public final void testDirectedTraits() {
        final Iterator<DirectedTrait> itr;
        DirectedTrait trait;

        Assert.assertEquals(traits.size(), 2);

        itr = traits.iterator();

        trait = itr.next();
        Assert.assertEquals(trait.getName(), "directed_1");

        trait = itr.next();
        Assert.assertEquals(trait.getName(), "directed_2");
    }

}
