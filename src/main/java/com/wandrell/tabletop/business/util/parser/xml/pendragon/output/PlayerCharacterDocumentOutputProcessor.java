package com.wandrell.tabletop.business.util.parser.xml.pendragon.output;

import java.util.Iterator;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.business.conf.pendragon.FileToken;
import com.wandrell.tabletop.business.model.pendragon.character.HorseCharacter;
import com.wandrell.tabletop.business.model.pendragon.character.PendragonPlayerCharacter;
import com.wandrell.tabletop.business.model.pendragon.character.follower.Child;
import com.wandrell.tabletop.business.model.pendragon.character.follower.Follower;
import com.wandrell.tabletop.business.model.pendragon.character.follower.Wife;
import com.wandrell.tabletop.business.model.pendragon.chargen.FamilyCharacteristicBonus;
import com.wandrell.tabletop.business.model.pendragon.glory.GloryEvent;
import com.wandrell.tabletop.business.model.pendragon.glory.GloryManager;
import com.wandrell.tabletop.business.model.pendragon.inventory.Item;
import com.wandrell.util.parser.xml.output.JDOMDocumentOutputProcessor;

public class PlayerCharacterDocumentOutputProcessor implements
        JDOMDocumentOutputProcessor<PendragonPlayerCharacter> {

    @SuppressWarnings("unused")
    private static final Element buildGloryEventXMLNode(final GloryEvent glory) {
        final Element gloryNode;

        gloryNode = new Element(FileToken.GLORY_EVENT);
        // gloryNode.setAttribute(FileToken.DATE,
        // String.valueOf(glory.getYear().getStoredValue()));
        // gloryNode.setAttribute(FileToken.GLORY,
        // String.valueOf(glory.getGloryGained().getStoredValue()));
        gloryNode.addContent(new Element(FileToken.GLORY_EVENT_DESCRIPTION)
                .setText(String.valueOf(glory.getDescription())));

        return gloryNode;
    }

    @SuppressWarnings("unused")
    private static final Element buildGloryXMLTree(final GloryManager holder) {
        final Element nodeGlory = new Element(FileToken.GLORY_HISTORY);
        final Iterator<GloryEvent> iteratorGlory;

        // TODO
        // iteratorGlory = holder.getGloryHistoryIterator();
        // while (iteratorGlory.hasNext()) {
        // nodeGlory.addContent(buildGloryEventXMLNode(iteratorGlory.next()));
        // }

        return nodeGlory;
    }

    public PlayerCharacterDocumentOutputProcessor() {
        super();
    }

    @Override
    public final Document process(final PendragonPlayerCharacter holder) {
        @SuppressWarnings("unused")
        final Document doc;

        // TODO
        // doc = new Document(new Element(FileLabels.CHARACTER));
        // PersistenceFactory.getCharacterService().addSimpleCharacterToDocument(
        // doc, holder);

        // Glory
        // TODO
        // doc.getRootElement().addContent(
        // buildGloryXMLTree(holder.getGloryData()));

        // ValueHandlers
        // TODO
        // doc.getRootElement().addContent(
        // XMLUtils.buildIntegerValueHandlerXMLTree(new Element(
        // FileLabels.VALUE_HANDLERS), holder
        // .getValueHandlersIterator(), FileLabels.VALUE_HANDLER));

        // Flags
        // TODO
        // doc.getRootElement().addContent(
        // XMLUtils.buildBooleansSetXMLTree(new Element(FileLabels.FLAGS),
        // holder.getFlagsIterator()));

        // Text values
        // TODO
        // doc.getRootElement().addContent(
        // XMLUtils.buildStringsDictionaryXMLTree(new Element(
        // FileLabels.TEXT_VALUES),
        // holder.getTextValuesIterator(), FileLabels.TEXT_VALUE));

        // Features
        // TODO
        // XMLUtils.buildIntegerValueHandlerXMLTree(new
        // Element(FileLabels.FEATURES),
        // holder.getFeaturesIterator(), FileLabels.FEATURE);

        // Wives
        // TODO
        // doc.getRootElement().addContent(
        // buildWivesXMLTree(holder.getWivesIterator()));

        // Items carried
        // TODO
        // doc.getRootElement().addContent(
        // buildHoldingsXMLTree(holder.getHoldingsCarriedIterator(),
        // FileLabels.ITEMS_CARRIED));

        // Items at home
        // TODO
        // doc.getRootElement().addContent(
        // buildHoldingsXMLTree(holder.getHoldingsAtHomeIterator(),
        // FileLabels.ITEMS_AT_HOME));

        // Money
        // TODO
        // doc.getRootElement().addContent(
        // PersistenceFactory.getItemService().buildMoneyXMLNode(
        // holder.getMoneyData()));

        // Horses
        // TODO
        // doc.getRootElement().addContent(
        // buildHorsesXMLTree(holder.getHorsesIterator()));

        // Followers
        // doc.getRootElement().addContent(
        // buildFollowersXMLTree(holder.getFollowersIterator()));

        return null;
    }

    @SuppressWarnings("unused")
    private final Element buildFamilyCharacteristicXMLTree(
            final FamilyCharacteristicBonus characteristic) {
        final Element root, bonusNode;

        root = new Element(FileToken.FAMILY_CHARACTERISTIC);
        // root.setAttribute(FileStreamerTags.NAME, characteristic.getName());

        // TODO
        // bonusNode = PersistenceFactory.getPendragonTemplateService()
        // .buildPendragonTemplateXMLNode(characteristic,
        // new Element(FileLabels.TEMPLATE_BONUS));
        // root.addContent(bonusNode);

        return root;
    }

    @SuppressWarnings("unused")
    private final Element buildFollowersXMLTree(
            final Iterator<Follower> itrFollowers) {
        Element root = new Element(FileToken.FOLLOWERS);
        while (itrFollowers.hasNext()) {
            root.addContent(buildFollowerXMLTree(itrFollowers.next()));
        }
        return root;
    }

    private final Element buildFollowerXMLTree(final Follower follower) {
        final Element followerNode;

        followerNode = new Element(FileToken.FOLLOWER);
        // followerNode.setAttribute(FileToken.FILE, follower.getFile());
        followerNode.setAttribute(FileToken.JOB, follower.getJob());

        return followerNode;
    }

    @SuppressWarnings("unused")
    private final Element buildHoldingsXMLTree(
            final Iterator<Item> itrHoldings, final String rootName) {
        Element root = new Element(rootName);
        while (itrHoldings.hasNext()) {
            // TODO
            // root.addContent(PersistenceFactory.getItemService()
            // .buildItemXMLNode(itrHoldings.next()));
        }
        return root;
    }

    @SuppressWarnings("unused")
    private final Element buildHorsesXMLTree(
            final Iterator<HorseCharacter> itrHorses) {
        Element root = new Element(FileToken.HORSES);
        while (itrHorses.hasNext()) {
            root.addContent(buildHorseXMLTree(itrHorses.next()));
        }
        return root;
    }

    private final Element buildHorseXMLTree(final HorseCharacter horse) {
        final Element horseNode;

        horseNode = new Element(FileToken.HORSE);
        // horseNode.setAttribute(FileStreamerTags.NAME, horse.getName());
        horseNode.setAttribute(FileToken.RACE, horse.getHorseType());
        // TODO
        // horseNode.addContent(XMLUtils.buildBooleansSetXMLTree(new Element(
        // FileLabels.FLAGS), horse.getFlagsIterator()));

        return horseNode;
    }

    @SuppressWarnings("unused")
    private final Element buildWifeXMLTree(final Wife wife) {
        final Element root, children;
        final Iterator<Child> iteratorChildren;
        Child child;
        Element childNode;

        root = new Element(FileToken.WIFE);
        // root.setAttribute(FileToken.FILE, wife.getFile());
        // root.addContent(XMLUtil.buildValueHandlerXMLNode(wife.getYearWed(),
        // FileToken.YEAR_WEDDING));

        children = new Element(FileToken.CHILDREN);
        // TODO
        // iteratorChildren = wife.getChildrenIterator();
        // while (iteratorChildren.hasNext()) {
        // child = iteratorChildren.next();
        // childNode = new Element(FileLabels.CHILD);
        // childNode.setAttribute(FileStreamerTags.NAME, child.getChildName());
        // childNode.addContent(new Element(FileLabels.GENDER).setText(String
        // .valueOf(child.getGender().name())));
        // childNode.addContent(XMLUtils.buildValueHandlerXMLNode(
        // child.getYearBorn(), FileLabels.YEAR_BORN));
        // childNode.addContent(XMLUtils.buildValueHandlerXMLNode(
        // child.getYearDeath(), FileLabels.YEAR_DEATH));
        //
        // children.addContent(childNode);
        // }
        root.addContent(children);

        return root;
    }

    @SuppressWarnings("unused")
    private final Element buildWivesXMLTree(final Iterator<Wife> itrWives) {
        Element root = new Element(FileToken.WIVES);
        while (itrWives.hasNext()) {
            root.addContent(buildWifeXMLTree(itrWives.next()));
        }
        return root;
    }
}
