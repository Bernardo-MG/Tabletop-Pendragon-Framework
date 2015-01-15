package com.wandrell.tabletop.testing.pendragon.framework.test.integration.file.read;

import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.tabletop.business.model.pendragon.stats.Passion;
import com.wandrell.tabletop.business.service.pendragon.ModelService;
import com.wandrell.tabletop.business.util.parser.xml.pendragon.input.stats.PassionDocumentInputProcessor;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;
import com.wandrell.util.parser.InputParser;
import com.wandrell.util.parser.xml.XMLValidationType;
import com.wandrell.util.parser.xml.input.JDOMDocumentInputProcessor;
import com.wandrell.util.parser.xml.input.JDOMSAXInputParser;

public final class ITReadPassionDocumentInputProcessor {

    private Collection<Passion> passions;

    public ITReadPassionDocumentInputProcessor() {
        super();
    }

    @BeforeClass
    public final void initialize() throws Exception {
        final InputParser<Collection<Passion>> parser;
        final JDOMDocumentInputProcessor<Collection<Passion>> processor;
        final ModelService modelService;
        final Collection<InputStream> validationStreams;

        modelService = TestServiceFactory.getInstance().getModelService();

        validationStreams = new LinkedList<>();
        validationStreams
                .add(ResourceUtils
                        .getClassPathInputStream(TestModelFileConf.PASSIONS_VALIDATION));

        processor = new PassionDocumentInputProcessor(modelService);
        parser = new JDOMSAXInputParser<Collection<Passion>>(
                XMLValidationType.XSD, validationStreams, processor);

        passions = parser.read(ResourceUtils
                .getClassPathInputStream(TestModelFileConf.PASSIONS));
    }

    @Test
    public final void testSkills() {
        final Iterator<Passion> itr;
        Passion passion;

        Assert.assertEquals(passions.size(), 2);

        itr = passions.iterator();

        passion = itr.next();
        Assert.assertEquals(passion.getName(), "passion_1");
        Assert.assertEquals(passion.getDescriptor(), "descriptor_1");
        Assert.assertEquals(passion.isDescribed(), (Boolean) true);

        passion = itr.next();
        Assert.assertEquals(passion.getName(), "passion_2");
        Assert.assertEquals(passion.getDescriptor(), null);
        Assert.assertEquals(passion.isDescribed(), (Boolean) false);
    }

}
