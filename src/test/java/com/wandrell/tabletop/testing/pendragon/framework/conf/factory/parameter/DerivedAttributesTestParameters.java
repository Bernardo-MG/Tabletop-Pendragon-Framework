package com.wandrell.tabletop.testing.pendragon.framework.conf.factory.parameter;

import java.io.Reader;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import org.jdom2.Document;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.pattern.parser.xml.XMLFileParser;
import com.wandrell.tabletop.testing.pendragon.framework.conf.DerivedAttributeParametersConf;
import com.wandrell.tabletop.testing.pendragon.framework.util.parser.DamageParser;
import com.wandrell.tabletop.testing.pendragon.framework.util.parser.DistinctiveFeaturesParser;
import com.wandrell.tabletop.testing.pendragon.framework.util.parser.HealingRateParser;
import com.wandrell.tabletop.testing.pendragon.framework.util.parser.HitPointsParser;
import com.wandrell.tabletop.testing.pendragon.framework.util.parser.MoveRateParser;
import com.wandrell.tabletop.testing.pendragon.framework.util.parser.UnconciousParser;
import com.wandrell.tabletop.testing.pendragon.framework.util.parser.WeightParser;
import com.wandrell.util.ResourceUtils;

public final class DerivedAttributesTestParameters {

    private static final DerivedAttributesTestParameters instance = new DerivedAttributesTestParameters();

    public static final DerivedAttributesTestParameters getInstance() {
        return instance;
    }

    private static final Iterator<Object[]> getParameters(
            final Collection<Collection<Object>> valuesTable) {
        final Collection<Object[]> result;

        result = new LinkedList<Object[]>();
        for (final Collection<Object> values : valuesTable) {
            result.add(values.toArray());
        }

        return result.iterator();
    }

    private DerivedAttributesTestParameters() {
        super();
    }

    public final Iterator<Object[]> getDamage() throws Exception {
        return getParameters(getDamageValues());
    }

    public final Iterator<Object[]> getDistinctiveFeatures() throws Exception {
        return getParameters(getDistinctiveFeaturesValues());
    }

    public final Iterator<Object[]> getHealingRate() throws Exception {
        return getParameters(getHealingRateValues());
    }

    public final Iterator<Object[]> getHitPoints() throws Exception {
        return getParameters(getHitPointValues());
    }

    public final Iterator<Object[]> getMoveRate() throws Exception {
        return getParameters(getMoveRateValues());
    }

    public final Iterator<Object[]> getUnconcious() throws Exception {
        return getParameters(getUnconciousValues());
    }

    public final Iterator<Object[]> getWeight() throws Exception {
        return getParameters(getWeightValues());
    }

    private final Collection<Collection<Object>> getDamageValues()
            throws Exception {
        final Parser<Reader, Document> parserFile;
        final Parser<Document, Collection<Collection<Object>>> parserParams;

        parserFile = new XMLFileParser();
        parserParams = new DamageParser();

        return parserParams.parse(parserFile.parse(ResourceUtils
                .getClassPathReader(DerivedAttributeParametersConf.DAMAGE)));
    }

    private final Collection<Collection<Object>> getDistinctiveFeaturesValues()
            throws Exception {
        final Parser<Reader, Document> parserFile;
        final Parser<Document, Collection<Collection<Object>>> parserParams;

        parserFile = new XMLFileParser();
        parserParams = new DistinctiveFeaturesParser();

        return parserParams
                .parse(parserFile.parse(ResourceUtils
                        .getClassPathReader(DerivedAttributeParametersConf.DISTINCTIVE_FEATURES)));
    }

    private final Collection<Collection<Object>> getHealingRateValues()
            throws Exception {
        final Parser<Reader, Document> parserFile;
        final Parser<Document, Collection<Collection<Object>>> parserParams;

        parserFile = new XMLFileParser();
        parserParams = new HealingRateParser();

        return parserParams
                .parse(parserFile.parse(ResourceUtils
                        .getClassPathReader(DerivedAttributeParametersConf.HEALING_RATE)));
    }

    private final Collection<Collection<Object>> getHitPointValues()
            throws Exception {
        final Parser<Reader, Document> parserFile;
        final Parser<Document, Collection<Collection<Object>>> parserParams;

        parserFile = new XMLFileParser();
        parserParams = new HitPointsParser();

        return parserParams
                .parse(parserFile.parse(ResourceUtils
                        .getClassPathReader(DerivedAttributeParametersConf.HIT_POINTS)));
    }

    private final Collection<Collection<Object>> getMoveRateValues()
            throws Exception {
        final Parser<Reader, Document> parserFile;
        final Parser<Document, Collection<Collection<Object>>> parserParams;

        parserFile = new XMLFileParser();
        parserParams = new MoveRateParser();

        return parserParams.parse(parserFile.parse(ResourceUtils
                .getClassPathReader(DerivedAttributeParametersConf.MOVE_RATE)));
    }

    private final Collection<Collection<Object>> getUnconciousValues()
            throws Exception {
        final Parser<Reader, Document> parserFile;
        final Parser<Document, Collection<Collection<Object>>> parserParams;

        parserFile = new XMLFileParser();
        parserParams = new UnconciousParser();

        return parserParams
                .parse(parserFile.parse(ResourceUtils
                        .getClassPathReader(DerivedAttributeParametersConf.UNCONCIOUS)));
    }

    private final Collection<Collection<Object>> getWeightValues()
            throws Exception {
        final Parser<Reader, Document> parserFile;
        final Parser<Document, Collection<Collection<Object>>> parserParams;

        parserFile = new XMLFileParser();
        parserParams = new WeightParser();

        return parserParams.parse(parserFile.parse(ResourceUtils
                .getClassPathReader(DerivedAttributeParametersConf.WEIGHT)));
    }

}
