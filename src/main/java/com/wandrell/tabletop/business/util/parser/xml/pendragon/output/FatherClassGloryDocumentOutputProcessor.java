package com.wandrell.tabletop.business.util.parser.xml.pendragon.output;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.business.conf.pendragon.FileToken;
import com.wandrell.tabletop.business.model.pendragon.glory.FatherClassGlory;
import com.wandrell.util.parser.xml.output.JDOMDocumentOutputProcessor;

public class FatherClassGloryDocumentOutputProcessor implements
        JDOMDocumentOutputProcessor<FatherClassGlory> {

    public FatherClassGloryDocumentOutputProcessor() {
        super();
    }

    @Override
    public final Document process(final FatherClassGlory holder) {
        final Document doc;
        Element node;

        // Main body and name of the father's class
        node = new Element(FileToken.FATHER_CLASS_GLORY);
        // node.setAttribute(FileStreamerTags.NAME, holder.getName());
        doc = new Document(node);

        // node = XMLUtil.buildValueHandlerXMLNode(holder.getBaseGlory(),
        // FileToken.FATHER_CLASS_GLORY_BASE);
        // doc.getRootElement().addContent(node);

        // node = XMLUtil.buildValueHandlerXMLNode(holder.getGloryPerYear(),
        // FileToken.FATHER_CLASS_GLORY_PER_YEAR);
        // doc.getRootElement().addContent(node);

        return doc;
    }

}
