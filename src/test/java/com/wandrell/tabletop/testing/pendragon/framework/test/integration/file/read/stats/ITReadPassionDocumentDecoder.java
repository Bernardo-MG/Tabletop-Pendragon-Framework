package com.wandrell.tabletop.testing.pendragon.framework.test.integration.file.read.stats;

import java.util.Collection;
import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.pattern.parser.InputParser;
import com.wandrell.pattern.parser.xml.XMLValidationType;
import com.wandrell.pattern.parser.xml.input.JDOMDocumentDecoder;
import com.wandrell.pattern.parser.xml.input.SAXInputParser;
import com.wandrell.tabletop.business.model.valuebox.SkillBox;
import com.wandrell.tabletop.business.util.parser.xml.pendragon.input.stats.PassionDocumentDecoder;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.TestModelFileConf;
import com.wandrell.util.ResourceUtils;

public final class ITReadPassionDocumentDecoder {

    private Collection<SkillBox> passions;

    public ITReadPassionDocumentDecoder() {
        super();
    }

    @BeforeClass
    public final void initialize() throws Exception {
        final InputParser<Collection<SkillBox>> parser;
        final JDOMDocumentDecoder<Collection<SkillBox>> processor;

        processor = new PassionDocumentDecoder();
        parser = new SAXInputParser<Collection<SkillBox>>(
                XMLValidationType.XSD,
                ResourceUtils
                        .getClassPathInputStream(TestModelFileConf.PASSIONS_VALIDATION),
                processor);

        passions = parser.read(ResourceUtils
                .getClassPathInputStream(TestModelFileConf.PASSIONS));
    }

    @Test
    public final void testSkills() {
        final Iterator<SkillBox> itr;
        SkillBox passion;

        Assert.assertEquals(passions.size(), 2);

        itr = passions.iterator();

        passion = itr.next();
        Assert.assertEquals(passion.getName(), "passion_1");
        Assert.assertEquals(passion.getDescriptor(), "descriptor_1");
        Assert.assertEquals(passion.isDescribed(), (Boolean) true);

        passion = itr.next();
        Assert.assertEquals(passion.getName(), "passion_2");
        Assert.assertEquals(passion.getDescriptor(), "");
        Assert.assertEquals(passion.isDescribed(), (Boolean) false);
    }

}
