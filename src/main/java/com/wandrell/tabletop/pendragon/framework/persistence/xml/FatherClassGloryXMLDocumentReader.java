package com.wandrell.tabletop.pendragon.framework.persistence.xml;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.conf.FileStreamerTags;
import com.wandrell.tabletop.pendragon.framework.conf.FileToken;
import com.wandrell.tabletop.pendragon.glory.DefaultFatherClassGlory;
import com.wandrell.tabletop.pendragon.glory.FatherClassGlory;
import com.wandrell.util.file.api.xml.XMLDocumentReader;

public class FatherClassGloryXMLDocumentReader implements
	XMLDocumentReader<FatherClassGlory> {

    public FatherClassGloryXMLDocumentReader() {
	super();
    }

    @Override
    public final FatherClassGlory getValue(final Document doc) {
	final FatherClassGlory holder;
	final Element gloryBase;
	// final Element gloryYear;
	final Element root;

	root = doc.getRootElement();

	gloryBase = root.getChild(FileToken.FATHER_CLASS_GLORY_BASE);
	// gloryYear = root.getChild(FileLabels.FATHER_CLASS_GLORY_PER_YEAR);

	// Father's class name
	holder = new DefaultFatherClassGlory(
		root.getAttributeValue(FileStreamerTags.NAME));

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

	return holder;
    }

}
