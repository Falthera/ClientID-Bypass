package com.novinitygames.clientid.client;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientLoginNetworking;

import java.util.concurrent.CompletableFuture;

public class ClientIDBypassMod implements ModInitializer {
    public static final String MODCHECK_CHANNEL = "clientid:modcheck";
    public static final String MODLIST_CHANNEL = "clientid:modlist";
    public static final String PACKLIST_CHANNEL = "clientid:packlist";
    public static final String CLIENTVERSION_CHANNEL = "clientid:clientversion";

    @Override
    public void onInitialize() {
        ClientLoginNetworking.registerSend(MODCHECK_CHANNEL, (context) -> CompletableFuture.completedFuture(ClientIDNetworkHandler.createModCheckPacket()));
        ClientLoginNetworking.registerSend(MODLIST_CHANNEL, (context) -> CompletableFuture.completedFuture(ClientIDNetworkHandler.createModListPacket()));
        ClientLoginNetworking.registerSend(PACKLIST_CHANNEL, (context) -> CompletableFuture.completedFuture(ClientIDNetworkHandler.createPackListPacket()));
        ClientLoginNetworking.registerSend(CLIENTVERSION_CHANNEL, (context) -> CompletableFuture.completedFuture(ClientIDNetworkHandler.createClientVersionPacket()));
    }
}