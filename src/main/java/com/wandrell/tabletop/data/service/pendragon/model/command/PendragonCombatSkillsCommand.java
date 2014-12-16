package com.wandrell.tabletop.data.service.pendragon.model.command;

import java.util.Collection;

import com.wandrell.tabletop.business.conf.pendragon.FileToken;
import com.wandrell.tabletop.business.conf.pendragon.ModelFile;
import com.wandrell.tabletop.business.model.pendragon.stats.Skill;
import com.wandrell.tabletop.business.util.parser.xml.pendragon.input.SkillDocumentInputProcessor;
import com.wandrell.util.ResourceUtils;
import com.wandrell.util.command.ReturnCommand;
import com.wandrell.util.parser.xml.input.AbstractFilteredXMLInputParser;
import com.wandrell.util.parser.xml.input.FilteredJDOMSAXInputParser;

public final class PendragonCombatSkillsCommand implements
        ReturnCommand<Collection<Skill>> {

    public PendragonCombatSkillsCommand() {
        super();
    }

    @Override
    public final Collection<Skill> execute() throws Exception {
        final AbstractFilteredXMLInputParser<Collection<Skill>> file;

        file = new FilteredJDOMSAXInputParser<Collection<Skill>>(
                FileToken.SKILL, new SkillDocumentInputProcessor());
        file.addRequiredAttribute(FileToken.COMBAT);

        return file
                .read(ResourceUtils.getClassPathInputStream(ModelFile.SKILL));
    }

}
