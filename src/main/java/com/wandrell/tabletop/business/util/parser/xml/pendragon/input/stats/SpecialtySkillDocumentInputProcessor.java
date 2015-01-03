package com.wandrell.tabletop.business.util.parser.xml.pendragon.input.stats;

import java.util.Collection;
import java.util.LinkedList;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.business.conf.pendragon.ModelXMLConf;
import com.wandrell.tabletop.business.model.pendragon.stats.SpecialtySkill;
import com.wandrell.tabletop.business.service.pendragon.ModelService;
import com.wandrell.util.parser.xml.input.JDOMDocumentInputProcessor;

public final class SpecialtySkillDocumentInputProcessor implements
        JDOMDocumentInputProcessor<SpecialtySkill> {

    private final ModelService modelService;

    public SpecialtySkillDocumentInputProcessor(final ModelService service) {
        super();

        modelService = service;
    }

    @Override
    public final SpecialtySkill process(final Document doc) {
        final Element root;
        final Element skillsNode;
        final String name;
        final Collection<String> skills;

        root = doc.getRootElement();

        // Name
        name = root.getChildText(ModelXMLConf.NAME);

        // Skills
        skillsNode = root.getChild(ModelXMLConf.SKILLS);
        skills = new LinkedList<>();
        for (final Element skill : skillsNode.getChildren()) {
            skills.add(skill.getChildText(ModelXMLConf.NAME));
        }

        return getModelService().getSpecialtySkill(name, skills);
    }

    private final ModelService getModelService() {
        return modelService;
    }

}
