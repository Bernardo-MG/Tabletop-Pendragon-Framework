package com.wandrell.tabletop.testing.pendragon.framework.test.integration.parser.stats;

import java.io.Reader;
import java.util.Collection;
import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.util.parser.stats.DirectedTraitYAMLParser;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.valuebox.SkillBox;
import com.wandrell.util.ResourceUtils;

public final class ITParseDirectedTraitYAMLParser {

    private Collection<SkillBox> traits;

    public ITParseDirectedTraitYAMLParser() {
        super();
    }

    @BeforeClass
    public final void initialize() throws Exception {
        final Parser<Reader, Collection<SkillBox>> parser;

        parser = new DirectedTraitYAMLParser();

        traits = parser.parse(ResourceUtils
                .getClassPathReader(TestModelFileConf.DIRECTED_TRAITS));
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
