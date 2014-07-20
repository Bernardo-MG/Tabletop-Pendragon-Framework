package com.wandrell.tabletop.pendragon.framework.service.command;

import java.nio.file.Paths;
import java.util.Collection;

import com.wandrell.framework.command.ReturnCommand;
import com.wandrell.tabletop.pendragon.framework.conf.ModelFile;
import com.wandrell.tabletop.pendragon.framework.model.xml.TraitNameXMLDocumentReader;
import com.wandrell.util.PathUtils;
import com.wandrell.util.file.api.FileHandler;
import com.wandrell.util.file.impl.xml.DefaultXMLFileHandler;
import com.wandrell.util.file.impl.xml.DisabledXMLWriter;
import com.wandrell.util.file.impl.xml.XSDValidator;

public final class PendragonTraitNamesCommand implements
	ReturnCommand<Collection<String>> {

    public PendragonTraitNamesCommand() {
	super();
    }

    @Override
    public final Collection<String> execute() {
	final FileHandler<Collection<String>> file;

	file = new DefaultXMLFileHandler<Collection<String>>(
		new DisabledXMLWriter<Collection<String>>(),
		new TraitNameXMLDocumentReader(), new XSDValidator(
			PathUtils.getClassPathResource(Paths
				.get(ModelFile.VALIDATION_TRAIT))));

	return file.read(PathUtils.getClassPathResource(Paths
		.get(ModelFile.TRAIT)));
    }

}
