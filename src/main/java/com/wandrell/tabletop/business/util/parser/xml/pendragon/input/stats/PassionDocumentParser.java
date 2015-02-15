package com.wandrell.tabletop.business.util.parser.xml.pendragon.input.stats;

import java.util.Collection;
import java.util.LinkedList;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.business.conf.pendragon.ModelXMLConf;
import com.wandrell.tabletop.business.model.valuebox.DefaultSkillBox;
import com.wandrell.tabletop.business.model.valuebox.SkillBox;

public final class PassionDocumentParser implements
        Parser<Document, Collection<SkillBox>> {

    public PassionDocumentParser() {
        super();
    }

    @Override
    public final Collection<SkillBox> parse(final Document doc) {
        final Collection<SkillBox> passions;
        SkillBox passion;
        String name;
        String descriptor;

        passions = new LinkedList<>();
        for (final Element node : doc.getRootElement().getChildren()) {
            name = node.getChildText(ModelXMLConf.NAME);

            if (node.getChild(ModelXMLConf.DESCRIPTOR) == null) {
                passion = new DefaultSkillBox(name, 0, 0, Integer.MAX_VALUE);
            } else {
                descriptor = node.getChildText(ModelXMLConf.DESCRIPTOR);
                passion = new DefaultSkillBox(name, descriptor, 0, 0,
                        Integer.MAX_VALUE);
            }

            passions.add(passion);
        }

        return passions;
    }

}
