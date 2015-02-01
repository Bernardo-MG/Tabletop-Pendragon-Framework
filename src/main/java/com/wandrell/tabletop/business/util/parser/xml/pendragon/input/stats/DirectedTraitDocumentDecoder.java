package com.wandrell.tabletop.business.util.parser.xml.pendragon.input.stats;

import java.util.Collection;
import java.util.LinkedList;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.business.conf.pendragon.ModelXMLConf;
import com.wandrell.tabletop.business.model.valuebox.DefaultSkillBox;
import com.wandrell.tabletop.business.model.valuebox.SkillBox;
import com.wandrell.util.parser.xml.input.JDOMDocumentDecoder;

public final class DirectedTraitDocumentDecoder implements
        JDOMDocumentDecoder<Collection<SkillBox>> {

    public DirectedTraitDocumentDecoder() {
        super();
    }

    @Override
    public final Collection<SkillBox> decode(final Document doc) {
        final Collection<SkillBox> traits;
        SkillBox trait;
        String name;

        traits = new LinkedList<>();
        for (final Element node : doc.getRootElement().getChildren()) {
            name = node.getChildText(ModelXMLConf.NAME);

            trait = new DefaultSkillBox(name, 0, 0, Integer.MAX_VALUE);

            traits.add(trait);
        }

        return traits;
    }

}
