package com.wandrell.tabletop.data.service.pendragon.model.command;

import java.util.Collection;

import com.wandrell.tabletop.business.conf.pendragon.FileToken;
import com.wandrell.tabletop.business.conf.pendragon.ModelFile;
import com.wandrell.tabletop.business.model.pendragon.valuehandler.DirectedTrait;
import com.wandrell.tabletop.business.util.parser.xml.pendragon.input.DirectedTraitDocumentInputProcessor;
import com.wandrell.util.ResourceUtils;
import com.wandrell.util.command.ReturnCommand;
import com.wandrell.util.parser.xml.input.AbstractFilteredXMLInputParser;
import com.wandrell.util.parser.xml.input.FilteredJDOMSAXInputParser;

public final class PendragonDirectedTraitsCommand implements
        ReturnCommand<Collection<DirectedTrait>> {

    public PendragonDirectedTraitsCommand() {
        super();
    }

    @Override
    public final Collection<DirectedTrait> execute() throws Exception {
        final AbstractFilteredXMLInputParser<Collection<DirectedTrait>> file;

        file = new FilteredJDOMSAXInputParser<Collection<DirectedTrait>>(
                FileToken.DIRECTED_TRAIT,
                new DirectedTraitDocumentInputProcessor());

        return file.read(ResourceUtils
                .getClassPathInputStream(ModelFile.DIRECTED_TRAIT));
    }

}
