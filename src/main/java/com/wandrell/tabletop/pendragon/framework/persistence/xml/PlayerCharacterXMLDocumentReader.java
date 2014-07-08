package com.wandrell.tabletop.pendragon.framework.persistence.xml;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.pendragon.character.DefaultHorseCharacter;
import com.wandrell.tabletop.pendragon.character.DefaultPendragonPlayerCharacter;
import com.wandrell.tabletop.pendragon.character.HorseCharacter;
import com.wandrell.tabletop.pendragon.character.PendragonPlayerCharacter;
import com.wandrell.tabletop.pendragon.character.follower.Child;
import com.wandrell.tabletop.pendragon.character.follower.DefaultFollower;
import com.wandrell.tabletop.pendragon.character.follower.DefaultWife;
import com.wandrell.tabletop.pendragon.character.follower.Follower;
import com.wandrell.tabletop.pendragon.character.follower.Wife;
import com.wandrell.tabletop.pendragon.framework.conf.FileLabels;
import com.wandrell.tabletop.pendragon.glory.DefaultGloryEvent;
import com.wandrell.tabletop.pendragon.glory.GloryEvent;
import com.wandrell.tabletop.pendragon.inventory.PendragonItem;
import com.wandrell.tabletop.pendragon.valuehandler.PendragonAppearanceFeature;
import com.wandrell.util.file.api.xml.XMLDocumentReader;

public class PlayerCharacterXMLDocumentReader implements
	XMLDocumentReader<PendragonPlayerCharacter> {

    private static final GloryEvent readGloryEventXMLNode(final Element node) {
	final int year, gloryGained;
	final String description;

	year = Integer.parseInt(node.getAttributeValue(FileLabels.DATE));
	gloryGained = Integer
		.parseInt(node.getAttributeValue(FileLabels.GLORY));
	description = node.getChildText(FileLabels.GLORY_EVENT_DESCRIPTION);

	return new DefaultGloryEvent(year, gloryGained, description);
    }

    @SuppressWarnings("unused")
    private static final Iterator<GloryEvent> readGloryXMLTree(
	    final Element root) {
	final List<GloryEvent> listGlory;

	listGlory = new LinkedList<>();
	for (final Element node : root.getChildren()) {
	    listGlory.add(readGloryEventXMLNode(node));
	}

	return listGlory.iterator();
    }

    public PlayerCharacterXMLDocumentReader() {
	super();
    }

    @Override
    public final PendragonPlayerCharacter getValue(final Document doc) {
	final DefaultPendragonPlayerCharacter holder;
	@SuppressWarnings("unused")
	final Element glory, vhs, flags, texts, features, wives, famchar, invcarried, invhome, money, horses, squires, followers;
	final Element root;

	root = doc.getRootElement();
	holder = new DefaultPendragonPlayerCharacter(null);

	// Acquires the first sections
	texts = root.getChild(FileLabels.TEXT_VALUES);
	flags = root.getChild(FileLabels.FLAGS);

	// Flags
	if (flags != null) {
	    // TODO
	    // holder.setFlags(XMLUtils.readBooleanXMLTree(flags));
	}

	// Text values
	// TODO
	// holder.setTextValues(XMLUtils.readStringsDictionaryXMLTree(texts));

	// Religion
	// TODO
	// holder.setReligionData(PersistenceFactory.getPersistenceService()
	// .getReligion(NameLabels.TEXT_RELIGION_NAME));

	// TODO
	// PersistenceFactory.getCharacterService().addToBaseCharacter(root,
	// holder);

	// Acquires the different sections
	glory = root.getChild(FileLabels.GLORY_HISTORY);
	vhs = root.getChild(FileLabels.VALUE_HANDLERS);
	features = root.getChild(FileLabels.FEATURES);
	wives = root.getChild(FileLabels.WIVES);
	famchar = root.getChild(FileLabels.FAMILY_CHARACTERISTIC);
	invcarried = root.getChild(FileLabels.ITEMS_CARRIED);
	invhome = root.getChild(FileLabels.ITEMS_AT_HOME);
	money = root.getChild(FileLabels.MONEY);
	horses = root.getChild(FileLabels.HORSES);
	squires = root.getChild(FileLabels.SQUIRES);
	followers = root.getChild(FileLabels.FOLLOWERS);

	// Knight
	// TODO
	// holder.setKnight(holder.getFlag(NameLabels.FLAGS_KNIGHT));

	// Glory
	if (glory != null) {
	    // TODO
	    // holder.getGloryData().setGlory(readGloryXMLTree(glory));
	}

	// ValueHandlers
	if (vhs != null) {
	    // TODO
	    // holder.setValueHandlers(XMLUtils.readIntegerValueHandlerXMLTree(vhs,
	    // new IntegerValueHandler()));
	}

	// Features
	if (features != null) {
	    // TODO
	    // holder.setFeatures(loadFeatures(features));
	}

	// Wives
	if (wives != null) {
	    // TODO
	    // holder.setWives(readWivesXMLTree(wives));
	}

	// Items carried
	if (invcarried != null) {
	    // TODO
	    // holder.setHoldingsCarried(readItemsXMLTree(invcarried));
	}

	// Items at home
	if (invhome != null) {
	    // TODO
	    // holder.setHoldingsAtHome(readItemsXMLTree(invhome));
	}

	// Money
	if (money != null) {
	    // TODO
	    // PersistenceFactory.getItemService().readMoneyXMLNode(money,
	    // holder.getMoneyData());
	}

	// Horses
	if (horses != null) {
	    // TODO
	    // holder.setHorses(readHorsesXMLTree(horses));
	}

	// Followers
	if (followers != null) {
	    // TODO
	    // holder.setFollowers(readFollowersXMLTree(followers));
	}

	return holder;
    }

    @SuppressWarnings("unused")
    private final Iterator<PendragonAppearanceFeature> loadFeatures(
	    final Element root) {
	List<PendragonAppearanceFeature> listFeatures;

	listFeatures = new ArrayList<PendragonAppearanceFeature>(root
		.getChildren().size());
	for (final Element node : root.getChildren()) {
	    // TODO
	    // listFeatures.addInterval(new IntegerValueHandler(node
	    // .getChildText(FileStreamerTags.NAME), node
	    // .getChildText(FileStreamerTags.ANNOTATION)));
	}

	return listFeatures.iterator();
    }

    @SuppressWarnings("unused")
    private final Iterator<Follower> readFollowersXMLTree(final Element root) {
	final List<Follower> listFollowers;
	DefaultFollower follower;

	listFollowers = new ArrayList<Follower>(root.getChildren().size());
	for (final Element itemNode : root.getChildren()) {
	    follower = new DefaultFollower();

	    follower.setFile(itemNode.getAttributeValue(FileLabels.FILE));
	    follower.setJob(itemNode.getAttributeValue(FileLabels.JOB));

	    listFollowers.add(follower);
	}

	return listFollowers.iterator();
    }

    @SuppressWarnings("unused")
    private final Iterator<HorseCharacter> readHorsesXMLTree(final Element root) {
	final List<HorseCharacter> listHorse;
	DefaultHorseCharacter horse;

	listHorse = new ArrayList<HorseCharacter>(root.getChildren().size());
	for (final Element horseNode : root.getChildren()) {
	    // TODO
	    // horse = new DefaultHorseCharacter();

	    // horse.setName(horseNode.getAttributeValue(FileStreamerTags.NAME));
	    // horse.setHorseType(horseNode.getAttributeValue(FileLabels.RACE));
	    // horse.setFlags(XMLUtils.readBooleanXMLTree(horseNode
	    // .getChild(FileLabels.FLAGS)));

	    // listHorse.add(horse);
	}

	return listHorse.iterator();
    }

    @SuppressWarnings("unused")
    private final Iterator<PendragonItem> readItemsXMLTree(final Element root) {
	final List<PendragonItem> listItems;

	listItems = new ArrayList<PendragonItem>(root.getChildren().size());
	for (final Element itemNode : root.getChildren()) {
	    // TODO
	    // listItems.add(PersistenceFactory.getItemService().readItemXMLNode(
	    // itemNode, new DefaultPendragonItem()));
	}

	return listItems.iterator();
    }

    @SuppressWarnings("unused")
    private final Iterator<Wife> readWivesXMLTree(final Element root) {
	final List<Wife> listWives;
	List<Child> listChildren;
	Element children;
	DefaultWife wife;
	Child child;

	listWives = new ArrayList<Wife>(root.getChildren().size());
	for (final Element wifeNode : root.getChildren()) {
	    wife = new DefaultWife();

	    wife.setFile(wifeNode.getAttributeValue(FileLabels.FILE));
	    // TODO
	    // wife.getYearWed().setValue(
	    // XMLUtils.readIntegerValueHandlerXMLNode(
	    // wifeNode.getChild(FileLabels.YEAR_WEDDING),
	    // new IntegerValueHandler()).getStoredValue());

	    children = wifeNode.getChild(FileLabels.CHILDREN);

	    listChildren = new ArrayList<Child>(children.getChildren().size());
	    for (final Element childNode : children.getChildren()) {
		// TODO
		// child = new Child();

		// child.setChildName(childNode
		// .getAttributeValue(FileStreamerTags.NAME));
		// child.setGender(Gender.valueOf(childNode
		// .getChildText(FileLabels.GENDER)));
		// child.getYearBorn().setValue(
		// XMLUtils.readIntegerValueHandlerXMLNode(
		// childNode.getChild(FileLabels.YEAR_BORN),
		// new IntegerValueHandler()).getStoredValue());
		// child.getYearDeath().setValue(
		// XMLUtils.readIntegerValueHandlerXMLNode(
		// childNode.getChild(FileLabels.YEAR_DEATH),
		// new IntegerValueHandler()).getStoredValue());

		// listChildren.add(child);
	    }
	    wife.setChildren(listChildren);

	    listWives.add(wife);
	}

	return listWives.iterator();
    }

}
