package com.wandrell.tabletop.business.util.parser.xml.pendragon.input;

import java.util.Iterator;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.business.conf.pendragon.FileToken;
import com.wandrell.tabletop.business.model.interval.Interval;
import com.wandrell.tabletop.business.model.pendragon.chargen.CultureCharacterTemplate;
import com.wandrell.tabletop.business.model.pendragon.chargen.CultureTemplate;
import com.wandrell.util.parser.xml.input.JDOMDocumentInputProcessor;

public class CultureDocumentInputProcessor implements
        JDOMDocumentInputProcessor<CultureTemplate> {

    public final static CultureCharacterTemplate
            loadAttributesLimitsTemplateXMLNode(final Element root) {
        // final Element attributesLimits;
        // TODO
        //
        // TODO: Hacer mejor. Que pida para cada parte solo el valuehandler que
        // necesite
        // attributesLimits = root.getChild(FileLabels.ATTRIBUTES_LIMITS);
        //
        // PersistenceFactory.getPendragonTemplateService()
        // .readPendragonTemplateXMLTree(root, new
        // DefaultCultureCharacterTemplate());
        //
        // Attributes limits
        // if (attributesLimits != null) {
        // holder.setAttributeLimits(readAttributesLimits(attributesLimits));
        // }
        //
        return null;
    }

    @SuppressWarnings("unused")
    private final static Iterator<Interval> readAttributesLimits(
            final Element nodeLimits) {
        // TODO
        // final List<NamedContrastInterval<Integer>> listLimits;
        // NamedContrastInterval<Integer> interval;
        //
        // listLimits = new ArrayList<NamedContrastInterval<Integer>>(nodeLimits
        // .getChildren().size());
        // for (final Element node : nodeLimits.getChildren()) {
        // TODO: Vigilar esto
        // interval = (NamedContrastInterval<Integer>) XMLUtils
        // .readIntegerIntervalXMLNode(node);
        // listLimits.addInterval(interval);
        // }
        //
        // return listLimits.iterator();
        return null;
    }

    public CultureDocumentInputProcessor() {
        super();
    }

    @Override
    public final CultureTemplate process(final Document doc) {
        final CultureTemplate holder;
        final Element root;
        final Element files, templateFemale, templateMale, templateRandomFemale, templateRandomMale;

        root = doc.getRootElement();

        // Acquires the different sections
        files = root.getChild(FileToken.SELECTORS_FILES);
        templateFemale = root.getChild(FileToken.TEMPLATE_FEMALE);
        templateMale = root.getChild(FileToken.TEMPLATE_MALE);
        templateRandomFemale = root.getChild(FileToken.TEMPLATE_FEMALE_RANDOM);
        templateRandomMale = root.getChild(FileToken.TEMPLATE_MALE_RANDOM);

        // Culture's name
        // holder = new DefaultCulture(
        // root.getAttributeValue(FileStreamerTags.NAME));

        // Files
        if (files != null) {
            // TODO
            // holder.setFiles(XMLUtils.readStringsDictionaryXMLTree(files));
        }

        // Female template
        if (templateFemale != null) {
            // TODO
            // holder.getFemaleTemplate().copy(
            // loadAttributesLimitsTemplateXMLNode(templateFemale));
        }

        // Male template
        if (templateMale != null) {
            // TODO
            // holder.getMaleTemplate().copy(
            // loadAttributesLimitsTemplateXMLNode(templateMale));
        }

        // Random generation templates

        // Female template
        if (templateRandomFemale != null) {
            // TODO
            // holder.getFemaleRandomTemplate().copy(
            // loadAttributesLimitsTemplateXMLNode(templateRandomFemale));
        }

        // Male template
        if (templateRandomMale != null) {
            // TODO
            // holder.getMaleRandomTemplate().copy(
            // loadAttributesLimitsTemplateXMLNode(templateRandomMale));
        }

        return null;
    }

}
