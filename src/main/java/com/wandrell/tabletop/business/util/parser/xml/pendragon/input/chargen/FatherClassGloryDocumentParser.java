package com.wandrell.tabletop.business.util.parser.xml.pendragon.input.chargen;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.business.conf.pendragon.ModelXMLConf;
import com.wandrell.tabletop.business.model.pendragon.glory.FatherClassGlory;
import com.wandrell.tabletop.business.service.pendragon.ModelService;

public final class FatherClassGloryDocumentParser implements
        Parser<Document, FatherClassGlory> {

    private final ModelService modelService;

    public FatherClassGloryDocumentParser(final ModelService service) {
        super();

        modelService = service;
    }

    @Override
    public final FatherClassGlory parse(final Document doc) {
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
