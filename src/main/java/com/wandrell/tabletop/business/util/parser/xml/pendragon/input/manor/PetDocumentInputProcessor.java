package com.wandrell.tabletop.business.util.parser.xml.pendragon.input.manor;

import java.util.LinkedHashMap;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;

import com.wandrell.tabletop.business.conf.pendragon.ModelXMLConf;
import com.wandrell.tabletop.business.model.interval.DefaultInterval;
import com.wandrell.tabletop.business.model.interval.DefaultIntervalTable;
import com.wandrell.tabletop.business.model.interval.Interval;
import com.wandrell.tabletop.business.model.interval.IntervalTable;
import com.wandrell.tabletop.business.model.pendragon.manor.AnimalYearResult;
import com.wandrell.tabletop.business.model.pendragon.manor.Pet;
import com.wandrell.tabletop.business.service.pendragon.ModelService;
import com.wandrell.util.parser.xml.input.JDOMDocumentInputProcessor;

public class PetDocumentInputProcessor implements
        JDOMDocumentInputProcessor<Pet> {

    private final ModelService modelService;

    public PetDocumentInputProcessor(final ModelService service) {
        super();

        modelService = service;
    }

    @Override
    public final Pet process(final Document doc) {
        final Element root;
        final Element resultsNode;
        final String name;
        final IntervalTable<AnimalYearResult> results;
        final Map<Interval, AnimalYearResult> intervals;
        AnimalYearResult result;
        Element puppyNode;
        Element diesNode;
        Element moneyNode;
        Element intervalNode;
        String puppyName;
        String description;
        Integer lowerLimit;
        Integer upperLimit;
        Integer libra;
        Integer denarii;
        Boolean dies;

        root = doc.getRootElement();

        name = root.getChildText(ModelXMLConf.NAME);

        resultsNode = root.getChild(ModelXMLConf.YEAR_RESULTS_TABLE);
        intervals = new LinkedHashMap<>();
        for (final Element resultNode : resultsNode.getChildren()) {
            description = resultNode.getChildText(ModelXMLConf.DESCRIPTION);

            intervalNode = resultNode.getChild(ModelXMLConf.INTERVAL);
            lowerLimit = Integer.parseInt(intervalNode
                    .getChildText(ModelXMLConf.LOWER_LIMIT));
            upperLimit = Integer.parseInt(intervalNode
                    .getChildText(ModelXMLConf.UPPER_LIMIT));

            puppyNode = resultNode.getChild(ModelXMLConf.PUPPY_NAME);
            if (puppyNode == null) {
                puppyName = "";
            } else {
                puppyName = puppyNode.getText();
            }

            diesNode = resultNode.getChild(ModelXMLConf.DIES);
            if (diesNode == null) {
                dies = false;
            } else {
                dies = Boolean.valueOf(diesNode.getText());
            }

            moneyNode = resultNode.getChild(ModelXMLConf.MONEY);
            if (moneyNode == null) {
                libra = 0;
                denarii = 0;
            } else {
                libra = Integer.valueOf(moneyNode
                        .getChildText(ModelXMLConf.LIBRA));
                denarii = Integer.valueOf(moneyNode
                        .getChildText(ModelXMLConf.DENARII));
            }

            result = getModelService().getAnimalYearResult(description,
                    puppyName, dies, libra, denarii);

            intervals.put(new DefaultInterval(lowerLimit, upperLimit), result);
        }
        results = new DefaultIntervalTable<AnimalYearResult>(intervals);

        return getModelService().getPet(name, results);
    }

    private final ModelService getModelService() {
        return modelService;
    }

}
