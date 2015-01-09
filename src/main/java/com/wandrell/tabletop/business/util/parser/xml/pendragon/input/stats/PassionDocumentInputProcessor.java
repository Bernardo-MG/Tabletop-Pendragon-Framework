package com.wandrell.tabletop.business.util.parser.xml.pendragon.input.stats;

import java.util.Collection;
import java.util.LinkedList;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.business.conf.pendragon.ModelXMLConf;
import com.wandrell.tabletop.business.model.pendragon.stats.Passion;
import com.wandrell.tabletop.business.service.pendragon.ModelService;
import com.wandrell.util.parser.xml.input.JDOMDocumentInputProcessor;

public final class PassionDocumentInputProcessor implements
        JDOMDocumentInputProcessor<Collection<Passion>> {

    private final ModelService modelService;

    public PassionDocumentInputProcessor(final ModelService service) {
        super();

        modelService = service;
    }

    @Override
    public final Collection<Passion> process(final Document doc) {
        final Collection<Passion> passions;
        Passion passion;
        String name;
        String descriptor;
        Boolean repeatable;

        passions = new LinkedList<>();
        for (final Element node : doc.getRootElement().getChildren()) {
            name = node.getChildText(ModelXMLConf.NAME);
            descriptor = node.getChildText(ModelXMLConf.DESCRIPTOR);

            repeatable = new Boolean(
                    node.getAttributeValue(ModelXMLConf.REPEATEABLE));

            passion = getModelService()
                    .getPassion(name, descriptor, repeatable);

            passions.add(passion);
        }

        return passions;
    }

    private final ModelService getModelService() {
        return modelService;
    }

}
