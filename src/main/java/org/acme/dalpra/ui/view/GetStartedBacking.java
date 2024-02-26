package org.acme.dalpra.ui.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;


@Named
@RequestScoped
public class GetStartedBacking {
    private static final Logger LOG = LoggerFactory.getLogger(GetStartedBacking.class);

    private String data = "Loading...";

    @PostConstruct
    public void init() {
        LOG.debug("init...");
//        onLongRunning();
    }

    public void onLongRunning() {
        LOG.debug("long running...");

        try {
            data = "This Data Loaded";

            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LOG.debug("long running done");
    }

    public String getData() {
        return data;
    }
}