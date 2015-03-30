package com.wandrell.tabletop.testing.pendragon.test.integration.outputter.stats;

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
import com.wandrell.tabletop.pendragon.model.character.stats.SpecialtySkillBox;
import com.wandrell.tabletop.pendragon.service.model.StatConstructorService;
import com.wandrell.tabletop.pendragon.util.parser.dictionary.stats.SpecialtySkillMapParser;
import com.wandrell.tabletop.pendragon.util.parser.yaml.stats.SpecialtySkillYAMLParser;
import com.wandrell.tabletop.testing.pendragon.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;

public final class ITSendSpecialtySkillYAMLOutput {

    private static final Random                                  random        = new Random();
    private static final String                                  TEMPLATE_PATH = "target/test_write_SpecialtySkill_";
    private final Parser<SpecialtySkillBox, Map<String, Object>> parserMap;

    {
        parserMap = new SpecialtySkillMapParser();
    }

    public ITSendSpecialtySkillYAMLOutput() {
        super();
    }

    @Test
    public final void testWriteFile() throws Exception {
        final SpecialtySkillBox skill;
        final SpecialtySkillBox skillOut;
        final Parser<Reader, SpecialtySkillBox> parser;
        final StatConstructorService statService;
        final Path pathOut;
        final Outputter<Object> outputter;

        outputter = new YAMLOutputter();

        statService = TestServiceFactory.getInstance()
                .getStatConstructorService();

        parser = new SpecialtySkillYAMLParser(statService);

        skill = parser.parse(ResourceUtils
                .getClassPathReader(TestModelFileConf.SPECIALTY_SKILL));

        pathOut = Paths.get(TEMPLATE_PATH + getRandomID() + ".yml")
                .toAbsolutePath();

        outputter.output(parserMap.parse(skill), new BufferedWriter(
                new FileWriter(pathOut.toFile())));

        skillOut = parser.parse(new BufferedReader(new FileReader(pathOut
                .toFile())));

        Assert.assertEquals(skillOut.getName(), skill.getName());
        Assert.assertEquals(skillOut.getSurrogatedSkills(),
                skill.getSurrogatedSkills());
    }

    @Test
    public final void testWriteFileMinimum() throws Exception {
        final SpecialtySkillBox skill;
        final SpecialtySkillBox skillOut;
        final Parser<Reader, SpecialtySkillBox> parser;
        final StatConstructorService statService;
        final Path pathOut;
        final Outputter<Object> outputter;

        outputter = new YAMLOutputter();

        statService = TestServiceFactory.getInstance()
                .getStatConstructorService();

        parser = new SpecialtySkillYAMLParser(statService);

        skill = parser.parse(ResourceUtils
                .getClassPathReader(TestModelFileConf.SPECIALTY_SKILL_MINIMUM));

        pathOut = Paths.get(TEMPLATE_PATH + getRandomID() + ".yml")
                .toAbsolutePath();

        outputter.output(parserMap.parse(skill), new BufferedWriter(
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
