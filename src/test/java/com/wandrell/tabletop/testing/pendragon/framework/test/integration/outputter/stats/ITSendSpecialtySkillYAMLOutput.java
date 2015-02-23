package com.wandrell.tabletop.testing.pendragon.framework.test.integration.outputter.stats;

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
import com.wandrell.tabletop.pendragon.model.stats.SpecialtySkill;
import com.wandrell.tabletop.pendragon.service.ModelService;
import com.wandrell.tabletop.pendragon.util.parser.dictionary.stats.SpecialtySkillMapParser;
import com.wandrell.tabletop.pendragon.util.parser.yaml.stats.SpecialtySkillYAMLParser;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;

public final class ITSendSpecialtySkillYAMLOutput {

    private static final Random                               random        = new Random();
    private static final String                               TEMPLATE_PATH = "target/test_write_SpecialtySkill_";
    private final Parser<SpecialtySkill, Map<String, Object>> parserMap;

    {
        parserMap = new SpecialtySkillMapParser();
    }

    public ITSendSpecialtySkillYAMLOutput() {
        super();
    }

    @Test
    public final void testWriteFile() throws Exception {
        final SpecialtySkill skill;
        final SpecialtySkill skillOut;
        final Parser<Reader, SpecialtySkill> parser;
        final ModelService modelService;
        final Path pathOut;
        final Outputter<Object> outputter;

        outputter = new YAMLOutputter();

        modelService = TestServiceFactory.getInstance().getModelService();

        parser = new SpecialtySkillYAMLParser(modelService);

        skill = parser.parse(ResourceUtils
                .getClassPathReader(TestModelFileConf.SPECIALTY_SKILL));

        pathOut = Paths.get(TEMPLATE_PATH + getRandomID() + ".yml")
                .toAbsolutePath();

        outputter.send(parserMap.parse(skill), new BufferedWriter(
                new FileWriter(pathOut.toFile())));

        skillOut = parser.parse(new BufferedReader(new FileReader(pathOut
                .toFile())));

        Assert.assertEquals(skillOut.getName(), skill.getName());
        Assert.assertEquals(skillOut.getSurrogatedSkills(),
                skill.getSurrogatedSkills());
    }

    private final Integer getRandomID() {
        return random.nextInt(Integer.MAX_VALUE);
    }

}
