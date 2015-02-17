package com.wandrell.tabletop.testing.pendragon.framework.test.integration.parser.stats;

import java.io.Reader;
import java.util.Collection;
import java.util.Iterator;

import org.jdom2.Document;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.pattern.parser.xml.XMLFileParser;
import com.wandrell.tabletop.pendragon.util.parser.xml.input.stats.DirectedTraitDocumentParser;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.valuebox.SkillBox;
import com.wandrell.util.ResourceUtils;

public final class ITReadDirectedTraitDocumentParser {

    private Collection<SkillBox> traits;

    public ITReadDirectedTraitDocumentParser() {
        super();
    }

    @BeforeClass
    public final void initialize() throws Exception {
        final Parser<Reader, Document> parserFile;
        final Parser<Document, Collection<SkillBox>> parserDoc;

        parserDoc = new DirectedTraitDocumentParser();
        parserFile = new XMLFileParser();

        traits = parserDoc.parse(parserFile.parse(ResourceUtils
                .getClassPathReader(TestModelFileConf.DIRECTED_TRAITS)));
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
