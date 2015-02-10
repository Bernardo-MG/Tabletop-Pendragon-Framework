package com.wandrell.tabletop.testing.pendragon.framework.test.integration.file.read.stats;

import java.util.Collection;
import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.pattern.parser.InputParser;
import com.wandrell.pattern.parser.xml.input.JDOMDocumentDecoder;
import com.wandrell.pattern.parser.xml.input.StAXInputParser;
import com.wandrell.tabletop.business.model.valuebox.SkillBox;
import com.wandrell.tabletop.business.util.parser.xml.pendragon.input.stats.DirectedTraitDocumentDecoder;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.TestModelFileConf;
import com.wandrell.util.ResourceUtils;

public final class ITReadDirectedTraitDocumentDecoder {

    private Collection<SkillBox> traits;

    public ITReadDirectedTraitDocumentDecoder() {
        super();
    }

    @BeforeClass
    public final void initialize() throws Exception {
        final InputParser<Collection<SkillBox>> parser;
        final JDOMDocumentDecoder<Collection<SkillBox>> processor;

        processor = new DirectedTraitDocumentDecoder();
        parser = new StAXInputParser<Collection<SkillBox>>(processor);

        traits = parser.read(ResourceUtils
                .getClassPathInputStream(TestModelFileConf.DIRECTED_TRAITS));
    }

    @Test
    public final void testDirectedTraits() {
        final Iterator<SkillBox> itr;
        SkillBox trait;

        Assert.assertEquals(traits.size(), 2);

        itr = traits.iterator();

        trait = itr.next();
        Assert.assertEquals(trait.getName(), "directed_1");

        trait = itr.next();
        Assert.assertEquals(trait.getName(), "directed_2");
    }

}
