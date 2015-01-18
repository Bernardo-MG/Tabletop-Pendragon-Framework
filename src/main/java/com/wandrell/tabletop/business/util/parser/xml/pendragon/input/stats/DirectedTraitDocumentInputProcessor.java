package com.wandrell.tabletop.business.util.parser.xml.pendragon.input.stats;

import java.util.Collection;
import java.util.LinkedList;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.business.conf.pendragon.ModelXMLConf;
import com.wandrell.tabletop.business.model.valuebox.DefaultSkillBox;
import com.wandrell.tabletop.business.model.valuebox.SkillBox;
import com.wandrell.util.parser.xml.input.JDOMDocumentInputProcessor;

public final class DirectedTraitDocumentInputProcessor implements
        JDOMDocumentInputProcessor<Collection<SkillBox>> {

    public DirectedTraitDocumentInputProcessor() {
        super();
    }

    @Override
    public final Collection<SkillBox> process(final Document doc) {
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
