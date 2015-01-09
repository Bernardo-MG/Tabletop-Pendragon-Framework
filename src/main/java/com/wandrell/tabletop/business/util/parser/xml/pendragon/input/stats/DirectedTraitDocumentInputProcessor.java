package com.wandrell.tabletop.business.util.parser.xml.pendragon.input.stats;

import java.util.Collection;
import java.util.LinkedList;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.business.conf.pendragon.ModelXMLConf;
import com.wandrell.tabletop.business.model.pendragon.stats.DirectedTrait;
import com.wandrell.tabletop.business.service.pendragon.ModelService;
import com.wandrell.util.parser.xml.input.JDOMDocumentInputProcessor;

public final class DirectedTraitDocumentInputProcessor implements
        JDOMDocumentInputProcessor<Collection<DirectedTrait>> {

    private final ModelService modelService;

    public DirectedTraitDocumentInputProcessor(final ModelService service) {
        super();

        modelService = service;
    }

    @Override
    public final Collection<DirectedTrait> process(final Document doc) {
        final Collection<DirectedTrait> traits;
        DirectedTrait trait;
        String name;

        traits = new LinkedList<>();
        for (final Element node : doc.getRootElement().getChildren()) {
            name = node.getChildText(ModelXMLConf.NAME);

            trait = getModelService().getDirectedTrait(name);

            traits.add(trait);
        }

        return traits;
    }

    private final ModelService getModelService() {
        return modelService;
    }

}
