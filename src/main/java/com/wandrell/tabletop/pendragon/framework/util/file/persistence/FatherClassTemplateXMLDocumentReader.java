package com.wandrell.tabletop.pendragon.framework.util.file.persistence;

import java.util.Iterator;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.pendragon.character.background.FatherClass;
import com.wandrell.tabletop.pendragon.framework.conf.FileToken;
import com.wandrell.tabletop.pendragon.valuehandler.Skill;
import com.wandrell.util.file.api.xml.XMLDocumentReader;

public class FatherClassTemplateXMLDocumentReader implements
        XMLDocumentReader<FatherClass> {

    public FatherClassTemplateXMLDocumentReader() {
        super();
    }

    @Override
    public final FatherClass getValue(final Document doc) {
        final FatherClass holder;
        final Element skillsGroup, skillPoints, money;
        final Element root;

        root = doc.getRootElement();

        // TODO
        // PersistenceFactory.getTemplatesContainerService().readXMLTree(root,
        // holder);

        skillsGroup = root.getChild(FileToken.SKILLS_GROUP);
        skillPoints = root.getChild(FileToken.SKILLS_POINTS);
        money = root.getChild(FileToken.MONEY);

        // Father's class name
        // holder = new DefaultFatherClass(
        // root.getAttributeValue(FileStreamerTags.NAME));

        // Skills groups
        if (skillsGroup != null) {
            // readSkillsGroupXMLTree(skillsGroup, holder);
        }

        // Skills points
        if (skillPoints != null) {
            // TODO
            // holder.setSkillPoints(XMLUtils.readIntegerValueHandlerXMLTree(
            // skillPoints, new IntegerValueHandler()));
        }

        // Money
        if (money != null) {
            // TODO
            // PersistenceFactory.getItemService().readMoneyXMLNode(money,
            // holder.getMoneyData());
        }

        return null;
    }

    @SuppressWarnings("unused")
    private final Iterator<Skill> buildSkillsIterator(final FatherClass holder,
            final Element skills) {
        // TODO
        // final Iterator<? extends ValueHandler<Integer>> itrSkills;
        // final List<DefaultPendragonSkill> listSkills;
        // ValueHandler<Integer> vhValue;
        // DefaultPendragonSkill vhSkill;
        //
        // listSkills = new ArrayList<>();
        //
        // itrSkills = XMLUtils.readIntegerValueHandlerXMLTree(skills,
        // new IntegerValueHandler());
        // while (itrSkills.hasNext()) {
        // vhValue = itrSkills.next();
        //
        // vhSkill = SkillsFactory.getSkill(vhValue.getName());
        // vhSkill.setValue(vhValue.getStoredValue());
        //
        // listSkills.add(vhSkill);
        // }
        //
        // return listSkills.iterator();
        return null;
    }

    private final void readSkillsGroupXMLTree(final Element root,
            final FatherClass holder) {
        // TODO
        // final Element skills, points;
        //
        // skills = root.getChild(FileLabels.SKILLS);
        // points = root.getChild(NameLabels.VH_SKILLS_POINTS);
        //
        // holder.setSkillsGroup(buildSkillsIterator(holder, skills), XMLUtils
        // .readIntegerValueHandlerXMLNode(points,
        // new IntegerValueHandler()));
    }

}
