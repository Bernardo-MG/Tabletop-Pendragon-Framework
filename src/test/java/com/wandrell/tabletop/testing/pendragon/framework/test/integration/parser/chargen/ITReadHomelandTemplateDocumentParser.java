package com.wandrell.tabletop.testing.pendragon.framework.test.integration.parser.chargen;

import java.io.Reader;

import org.jdom2.Document;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.pattern.parser.xml.XMLFileParser;
import com.wandrell.tabletop.pendragon.model.chargen.HomelandTemplate;
import com.wandrell.tabletop.pendragon.service.ModelService;
import com.wandrell.tabletop.pendragon.util.parser.xml.input.chargen.HomelandTemplateDocumentParser;
import com.wandrell.tabletop.skill.DefaultNameAndDescriptor;
import com.wandrell.tabletop.skill.NameAndDescriptor;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;

public final class ITReadHomelandTemplateDocumentParser {

    private HomelandTemplate homeland;
    private ModelService     modelService;

    public ITReadHomelandTemplateDocumentParser() {
        super();
    }

    @BeforeClass
    public final void initialize() throws Exception {
        final Parser<Reader, Document> parserFile;
        final Parser<Document, HomelandTemplate> parserDoc;

        modelService = TestServiceFactory.getInstance().getModelService();

        parserDoc = new HomelandTemplateDocumentParser(modelService);
        parserFile = new XMLFileParser();

        homeland = parserDoc.parse(parserFile.parse(ResourceUtils
                .getClassPathReader(TestModelFileConf.HOMELAND)));
    }

    @Test
    public final void testDirectedTraits() {
        NameAndDescriptor skill;

        Assert.assertEquals(homeland.getDirectedTraits().size(), 1);

        skill = new DefaultNameAndDescriptor("directed_1",
                "descriptor_directed_1");
        Assert.assertTrue(homeland.getDirectedTraits().contains(skill));
    }

    @Test
    public final void testName() {
        Assert.assertEquals(homeland.getHomeland(), "test_homeland");
    }

    @Test
    public final void testPassions() {
        NameAndDescriptor skill;

        Assert.assertEquals(homeland.getPassions().size(), 1);

        skill = new DefaultNameAndDescriptor("passion_1",
                "descriptor_passion_1");
        Assert.assertTrue(homeland.getPassions().contains(skill));
    }

    @Test
    public final void testSkills() {
        NameAndDescriptor skill;

        Assert.assertEquals(homeland.getSkills().size(), 2);

        skill = new DefaultNameAndDescriptor("skill_1", "");
        Assert.assertTrue(homeland.getSkills().containsKey(skill));
        Assert.assertEquals(homeland.getSkills().get(skill), (Integer) 3);

        skill = new DefaultNameAndDescriptor("skill_2", "descriptor_skill_2");
        Assert.assertTrue(homeland.getSkills().containsKey(skill));
        Assert.assertEquals(homeland.getSkills().get(skill), (Integer) 4);

    }

    @Test
    public final void testSpecialtySkills() {
        Assert.assertEquals(homeland.getSpecialtySkills().size(), 1);

        Assert.assertTrue(homeland.getSpecialtySkills().containsKey(
                "specialty_skill_1"));

        Assert.assertEquals(
                homeland.getSpecialtySkills().get("specialty_skill_1"),
                (Integer) 5);
    }

}
