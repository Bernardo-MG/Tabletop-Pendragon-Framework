package com.wandrell.tabletop.pendragon.framework.service.command;

import java.util.Collection;

import com.wandrell.tabletop.pendragon.framework.conf.ModelFile;
import com.wandrell.tabletop.pendragon.framework.util.file.model.TraitNameXMLDocumentReader;
import com.wandrell.util.PathUtils;
import com.wandrell.util.command.ReturnCommand;
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

        file = new DefaultXMLFileHandler<>(
                new DisabledXMLWriter<Collection<String>>(),
                new TraitNameXMLDocumentReader(),
                new XSDValidator(PathUtils
                        .getClassPathResource(ModelFile.VALIDATION_TRAIT)));

        return file.read(PathUtils.getClassPathResource(ModelFile.TRAIT));
    }

}
