package com.wandrell.tabletop.business.util.parser.xml.pendragon.input.glory;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.business.conf.pendragon.ModelXMLConf;
import com.wandrell.tabletop.business.model.pendragon.glory.FatherClassGlory;
import com.wandrell.tabletop.business.service.pendragon.ModelService;
import com.wandrell.util.parser.xml.input.JDOMDocumentInputProcessor;

public final class FatherClassGloryDocumentInputProcessor implements
        JDOMDocumentInputProcessor<FatherClassGlory> {

    private final ModelService modelService;

    public FatherClassGloryDocumentInputProcessor(final ModelService service) {
        super();

        modelService = service;
    }

    @Override
    public final FatherClassGlory process(final Document doc) {
        final Element root;
        final String name;
        final Integer glory;
        final Integer yearlyGlory;

        root = doc.getRootElement();

        // Name
        name = root.getChildText(ModelXMLConf.NAME);

        // Glory
        glory = Integer.parseInt(root.getChildText(ModelXMLConf.BASE_GLORY));
        yearlyGlory = Integer.parseInt(root
                .getChildText(ModelXMLConf.YEARLY_GLORY));

        return getModelService().getFatherClassGlory(name, glory, yearlyGlory);
    }

    private final ModelService getModelService() {
        return modelService;
    }

}
