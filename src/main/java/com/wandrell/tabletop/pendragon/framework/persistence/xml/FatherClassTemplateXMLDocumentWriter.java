package com.wandrell.tabletop.pendragon.framework.persistence.xml;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.conf.FileStreamerTags;
import com.wandrell.tabletop.pendragon.character.background.FatherClass;
import com.wandrell.tabletop.pendragon.framework.conf.FileToken;
import com.wandrell.util.file.api.xml.XMLDocumentWriter;

public class FatherClassTemplateXMLDocumentWriter implements
	XMLDocumentWriter<FatherClass> {

    public FatherClassTemplateXMLDocumentWriter() {
	super();
    }

    @Override
    public final Document getDocument(final FatherClass holder) {
	final Document doc;
	Element node;

	// Main body and name of the father's class
	node = new Element(FileToken.FATHER_CLASS);
	node.setAttribute(FileStreamerTags.NAME, holder.getName());
	doc = new Document(node);

	// TODO
	// PersistenceFactory.getTemplatesContainerService().addToXMLDocument(doc,
	// holder);

	// Skills group
	node = buildSkillsGroupXMLTree(holder);
	if (node.getChildren().size() > 0) {
	    doc.getRootElement().addContent(node);
	}

	// Skills points
	// TODO
	// node = XMLUtils.buildIntegerValueHandlerXMLTree(new Element(
	// FileLabels.SKILLS_POINTS), holder.getSkillsPointsIterator(),
	// FileLabels.SKILL_POINTS);
	if (node.getChildren().size() > 0) {
	    doc.getRootElement().addContent(node);
	}

	// Money
	// TODO
	// node = PersistenceFactory.getItemService().buildMoneyXMLNode(
	// holder.getMoneyData());
	if (node.getChildren().size() > 0) {
	    doc.getRootElement().addContent(node);
	}

	return doc;
    }

    private final Element buildSkillsGroupXMLTree(final FatherClass holder) {
	// TODO
	// final Iterator<DefaultPendragonSkill> iterator;
	// Element root, nodeVH, skillsRoot;
	//
	// root = new Element(FileLabels.SKILLS_GROUP);
	//
	// iterator = holder.getSkillsGroupIterator();
	// skillsRoot = new Element(FileLabels.SKILLS);
	// while (iterator.hasNext()) {
	// nodeVH = XMLUtils.buildValueHandlerXMLNode(iterator.next(),
	// FileLabels.SKILL);
	// nodeVH.removeAttribute(FileStreamerTags.VALUE);
	// skillsRoot.addContent(nodeVH);
	// }
	//
	// if (skillsRoot.getChildren().size() > 0) {
	// root.addContent(new Element(NameLabels.VH_SKILLS_POINTS)
	// .setAttribute(
	// FileStreamerTags.VALUE,
	// holder.getSkillsGroupPoints().getStoredValue()
	// .toString()).setAttribute(
	// FileStreamerTags.NAME, NameLabels.VH_SKILLS_POINTS));
	// root.addContent(skillsRoot);
	// }
	//
	return null;
    }

}
