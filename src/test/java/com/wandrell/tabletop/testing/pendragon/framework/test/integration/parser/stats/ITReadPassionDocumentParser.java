package com.wandrell.tabletop.testing.pendragon.framework.test.integration.parser.stats;

import java.io.Reader;
import java.util.Collection;
import java.util.Iterator;

import org.jdom2.Document;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.pattern.conf.XMLValidationType;
import com.wandrell.pattern.parser.Parser;
import com.wandrell.pattern.parser.xml.ValidatedXMLFileParser;
import com.wandrell.tabletop.pendragon.util.parser.stats.PassionDocumentParser;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.valuebox.SkillBox;
import com.wandrell.util.ResourceUtils;

public final class ITReadPassionDocumentParser {

    private Collection<SkillBox> passions;

    public ITReadPassionDocumentParser() {
        super();
    }

    @BeforeClass
    public final void initialize() throws Exception {
        final Parser<Reader, Document> parserFile;
        final Parser<Document, Collection<SkillBox>> parserDoc;

        parserDoc = new PassionDocumentParser();
        parserFile = new ValidatedXMLFileParser(
                XMLValidationType.XSD,
                ResourceUtils
                        .getClassPathInputStream(TestModelFileConf.PASSIONS_VALIDATION));

        passions = parserDoc.parse(parserFile.parse(ResourceUtils
                .getClassPathReader(TestModelFileConf.PASSIONS)));
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
