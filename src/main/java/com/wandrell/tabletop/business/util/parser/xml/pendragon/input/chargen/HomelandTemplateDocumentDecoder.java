package com.wandrell.tabletop.business.util.parser.xml.pendragon.input.chargen;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.business.conf.pendragon.ModelXMLConf;
import com.wandrell.tabletop.business.model.pendragon.chargen.HomelandTemplate;
import com.wandrell.tabletop.business.model.skill.DefaultNameAndDescriptor;
import com.wandrell.tabletop.business.model.skill.NameAndDescriptor;
import com.wandrell.tabletop.business.service.pendragon.ModelService;
import com.wandrell.util.parser.xml.input.JDOMDocumentDecoder;

public final class HomelandTemplateDocumentDecoder implements
        JDOMDocumentDecoder<HomelandTemplate> {

    private final ModelService modelService;

    public HomelandTemplateDocumentDecoder(final ModelService service) {
        super();

        modelService = service;
    }

    @Override
    public final HomelandTemplate decode(final Document doc) {
        final Element root;
        final Element bonus;
        final Element skillsNode;
        final Element specialtySkillsNode;
        final Element traitsNode;
        final Element directedTraitsNode;
        final Element passionsNode;
        final String name;
        final Map<NameAndDescriptor, Integer> skills;
        final Map<String, Integer> specialtySkills;
        final Map<String, Integer> traits;
        final Collection<NameAndDescriptor> passions;
        final Collection<NameAndDescriptor> directedTraits;
        NameAndDescriptor skillData;
        String descriptor;

        root = doc.getRootElement();

        // Name
        name = root.getChildText(ModelXMLConf.NAME);

        bonus = root.getChild(ModelXMLConf.BONUS);

        // Skills
        skills = new LinkedHashMap<>();
        skillsNode = bonus.getChild(ModelXMLConf.SKILLS);
        if (skillsNode != null) {
            for (final Element skill : skillsNode.getChildren()) {
                if (skill.getChild(ModelXMLConf.DESCRIPTOR) == null) {
                    descriptor = "";
                } else {
                    descriptor = skill.getChildText(ModelXMLConf.DESCRIPTOR);
                }
                skillData = new DefaultNameAndDescriptor(
                        skill.getChildText(ModelXMLConf.NAME), descriptor);
                skills.put(skillData, Integer.parseInt(skill
                        .getChildText(ModelXMLConf.VALUE)));
            }
        }

        // Specialty skills
        specialtySkills = new LinkedHashMap<>();
        specialtySkillsNode = bonus.getChild(ModelXMLConf.SPECIALTY_SKILLS);
        if (specialtySkillsNode != null) {
            for (final Element skill : specialtySkillsNode.getChildren()) {
                specialtySkills
                        .put(skill.getChildText(ModelXMLConf.NAME), Integer
                                .parseInt(skill
                                        .getChildText(ModelXMLConf.VALUE)));
            }
        }

        // Traits
        traits = new LinkedHashMap<>();
        traitsNode = bonus.getChild(ModelXMLConf.TRAITS);
        if (traitsNode != null) {
            for (final Element trait : traitsNode.getChildren()) {
                traits.put(trait.getChildText(ModelXMLConf.NAME), Integer
                        .parseInt(trait.getChildText(ModelXMLConf.VALUE)));
            }
        }

        // Directed traits
        directedTraits = new LinkedList<>();
        directedTraitsNode = bonus.getChild(ModelXMLConf.DIRECTED_TRAITS);
        if (directedTraitsNode != null) {
            for (final Element trait : directedTraitsNode.getChildren()) {
                skillData = new DefaultNameAndDescriptor(
                        trait.getChildText(ModelXMLConf.NAME),
                        trait.getChildText(ModelXMLConf.DESCRIPTOR));
                directedTraits.add(skillData);
            }
        }

        // Passions
        passions = new LinkedList<>();
        passionsNode = bonus.getChild(ModelXMLConf.PASSIONS);
        if (passionsNode != null) {
            for (final Element passion : passionsNode.getChildren()) {
                skillData = new DefaultNameAndDescriptor(
                        passion.getChildText(ModelXMLConf.NAME),
                        passion.getChildText(ModelXMLConf.DESCRIPTOR));
                passions.add(skillData);
            }
        }

        return getModelService().getHomelandTemplate(name, skills,
                specialtySkills, traits, directedTraits, passions);
    }

    private final ModelService getModelService() {
        return modelService;
    }

}
