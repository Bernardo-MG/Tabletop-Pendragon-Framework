package com.wandrell.tabletop.testing.pendragon.test.integration.outputter.chargen;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.pattern.outputter.Outputter;
import com.wandrell.pattern.outputter.yaml.YAMLOutputter;
import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.model.chargen.FatherClassTemplate;
import com.wandrell.tabletop.pendragon.service.model.ModelConstructorService;
import com.wandrell.tabletop.pendragon.util.parser.dictionary.chargen.FatherClassTemplateMapParser;
import com.wandrell.tabletop.pendragon.util.parser.yaml.chargen.FatherClassTemplateYAMLParser;
import com.wandrell.tabletop.testing.pendragon.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;

public final class ITSendFatherClassTemplateYAMLOutputter {

    private static final Random                                    random        = new Random();
    private static final String                                    TEMPLATE_PATH = "target/test_write_FatherClassTemplate_";
    private final Parser<FatherClassTemplate, Map<String, Object>> parserMap;

    {
        parserMap = new FatherClassTemplateMapParser();
    }

    public ITSendFatherClassTemplateYAMLOutputter() {
        super();
    }

    @Test
    public final void testWriteFile() throws Exception {
        final FatherClassTemplate father;
        final FatherClassTemplate fatherOut;
        final Parser<Reader, FatherClassTemplate> parser;
        final ModelConstructorService modelService;
        final Path pathOut;
        final Outputter<Object> outputter;

        outputter = new YAMLOutputter();

        modelService = TestServiceFactory.getInstance()
                .getModelConstructorService();

        parser = new FatherClassTemplateYAMLParser(modelService);

        father = parser.parse(ResourceUtils
                .getClassPathReader(TestModelFileConf.FATHER_CLASS));

        pathOut = Paths.get(TEMPLATE_PATH + getRandomID() + ".yml")
                .toAbsolutePath();

        outputter.send(parserMap.parse(father), new BufferedWriter(
                new FileWriter(pathOut.toFile())));

        fatherOut = parser.parse(new BufferedReader(new FileReader(pathOut
                .toFile())));

        Assert.assertEquals(father.getName(), fatherOut.getName());

        Assert.assertEquals(father.getSkillsPoints(),
                fatherOut.getSkillsPoints());
        Assert.assertEquals(father.getNonCombatSkillBonus(),
                fatherOut.getNonCombatSkillBonus());
        Assert.assertEquals(father.getSkillsGroupBonusPoints(),
                fatherOut.getSkillsGroupBonusPoints());
        Assert.assertEquals(father.getSkillsGroupDividePoints(),
                fatherOut.getSkillsGroupDividePoints());

        Assert.assertEquals(father.getMoney().getTextValue(), fatherOut
                .getMoney().getTextValue());

        Assert.assertEquals(father.getSkillsGroup(), fatherOut.getSkillsGroup());
        Assert.assertEquals(father.getDirectedTraits(),
                fatherOut.getDirectedTraits());
        Assert.assertEquals(father.getDirectedTraitsBase(),
                fatherOut.getDirectedTraitsBase());
        Assert.assertEquals(father.getSpecialtySkills(),
                fatherOut.getSpecialtySkills());
    }

    @Test
    public final void testWriteFile_Minimum() throws Exception {
        final FatherClassTemplate father;
        final FatherClassTemplate fatherOut;
        final Parser<Reader, FatherClassTemplate> parser;
        final ModelConstructorService modelService;
        final Path pathOut;
        final Outputter<Object> outputter;

        outputter = new YAMLOutputter();

        modelService = TestServiceFactory.getInstance()
                .getModelConstructorService();

        parser = new FatherClassTemplateYAMLParser(modelService);

        father = parser.parse(ResourceUtils
                .getClassPathReader(TestModelFileConf.FATHER_CLASS_MINIMUM));

        pathOut = Paths.get(TEMPLATE_PATH + getRandomID() + ".yml")
                .toAbsolutePath();

        outputter.send(parserMap.parse(father), new BufferedWriter(
                new FileWriter(pathOut.toFile())));

        fatherOut = parser.parse(new BufferedReader(new FileReader(pathOut
                .toFile())));

        Assert.assertEquals(father.getName(), fatherOut.getName());

        Assert.assertEquals(father.getSkillsPoints(),
                fatherOut.getSkillsPoints());
        Assert.assertEquals(father.getNonCombatSkillBonus(),
                fatherOut.getNonCombatSkillBonus());
        Assert.assertEquals(father.getSkillsGroupBonusPoints(),
                fatherOut.getSkillsGroupBonusPoints());
        Assert.assertEquals(father.getSkillsGroupDividePoints(),
                fatherOut.getSkillsGroupDividePoints());

        Assert.assertEquals(father.getMoney().getTextValue(), fatherOut
                .getMoney().getTextValue());

        Assert.assertEquals(father.getSkillsGroup(), fatherOut.getSkillsGroup());
        Assert.assertEquals(father.getDirectedTraits(),
                fatherOut.getDirectedTraits());
        Assert.assertEquals(father.getDirectedTraitsBase(),
                fatherOut.getDirectedTraitsBase());
        Assert.assertEquals(father.getSpecialtySkills(),
                fatherOut.getSpecialtySkills());
    }

    private final Integer getRandomID() {
        return random.nextInt(Integer.MAX_VALUE);
    }

}
