package com.wandrell.tabletop.data.service.pendragon.model.command;

import java.util.Collection;

import com.wandrell.tabletop.business.conf.pendragon.ModelFile;
import com.wandrell.tabletop.business.util.parser.xml.pendragon.input.TraitNameDocumentInputProcessor;
import com.wandrell.util.ResourceUtils;
import com.wandrell.util.command.ReturnCommand;
import com.wandrell.util.parser.InputParser;
import com.wandrell.util.parser.xml.input.JDOMSAXInputParser;

public final class PendragonTraitNamesCommand implements
        ReturnCommand<Collection<String>> {

    public PendragonTraitNamesCommand() {
        super();
    }

    @Override
    public final Collection<String> execute() throws Exception {
        final InputParser<Collection<String>> file;

        file = new JDOMSAXInputParser<>(new TraitNameDocumentInputProcessor());

        return file
                .read(ResourceUtils.getClassPathInputStream(ModelFile.TRAIT));
    }

}
