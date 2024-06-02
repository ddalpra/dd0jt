package org.dalpra.acme.view.app;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import lombok.Data;
import lombok.extern.jbosslog.JBossLog;
import org.dalpra.acme.util.VirtualMachine;
import org.omnifaces.util.Faces;

import org.apache.commons.lang3.StringUtils;


/**
 * TechnicalInfo.
 *
 * @author Oleg Varaksin / last modified by Melloware
 * @version $Revision$
 */
@ApplicationScoped
@Named
@Data
@JBossLog
public class TechnicalInfo {

    private String quarkus;
    private String omniFaces;
    private String primeFaces;
    private String primeFacesExt;
    private String facesImpl;
    private String server;

    @PostConstruct
    protected void initialize() {
        quarkus = "Quarkus: " + StringUtils.defaultIfEmpty(
                io.quarkus.bootstrap.runner.QuarkusEntryPoint.class.getPackage().getImplementationVersion(), "???");
        omniFaces = "OmniFaces: " + StringUtils.defaultIfEmpty(
                org.omnifaces.util.Faces.class.getPackage().getImplementationVersion(), "???");
        // PF version
        primeFaces = "PrimeFaces: " + StringUtils.defaultIfEmpty(
                org.primefaces.util.Constants.class.getPackage().getImplementationVersion(), "???");
        primeFacesExt = "PrimeFaces Extensions: " + StringUtils
                .defaultIfEmpty(org.primefaces.extensions.util.Constants.class.getPackage()
                        .getImplementationVersion(), "???");
        facesImpl = StringUtils.removeIgnoreCase(StringUtils.removeIgnoreCase(Faces.getImplInfo(), "Core"), "Impl");
        server = Faces.getServerInfo();
    }

    public String getMemory() {
        return VirtualMachine.getMemoryStatisticsAsString();
    }

}