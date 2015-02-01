package com.wandrell.tabletop.business.util.parser.xml.pendragon.output;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.business.conf.pendragon.FileToken;
import com.wandrell.tabletop.business.model.pendragon.stats.SpecialtySkill;
import com.wandrell.util.parser.xml.output.JDOMDocumentEncoder;

public class SpecialtyDocumentOutputProcessor implements
        JDOMDocumentEncoder<SpecialtySkill> {

    public SpecialtyDocumentOutputProcessor() {
        super();
    }

    @Override
    public final Document encode(final SpecialtySkill holder) {
        final Document doc;
        Element element;

        // Root and skill name
        element = new Element(FileToken.ADVANCED_SKILL);
        // element.setAttribute(FileStreamerTags.NAME, holder.getName());

        doc = new Document(element);

        // Skills list
        // TODO
        // doc.getRootElement().addContent(
        // XMLUtils.buildStringListXMLTree(new Element(FileLabels.SKILLS),
        // holder.getSkillsNames(), FileLabels.SKILL));

        return doc;
    }

}
