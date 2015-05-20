package com.wandrell.tabletop.testing.pendragon.test.integration.parser.stats;

import java.io.Reader;
import java.util.Collection;

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

public final class ITParseMinimumStatsYAMLParser {

    private Collection<SkillBox> stats;

    public ITParseMinimumStatsYAMLParser() {
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
                .getClassPathReader(TestModelFileConf.STATS_MINIMUM));
    }

    @Test
    public final void testStats() {
        Assert.assertTrue(stats.isEmpty());
    }

}
