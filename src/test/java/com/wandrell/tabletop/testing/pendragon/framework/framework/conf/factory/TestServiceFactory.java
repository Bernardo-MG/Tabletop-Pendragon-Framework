package com.wandrell.tabletop.testing.pendragon.framework.framework.conf.factory;

import com.wandrell.tabletop.business.service.pendragon.ModelService;
import com.wandrell.tabletop.testing.pendragon.framework.framework.service.TestModelService;

public final class TestServiceFactory {

    private static final TestServiceFactory instance = new TestServiceFactory();

    public static final TestServiceFactory getInstance() {
        return instance;
    }

    private TestServiceFactory() {
        super();
    }

    public final ModelService getModelService() {
        return new TestModelService();
    }

}
