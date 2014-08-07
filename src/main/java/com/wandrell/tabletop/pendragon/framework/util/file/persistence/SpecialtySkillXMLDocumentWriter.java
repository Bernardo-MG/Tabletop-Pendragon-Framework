package com.wandrell.tabletop.pendragon.framework.util.file.persistence;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.conf.FileStreamerTags;
import com.wandrell.tabletop.pendragon.framework.conf.FileToken;
import com.wandrell.tabletop.pendragon.valuehandler.SpecialtySkill;
import com.wandrell.util.file.api.xml.XMLDocumentWriter;

public class SpecialtySkillXMLDocumentWriter implements
        XMLDocumentWriter<SpecialtySkill> {

    public SpecialtySkillXMLDocumentWriter() {
        super();
    }

    @Override
    public final Document getDocument(final SpecialtySkill holder) {
        final Document doc;
        Element element;

        // Root and skill name
        element = new Element(FileToken.ADVANCED_SKILL);
        element.setAttribute(FileStreamerTags.NAME, holder.getName());

        doc = new Document(element);

        // Skills list
        // TODO
        // doc.getRootElement().addContent(
        // XMLUtils.buildStringListXMLTree(new Element(FileLabels.SKILLS),
        // holder.getSkillsNames(), FileLabels.SKILL));

        return doc;
    }

}
