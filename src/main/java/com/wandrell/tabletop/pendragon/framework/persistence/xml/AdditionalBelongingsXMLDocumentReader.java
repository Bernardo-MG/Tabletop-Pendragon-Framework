package com.wandrell.tabletop.pendragon.framework.persistence.xml;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.conf.FileStreamerTags;
import com.wandrell.tabletop.dice.DefaultRollTable;
import com.wandrell.tabletop.dice.RollTable;
import com.wandrell.tabletop.pendragon.framework.conf.FileLabels;
import com.wandrell.tabletop.pendragon.inventory.AdditionalBelongings;
import com.wandrell.tabletop.pendragon.inventory.DefaultAdditionalBelongings;
import com.wandrell.util.file.api.xml.XMLDocumentReader;

public class AdditionalBelongingsXMLDocumentReader implements
	XMLDocumentReader<RollTable<AdditionalBelongings>> {

    public AdditionalBelongingsXMLDocumentReader() {
	super();
    }

    @SuppressWarnings("unused")
    @Override
    public final RollTable<AdditionalBelongings> getValue(final Document doc) {
	final RollTable<AdditionalBelongings> holder;
	final Element intervals;
	final Element root;

	root = doc.getRootElement();
	// TODO
	holder = new DefaultRollTable<AdditionalBelongings>(
		root.getAttributeValue(FileStreamerTags.NAME));

	// Acquires the different sections
	intervals = root.getChild(FileLabels.INTERVALS);

	// Intervals and results
	// TODO
	// readIntervalsXMLTree(intervals, holder);

	return holder;
    }

    @SuppressWarnings("unused")
    private final void readIntervalsXMLTree(final Element root,
	    final RollTable<AdditionalBelongings> holder) {
	Element belongings;
	DefaultAdditionalBelongings items;

	for (final Element node : root.getChildren()) {
	    items = new DefaultAdditionalBelongings();

	    belongings = node.getChild(FileLabels.BELONGINGS);

	    readItemsXMLTree(belongings, items);

	    // TODO
	    // holder.put((ContrastInterval<Integer>) XMLUtils
	    // .readIntegerIntervalXMLNode(node), items);
	}

    }

    private final void readItemsXMLTree(final Element root,
	    final DefaultAdditionalBelongings holder) {
	final Element flags, horses, equipment, pets, reroll, rerolls, weapons, shields, money, name;

	flags = root.getChild(FileLabels.FLAGS);
	horses = root.getChild(FileLabels.HORSES);
	equipment = root.getChild(FileLabels.ITEMS);
	weapons = root.getChild(FileLabels.WEAPONS);
	shields = root.getChild(FileLabels.SHIELDS);
	reroll = root.getChild(FileLabels.REROLL);
	pets = root.getChild(FileLabels.PETS);
	money = root.getChild(FileLabels.MONEY);
	name = root.getChild(FileStreamerTags.NAME);

	// Name
	if (name != null) {
	    holder.setMoneyName(name.getText());
	}

	// Flags
	if (flags != null) {
	    if (flags.getChildren().size() > 0) {
		holder.setHasToChoose(flags.getChildren().get(0)
			.getAttributeValue(FileStreamerTags.VALUE)
			.equalsIgnoreCase("true"));
	    }
	}

	// List of horses
	if (horses != null) {
	    // TODO
	    // holder.setHorsesFiles(XMLUtils.readStringsListXMLTree(horses));
	}

	// List of pets
	if (pets != null) {
	    // TODO
	    // holder.setPetsFiles(XMLUtils.readStringsListXMLTree(pets));
	}

	// List of equipment
	if (equipment != null) {
	    // TODO
	    // holder.setEquippableItemsFiles(XMLUtils
	    // .readStringsListXMLTree(equipment));
	}

	// List of weapons
	if (weapons != null) {
	    // holder.setWeaponsFiles(XMLUtils.readStringsListXMLTree(weapons));
	}

	// List of shields
	if (shields != null) {
	    // holder.setWeaponsFiles(XMLUtils.readStringsListXMLTree(shields));
	}

	// Re-roll
	if (reroll != null) {
	    rerolls = reroll.getChild(FileLabels.REROLLS_LIST);
	    if (rerolls != null) {
		// TODO
		// holder.setReroll(reroll
		// .getAttributeValue(FileLabels.ROLL_TABLE), XMLUtils
		// .readIntegerValueHandlerXMLTree(rerolls,
		// new IntegerValueHandler()));
	    }
	}

	// Money
	if (money != null) {
	    // TODO
	    // PersistenceFactory.getItemService().readMoneyXMLNode(money,
	    // holder.getMoneyData());
	}

    }

}
