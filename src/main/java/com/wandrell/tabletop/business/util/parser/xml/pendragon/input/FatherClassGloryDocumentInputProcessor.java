package com.wandrell.tabletop.business.util.parser.xml.pendragon.input;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.business.conf.pendragon.FileToken;
import com.wandrell.tabletop.business.model.pendragon.glory.FatherClassGlory;
import com.wandrell.util.parser.xml.input.JDOMDocumentInputProcessor;

public class FatherClassGloryDocumentInputProcessor implements
        JDOMDocumentInputProcessor<FatherClassGlory> {

    public FatherClassGloryDocumentInputProcessor() {
        super();
    }

    @Override
    public final FatherClassGlory process(final Document doc) {
        final FatherClassGlory holder;
        final Element gloryBase;
        // final Element gloryYear;
        final Element root;

        root = doc.getRootElement();

        gloryBase = root.getChild(FileToken.FATHER_CLASS_GLORY_BASE);
        // gloryYear = root.getChild(FileLabels.FATHER_CLASS_GLORY_PER_YEAR);

        // Father's class name
        // holder = new DefaultFatherClassGlory(
        // root.getAttributeValue(FileStreamerTags.NAME));

        // Base glory
        if (gloryBase != null) {
            // TODO
            // holder.getBaseGlory().setValue(
            // XMLUtils.readIntegerValueHandlerXMLNode(gloryBase,
            // new IntegerValueHandler()).getStoredValue());
        }

        // Per year glory
        if (gloryBase != null) {
            // TODO
            // holder.getGloryPerYear().setValue(
            // XMLUtils.readIntegerValueHandlerXMLNode(gloryYear,
            // new IntegerValueHandler()).getStoredValue());
        }

        return null;
    }

}
