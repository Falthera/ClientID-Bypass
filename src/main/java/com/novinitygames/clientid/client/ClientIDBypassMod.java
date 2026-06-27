package com.novinitygames.clientid.client;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientLoginNetworking;
import net.minecraft.resources.Identifier;

import java.util.concurrent.CompletableFuture;

public class ClientIDBypassMod implements ModInitializer {
    public static final String MODCHECK_CHANNEL = "clientid:modcheck";
    public static final String MODLIST_CHANNEL = "clientid:modlist";
    public static final String PACKLIST_CHANNEL = "clientid:packlist";
    public static final String CLIENTVERSION_CHANNEL = "clientid:clientversion";

    @Override
    public void onInitialize() {
        ClientLoginNetworking.registerGlobalReceiver(Identifier.of(MODCHECK_CHANNEL), (client, handler, buf, callbacks) -> CompletableFuture.completedFuture(ClientIDNetworkHandler.createModCheckPacket()));
        ClientLoginNetworking.registerGlobalReceiver(Identifier.of(MODLIST_CHANNEL), (client, handler, buf, callbacks) -> CompletableFuture.completedFuture(ClientIDNetworkHandler.createModListPacket()));
        ClientLoginNetworking.registerGlobalReceiver(Identifier.of(PACKLIST_CHANNEL), (client, handler, buf, callbacks) -> CompletableFuture.completedFuture(ClientIDNetworkHandler.createPackListPacket()));
        ClientLoginNetworking.registerGlobalReceiver(Identifier.of(CLIENTVERSION_CHANNEL), (client, handler, buf, callbacks) -> CompletableFuture.completedFuture(ClientIDNetworkHandler.createClientVersionPacket()));
    }
}