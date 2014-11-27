package com.wandrell.tabletop.data.service.pendragon.model.command;

import java.util.Collection;

import com.wandrell.tabletop.business.conf.pendragon.FileToken;
import com.wandrell.tabletop.business.conf.pendragon.ModelFile;
import com.wandrell.tabletop.business.model.pendragon.valuehandler.Skill;
import com.wandrell.tabletop.business.util.parser.xml.pendragon.input.SkillDocumentInputProcessor;
import com.wandrell.util.ResourceUtils;
import com.wandrell.util.command.ReturnCommand;
import com.wandrell.util.parser.xml.input.AbstractFilteredXMLInputParser;
import com.wandrell.util.parser.xml.input.FilteredJDOMSAXInputParser;

public final class PendragonCommonNonCombatSkillsCommand implements
        ReturnCommand<Collection<Skill>> {

    public PendragonCommonNonCombatSkillsCommand() {
        super();
    }

    @Override
    public final Collection<Skill> execute() throws Exception {
        final AbstractFilteredXMLInputParser<Collection<Skill>> file;

        file = new FilteredJDOMSAXInputParser<Collection<Skill>>(
                FileToken.SKILL, new SkillDocumentInputProcessor());
        file.addRejectedAttribute(FileToken.ITALIAN);
        file.addRejectedAttribute(FileToken.REPEAT);
        file.addRejectedAttribute(FileToken.COMBAT);

        return file
                .read(ResourceUtils.getClassPathInputStream(ModelFile.SKILL));
    }

}
