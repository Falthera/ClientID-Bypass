package com.novinitygames.clientid.client;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientLoginNetworking;

import java.util.concurrent.CompletableFuture;
import java.util.Optional;

public class ClientIDBypassMod implements ModInitializer {
    public static final String MODCHECK_CHANNEL = "clientid:modcheck";
    public static final String MODLIST_CHANNEL = "clientid:modlist";
    public static final String PACKLIST_CHANNEL = "clientid:packlist";
    public static final String CLIENTVERSION_CHANNEL = "clientid:clientversion";

    @Override
    public void onInitialize() {
        ClientLoginNetworking.registerLoginQuery(MODCHECK_CHANNEL, (request) -> CompletableFuture.completedFuture(Optional.of(ClientIDNetworkHandler.createModCheckPacket())));
        ClientLoginNetworking.registerLoginQuery(MODLIST_CHANNEL, (request) -> CompletableFuture.completedFuture(Optional.of(ClientIDNetworkHandler.createModListPacket())));
        ClientLoginNetworking.registerLoginQuery(PACKLIST_CHANNEL, (request) -> CompletableFuture.completedFuture(Optional.of(ClientIDNetworkHandler.createPackListPacket())));
        ClientLoginNetworking.registerLoginQuery(CLIENTVERSION_CHANNEL, (request) -> CompletableFuture.completedFuture(Optional.of(ClientIDNetworkHandler.createClientVersionPacket())));
    }
}