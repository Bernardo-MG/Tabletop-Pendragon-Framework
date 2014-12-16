package com.wandrell.tabletop.business.util.parser.xml.pendragon.input;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.business.conf.pendragon.FileToken;
import com.wandrell.tabletop.business.model.interval.IntervalTable;
import com.wandrell.tabletop.business.model.pendragon.chargen.AdditionalBelongings;
import com.wandrell.util.parser.xml.input.JDOMDocumentInputProcessor;

public class AdditionalBelongingsDocumentInputProcessor implements
        JDOMDocumentInputProcessor<IntervalTable<AdditionalBelongings>> {

    public AdditionalBelongingsDocumentInputProcessor() {
        super();
    }

    @SuppressWarnings("unused")
    @Override
    public final IntervalTable<AdditionalBelongings>
            process(final Document doc) {
        final IntervalTable<AdditionalBelongings> holder;
        final Element intervals;
        final Element root;

        root = doc.getRootElement();
        // TODO
        // holder = new DefaultRollTable<AdditionalBelongings>(
        // root.getAttributeValue(FileStreamerTags.NAME));

        // Acquires the different sections
        intervals = root.getChild(FileToken.INTERVALS);

        // Intervals and results
        // TODO
        // readIntervalsXMLTree(intervals, holder);

        return null;
    }

    @SuppressWarnings("unused")
    private final void readIntervalsXMLTree(final Element root,
            final IntervalTable<AdditionalBelongings> holder) {
        Element belongings;
        // DefaultAdditionalBelongings items;

        for (final Element node : root.getChildren()) {
            // items = new DefaultAdditionalBelongings();

            // belongings = node.getChild(FileToken.BELONGINGS);

            // readItemsXMLTree(belongings, items);

            // TODO
            // holder.put((ContrastInterval<Integer>) XMLUtils
            // .readIntegerIntervalXMLNode(node), items);
        }

    }

    private final void readItemsXMLTree(final Element root,
            final AdditionalBelongings holder) {
        final Element flags, horses, equipment, pets, reroll, rerolls, weapons, shields, money, name;

        flags = root.getChild(FileToken.FLAGS);
        horses = root.getChild(FileToken.HORSES);
        equipment = root.getChild(FileToken.ITEMS);
        weapons = root.getChild(FileToken.WEAPONS);
        shields = root.getChild(FileToken.SHIELDS);
        reroll = root.getChild(FileToken.REROLL);
        pets = root.getChild(FileToken.PETS);
        money = root.getChild(FileToken.MONEY);
        // name = root.getChild(FileStreamerTags.NAME);

        // Name
        // if (name != null) {
        // holder.setMoneyName(name.getText());
        // }

        // Flags
        if (flags != null) {
            if (flags.getChildren().size() > 0) {
                // holder.setHasToChoose(flags.getChildren().get(0)
                // .getAttributeValue(FileStreamerTags.VALUE)
                // .equalsIgnoreCase("true"));
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
            rerolls = reroll.getChild(FileToken.REROLLS_LIST);
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
