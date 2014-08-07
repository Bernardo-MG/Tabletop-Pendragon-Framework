package com.wandrell.tabletop.pendragon.framework.service.command;

import java.nio.file.Paths;
import java.util.Collection;

import com.wandrell.framework.command.ReturnCommand;
import com.wandrell.tabletop.pendragon.framework.conf.FileToken;
import com.wandrell.tabletop.pendragon.framework.conf.ModelFile;
import com.wandrell.tabletop.pendragon.framework.util.file.model.SkillNodesReader;
import com.wandrell.tabletop.pendragon.valuehandler.Skill;
import com.wandrell.util.PathUtils;
import com.wandrell.util.file.api.FileHandler;
import com.wandrell.util.file.api.xml.FilteredXMLDocumentReader;
import com.wandrell.util.file.impl.xml.DefaultFilteredXMLDocumentReader;
import com.wandrell.util.file.impl.xml.DefaultXMLFileHandler;
import com.wandrell.util.file.impl.xml.DisabledXMLWriter;
import com.wandrell.util.file.impl.xml.XSDValidator;

public final class PendragonCommonNonCombatSkillsCommand implements
        ReturnCommand<Collection<Skill>> {

    public PendragonCommonNonCombatSkillsCommand() {
        super();
    }

    @Override
    public final Collection<Skill> execute() {
        final FileHandler<Collection<Skill>> file;
        final FilteredXMLDocumentReader<Collection<Skill>> reader;

        reader = new DefaultFilteredXMLDocumentReader<Collection<Skill>>(
                FileToken.SKILL, new SkillNodesReader());
        reader.addRejectedAttribute(FileToken.ITALIAN);
        reader.addRejectedAttribute(FileToken.REPEAT);
        reader.addRejectedAttribute(FileToken.COMBAT);

        file = new DefaultXMLFileHandler<>(
                new DisabledXMLWriter<Collection<Skill>>(), reader,
                new XSDValidator(PathUtils.getClassPathResource(Paths
                        .get(ModelFile.VALIDATION_SKILL))));

        return file.read(PathUtils.getClassPathResource(Paths
                .get(ModelFile.SKILL)));
    }

}
