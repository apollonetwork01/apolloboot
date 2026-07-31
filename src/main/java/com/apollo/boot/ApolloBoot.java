package com.apollo.boot;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import org.slf4j.Logger;

import java.io.File;

@Plugin(
    id = "apolloboot",
    name = "ApolloBoot",
    version = "1.0",
    description = "Inicializador de sub-servidores da Rede Apollo",
    authors = {"Apollo"}
)
public class ApolloBoot {

    private final Logger logger;

    @Inject
    public ApolloBoot(Logger logger) {
        this.logger = logger;
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        logger.info("[ApolloBoot] Disparando inicializacao dos sub-servidores via start.sh...");
        try {
            ProcessBuilder pb = new ProcessBuilder("bash", "/home/container/start.sh");
            pb.directory(new File("/home/container"));
            pb.start();
            logger.info("[ApolloBoot] Script start.sh acionado com sucesso!");
        } catch (Exception e) {
            logger.error("[ApolloBoot] Erro ao executar start.sh: " + e.getMessage());
        }
    }
}
