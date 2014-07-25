package com.wandrell.tabletop.pendragon.framework.service.command;

import java.nio.file.Paths;
import java.util.Collection;

import com.wandrell.framework.command.ReturnCommand;
import com.wandrell.tabletop.pendragon.framework.conf.FileToken;
import com.wandrell.tabletop.pendragon.framework.conf.ModelFile;
import com.wandrell.tabletop.pendragon.framework.model.xml.PassionXMLDocumentReader;
import com.wandrell.tabletop.pendragon.valuehandler.PendragonPassion;
import com.wandrell.util.PathUtils;
import com.wandrell.util.file.api.FileHandler;
import com.wandrell.util.file.impl.xml.DefaultXMLFileHandler;
import com.wandrell.util.file.impl.xml.DisabledXMLWriter;
import com.wandrell.util.file.impl.xml.XSDValidator;

public final class PendragonInitialPassionsCommand implements
	ReturnCommand<Collection<PendragonPassion>> {

    public PendragonInitialPassionsCommand() {
	super();
    }

    @Override
    public final Collection<PendragonPassion> execute() {
	final FileHandler<Collection<PendragonPassion>> file;
	final PassionXMLDocumentReader reader;

	reader = new PassionXMLDocumentReader();
	reader.addRejectedAttribute(FileToken.REPEAT);
	reader.addRejectedAttribute(FileToken.RARE);

	file = new DefaultXMLFileHandler<>(
		new DisabledXMLWriter<Collection<PendragonPassion>>(), reader,
		new XSDValidator(PathUtils.getClassPathResource(Paths
			.get(ModelFile.VALIDATION_PASSION))));

	return file.read(PathUtils.getClassPathResource(Paths
		.get(ModelFile.PASSION)));
    }

}
