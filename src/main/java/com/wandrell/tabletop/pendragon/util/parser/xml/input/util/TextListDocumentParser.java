package com.wandrell.tabletop.pendragon.util.parser.xml.input.util;

import java.util.Collection;
import java.util.LinkedList;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.pendragon.conf.ModelXMLConf;
import com.wandrell.tabletop.pendragon.model.util.TextList;
import com.wandrell.tabletop.pendragon.service.ModelService;

public final class TextListDocumentParser implements Parser<Document, TextList> {

    private final ModelService modelService;

    public TextListDocumentParser(final ModelService service) {
        super();

        modelService = service;
    }

    @Override
    public final TextList parse(final Document doc) {
        final Element root;
        final Element valuesNode;
        final String name;
        final Collection<String> values;

        root = doc.getRootElement();

        // Name
        name = root.getChildText(ModelXMLConf.NAME);

        valuesNode = root.getChild(ModelXMLConf.VALUES);
        values = new LinkedList<String>();
        for (final Element value : valuesNode.getChildren()) {
            values.add(value.getText());
        }

        return getModelService().getTextList(name, values);
    }

    private final ModelService getModelService() {
        return modelService;
    }

}
