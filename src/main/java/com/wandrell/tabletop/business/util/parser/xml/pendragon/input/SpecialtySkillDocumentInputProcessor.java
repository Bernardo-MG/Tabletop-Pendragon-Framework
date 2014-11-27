package com.wandrell.tabletop.business.util.parser.xml.pendragon.input;

import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.business.conf.pendragon.FileToken;
import com.wandrell.tabletop.business.conf.pendragon.factory.PendragonFactory;
import com.wandrell.tabletop.business.model.pendragon.valuehandler.SpecialtySkill;
import com.wandrell.util.parser.xml.input.JDOMDocumentInputProcessor;

public class SpecialtySkillDocumentInputProcessor implements
        JDOMDocumentInputProcessor<SpecialtySkill> {

    public SpecialtySkillDocumentInputProcessor() {
        super();
    }

    @SuppressWarnings("unused")
    @Override
    public final SpecialtySkill process(final Document doc) {
        final Element skills;
        final String name;
        final Element root;
        final PendragonFactory factory;
        final List<String> list;

        root = doc.getRootElement();

        // Acquires the different sections
        skills = root.getChild(FileToken.SKILLS);

        // Skill's name
        // name = root.getAttributeValue(FileStreamerTags.NAME);

        // Acquires the different sections
        list = new ArrayList<String>(root.getChildren().size());
        for (final Element skill : skills.getChildren()) {
            // list.add(skill.getAttributeValue(FileStreamerTags.NAME));
        }

        factory = PendragonFactory.getInstance();
        // return factory.getSpecialtySkill(name, list);
        return null;
    }

}
