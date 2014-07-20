package com.wandrell.tabletop.pendragon.framework.service.command;

import java.nio.file.Paths;
import java.util.Collection;

import com.wandrell.framework.command.ReturnCommand;
import com.wandrell.tabletop.pendragon.framework.conf.FileToken;
import com.wandrell.tabletop.pendragon.framework.conf.ModelFile;
import com.wandrell.tabletop.pendragon.framework.model.xml.SkillsXMLDocumentReader;
import com.wandrell.tabletop.pendragon.valuehandler.PendragonSkill;
import com.wandrell.util.PathUtils;
import com.wandrell.util.file.api.FileHandler;
import com.wandrell.util.file.impl.xml.DefaultXMLFileHandler;
import com.wandrell.util.file.impl.xml.DisabledXMLWriter;
import com.wandrell.util.file.impl.xml.XSDValidator;

public final class PendragonBaseSkillsCommand implements
	ReturnCommand<Collection<PendragonSkill>> {

    public PendragonBaseSkillsCommand() {
	super();
    }

    @Override
    public final Collection<PendragonSkill> execute() {
	final FileHandler<Collection<PendragonSkill>> file;
	final SkillsXMLDocumentReader reader;

	reader = new SkillsXMLDocumentReader();
	reader.addRejectedAttribute(FileToken.ITALIAN);
	reader.addRejectedAttribute(FileToken.REPEAT);

	file = new DefaultXMLFileHandler<Collection<PendragonSkill>>(
		new DisabledXMLWriter<Collection<PendragonSkill>>(), reader,
		new XSDValidator(PathUtils.getClassPathResource(Paths
			.get(ModelFile.VALIDATION_SKILL))));

	return file.read(PathUtils.getClassPathResource(Paths
		.get(ModelFile.SKILL)));
    }

}
