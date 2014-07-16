package com.wandrell.tabletop.pendragon.framework.service.command;

import java.nio.file.Paths;
import java.util.Collection;

import com.wandrell.framework.command.ReturnCommand;
import com.wandrell.tabletop.pendragon.framework.conf.ModelFile;
import com.wandrell.tabletop.pendragon.framework.model.xml.AttributeNamesXMLDocumentReader;
import com.wandrell.tabletop.pendragon.framework.model.xml.AttributeNamesXMLDocumentWriter;
import com.wandrell.util.PathUtils;
import com.wandrell.util.file.api.FileHandler;
import com.wandrell.util.file.impl.xml.DefaultXMLFileHandler;
import com.wandrell.util.file.impl.xml.XSDValidator;

public final class PendragonAttributeNamesCommand implements
	ReturnCommand<Collection<String>> {

    public PendragonAttributeNamesCommand() {
	super();
    }

    @Override
    public final Collection<String> execute() {
	final FileHandler<Collection<String>> file;

	file = new DefaultXMLFileHandler<Collection<String>>(
		new AttributeNamesXMLDocumentWriter(),
		new AttributeNamesXMLDocumentReader(), new XSDValidator(
			PathUtils.getClassPathResource(Paths
				.get(ModelFile.VALIDATION_ATTRIBUTE))));

	return file.read(PathUtils.getClassPathResource(Paths
		.get(ModelFile.ATTRIBUTE)));
    }

}
