package com.wandrell.tabletop.testing.pendragon.test.integration.parser.stats;

import java.io.Reader;
import java.util.Collection;
import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.util.parser.yaml.stats.StatsYAMLParser;
import com.wandrell.tabletop.pendragon.util.parser.yaml.stats.StatsYAMLParser.StatBuilder;
import com.wandrell.tabletop.stats.valuebox.DefaultSkillBox;
import com.wandrell.tabletop.stats.valuebox.SkillBox;
import com.wandrell.tabletop.testing.pendragon.framework.conf.TestModelFileConf;
import com.wandrell.util.ResourceUtils;

public final class ITParseStatsYAMLParser {

    private Collection<SkillBox> stats;

    public ITParseStatsYAMLParser() {
        super();
    }

    @BeforeClass
    public final void initialize() throws Exception {
        final Parser<Reader, Collection<SkillBox>> parser;

        parser = new StatsYAMLParser<SkillBox>(new StatBuilder<SkillBox>() {

            @Override
            public final SkillBox getStat(final String name,
                    final String descriptor, final Integer value) {
                return new DefaultSkillBox(name, descriptor, value);
            }

        });

        stats = parser.parse(ResourceUtils
                .getClassPathReader(TestModelFileConf.STATS));
    }

    @Test
    public final void testStats() {
        final Iterator<SkillBox> itr;
        SkillBox stat;

        Assert.assertEquals(stats.size(), 2);

        itr = stats.iterator();

        stat = itr.next();
        Assert.assertEquals(stat.getName(), "stat_1");
        Assert.assertEquals(stat.getDescriptor(), "descriptor_1");
        Assert.assertEquals(stat.getValue(), (Integer) 0);

        stat = itr.next();
        Assert.assertEquals(stat.getName(), "stat_2");
        Assert.assertEquals(stat.getDescriptor(), "");
        Assert.assertEquals(stat.getValue(), (Integer) 1);
    }

}
