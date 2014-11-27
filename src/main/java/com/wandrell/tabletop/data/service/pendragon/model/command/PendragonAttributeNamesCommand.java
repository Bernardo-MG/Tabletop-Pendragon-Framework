package com.wandrell.tabletop.data.service.pendragon.model.command;

import java.util.Collection;

import com.wandrell.tabletop.business.conf.pendragon.ModelFile;
import com.wandrell.tabletop.business.util.parser.xml.pendragon.input.NameDocumentInputProcessor;
import com.wandrell.util.ResourceUtils;
import com.wandrell.util.command.ReturnCommand;
import com.wandrell.util.parser.InputParser;
import com.wandrell.util.parser.xml.input.JDOMSAXInputParser;

public final class PendragonAttributeNamesCommand implements
        ReturnCommand<Collection<String>> {

    public PendragonAttributeNamesCommand() {
        super();
    }

    @Override
    public final Collection<String> execute() throws Exception {
        final InputParser<Collection<String>> file;

        file = new JDOMSAXInputParser<>(new NameDocumentInputProcessor());

        return file.read(ResourceUtils
                .getClassPathInputStream(ModelFile.ATTRIBUTE));
    }

}
