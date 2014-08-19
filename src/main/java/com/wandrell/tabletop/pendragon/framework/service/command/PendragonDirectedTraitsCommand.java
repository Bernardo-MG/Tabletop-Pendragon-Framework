package com.wandrell.tabletop.pendragon.framework.service.command;

import java.util.Collection;

import com.wandrell.tabletop.pendragon.framework.conf.FileToken;
import com.wandrell.tabletop.pendragon.framework.conf.ModelFile;
import com.wandrell.tabletop.pendragon.framework.util.file.model.DirectedTraitNodesReader;
import com.wandrell.tabletop.pendragon.valuehandler.DirectedTrait;
import com.wandrell.util.PathUtils;
import com.wandrell.util.command.ReturnCommand;
import com.wandrell.util.file.api.FileHandler;
import com.wandrell.util.file.api.xml.FilteredXMLDocumentReader;
import com.wandrell.util.file.impl.xml.DefaultFilteredXMLDocumentReader;
import com.wandrell.util.file.impl.xml.DefaultXMLFileHandler;
import com.wandrell.util.file.impl.xml.DisabledXMLWriter;
import com.wandrell.util.file.impl.xml.XSDValidator;

public final class PendragonDirectedTraitsCommand implements
        ReturnCommand<Collection<DirectedTrait>> {

    public PendragonDirectedTraitsCommand() {
        super();
    }

    @Override
    public final Collection<DirectedTrait> execute() {
        final FileHandler<Collection<DirectedTrait>> file;
        final FilteredXMLDocumentReader<Collection<DirectedTrait>> reader;

        reader = new DefaultFilteredXMLDocumentReader<Collection<DirectedTrait>>(
                FileToken.DIRECTED_TRAIT, new DirectedTraitNodesReader());

        file = new DefaultXMLFileHandler<>(
                new DisabledXMLWriter<Collection<DirectedTrait>>(),
                reader,
                new XSDValidator(
                        PathUtils
                                .getClassPathResource(ModelFile.VALIDATION_DIRECTED_TRAIT)));

        return file.read(PathUtils
                .getClassPathResource(ModelFile.DIRECTED_TRAIT));
    }

}
