package com.wandrell.tabletop.pendragon.framework.service.command;

import java.nio.file.Paths;
import java.util.Collection;

import com.wandrell.framework.command.ReturnCommand;
import com.wandrell.tabletop.pendragon.framework.conf.ModelFile;
import com.wandrell.tabletop.pendragon.framework.util.file.model.DirectedTraitXMLDocumentReader;
import com.wandrell.tabletop.pendragon.valuehandler.PendragonDirectedTrait;
import com.wandrell.util.PathUtils;
import com.wandrell.util.file.api.FileHandler;
import com.wandrell.util.file.impl.xml.DefaultXMLFileHandler;
import com.wandrell.util.file.impl.xml.DisabledXMLWriter;
import com.wandrell.util.file.impl.xml.XSDValidator;

public final class PendragonDirectedTraitsCommand implements
	ReturnCommand<Collection<PendragonDirectedTrait>> {

    public PendragonDirectedTraitsCommand() {
	super();
    }

    @Override
    public final Collection<PendragonDirectedTrait> execute() {
	final FileHandler<Collection<PendragonDirectedTrait>> file;
	final DirectedTraitXMLDocumentReader reader;

	reader = new DirectedTraitXMLDocumentReader();

	file = new DefaultXMLFileHandler<>(
		new DisabledXMLWriter<Collection<PendragonDirectedTrait>>(),
		reader, new XSDValidator(PathUtils.getClassPathResource(Paths
			.get(ModelFile.VALIDATION_DIRECTED_TRAIT))));

	return file.read(PathUtils.getClassPathResource(Paths
		.get(ModelFile.DIRECTED_TRAIT)));
    }

}
