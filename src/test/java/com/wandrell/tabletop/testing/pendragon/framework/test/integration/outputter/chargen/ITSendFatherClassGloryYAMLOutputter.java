package com.wandrell.tabletop.testing.pendragon.framework.test.integration.outputter.chargen;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.wandrell.pattern.outputter.Outputter;
import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.model.glory.FatherClassGlory;
import com.wandrell.tabletop.pendragon.service.ModelService;
import com.wandrell.tabletop.pendragon.util.outputter.chargen.FatherClassGloryYAMLOutputter;
import com.wandrell.tabletop.pendragon.util.parser.yaml.chargen.FatherClassGloryYAMLParser;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.TestModelFileConf;
import com.wandrell.tabletop.testing.pendragon.framework.framework.conf.factory.TestServiceFactory;
import com.wandrell.util.ResourceUtils;

public final class ITSendFatherClassGloryYAMLOutputter {

    private static final Random               random        = new Random();
    private static final String               TEMPLATE_PATH = "target/test_write_FatherClassGlory_";
    private final Outputter<FatherClassGlory> outputter;

    {
        outputter = new FatherClassGloryYAMLOutputter();
    }

    public ITSendFatherClassGloryYAMLOutputter() {
        super();
    }

    @Test
    public final void testWriteFile() throws Exception {
        final FatherClassGlory glory;
        final FatherClassGlory gloryOut;
        final Parser<Reader, FatherClassGlory> parser;
        final ModelService modelService;
        final Path pathOut;

        modelService = TestServiceFactory.getInstance().getModelService();

        parser = new FatherClassGloryYAMLParser(modelService);

        glory = parser.parse(ResourceUtils
                .getClassPathReader(TestModelFileConf.FATHER_CLASS_GLORY));

        pathOut = Paths.get(TEMPLATE_PATH + getRandomID() + ".yml")
                .toAbsolutePath();

        outputter.send(glory,
                new BufferedWriter(new FileWriter(pathOut.toFile())));

        gloryOut = parser.parse(new BufferedReader(new FileReader(pathOut
                .toFile())));

        Assert.assertEquals(glory.getFatherClass(), gloryOut.getFatherClass());
        Assert.assertEquals(glory.getBaseGlory(), gloryOut.getBaseGlory());
        Assert.assertEquals(glory.getYearlyGlory(), gloryOut.getYearlyGlory());
    }

    private final Integer getRandomID() {
        return random.nextInt(Integer.MAX_VALUE);
    }

}
