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

public final class PendragonRepeatablePassionsCommand implements
        ReturnCommand<Collection<Passion>> {

    public PendragonRepeatablePassionsCommand() {
        super();
    }

    @Override
    public final Collection<Passion> execute() throws Exception {
        final AbstractFilteredXMLInputParser<Collection<Passion>> file;

        file = new FilteredJDOMSAXInputParser<Collection<Passion>>(
                FileToken.PASSION, new PassionDocumentInputProcessor());
        file.addRequiredAttribute(FileToken.REPEAT);

        return file.read(ResourceUtils
                .getClassPathInputStream(ModelFile.PASSION));
    }

}
