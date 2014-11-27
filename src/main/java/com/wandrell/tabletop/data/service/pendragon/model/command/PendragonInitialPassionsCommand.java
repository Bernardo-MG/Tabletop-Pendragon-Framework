package com.wandrell.tabletop.data.service.pendragon.model.command;

import java.util.Collection;

import com.wandrell.tabletop.business.conf.pendragon.FileToken;
import com.wandrell.tabletop.business.conf.pendragon.ModelFile;
import com.wandrell.tabletop.business.model.pendragon.valuehandler.Passion;
import com.wandrell.tabletop.business.util.parser.xml.pendragon.input.PassionDocumentInputProcessor;
import com.wandrell.util.ResourceUtils;
import com.wandrell.util.command.ReturnCommand;
import com.wandrell.util.parser.xml.input.AbstractFilteredXMLInputParser;
import com.wandrell.util.parser.xml.input.FilteredJDOMSAXInputParser;

public final class PendragonInitialPassionsCommand implements
        ReturnCommand<Collection<Passion>> {

    public PendragonInitialPassionsCommand() {
        super();
    }

    @Override
    public final Collection<Passion> execute() throws Exception {
        final AbstractFilteredXMLInputParser<Collection<Passion>> file;

        file = new FilteredJDOMSAXInputParser<Collection<Passion>>(
                FileToken.PASSION, new PassionDocumentInputProcessor());
        file.addRejectedAttribute(FileToken.REPEAT);
        file.addRejectedAttribute(FileToken.RARE);

        return file.read(ResourceUtils
                .getClassPathInputStream(ModelFile.PASSION));
    }

}
