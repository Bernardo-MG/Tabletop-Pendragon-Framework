package com.wandrell.tabletop.business.util.parser.xml.pendragon.input.interval;

import java.util.Collection;
import java.util.LinkedList;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.business.conf.pendragon.ModelXMLConf;
import com.wandrell.tabletop.business.model.interval.IntervalTable;
import com.wandrell.tabletop.business.model.pendragon.util.TextList;
import com.wandrell.tabletop.business.service.pendragon.ModelService;
import com.wandrell.util.parser.xml.input.JDOMDocumentInputProcessor;

public final class TextListDocumentInputProcessor implements
        JDOMDocumentInputProcessor<TextList> {

    private final ModelService modelService;

    public TextListDocumentInputProcessor(final ModelService service) {
        super();

        modelService = service;
    }

    @Override
    public final TextList process(final Document doc) {
        final Element root;
        final Element valuesNode;
        final String name;
        final IntervalTable<String> table;
        final Collection<String> values;

        root = doc.getRootElement();

        // Name
        name = root.getChildText(ModelXMLConf.NAME);

        valuesNode = root.getChild(ModelXMLConf.VALUES);
        values = new LinkedList<>();
        for (final Element value : valuesNode.getChildren()) {
            values.add(value.getText());
        }

        return getModelService().getTextList(name, values);
    }

    private final ModelService getModelService() {
        return modelService;
    }

}
