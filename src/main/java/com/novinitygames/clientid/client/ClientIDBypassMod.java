package com.novinitygames.clientid.client;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientLoginNetworking;
import net.minecraft.resources.ResourceLocation;

import java.util.concurrent.CompletableFuture;

public class ClientIDBypassMod implements ModInitializer {
    public static final String MODCHECK_CHANNEL = "clientid:modcheck";
    public static final String MODLIST_CHANNEL = "clientid:modlist";
    public static final String PACKLIST_CHANNEL = "clientid:packlist";
    public static final String CLIENTVERSION_CHANNEL = "clientid:clientversion";

    @Override
    public void onInitialize() {
        ClientLoginNetworking.registerGlobalReceiver(ResourceLocation.parse(MODCHECK_CHANNEL), (client, handler, buf, callbacks) -> CompletableFuture.completedFuture(ClientIDNetworkHandler.createModCheckPacket()));
        ClientLoginNetworking.registerGlobalReceiver(ResourceLocation.parse(MODLIST_CHANNEL), (client, handler, buf, callbacks) -> CompletableFuture.completedFuture(ClientIDNetworkHandler.createModListPacket()));
        ClientLoginNetworking.registerGlobalReceiver(ResourceLocation.parse(PACKLIST_CHANNEL), (client, handler, buf, callbacks) -> CompletableFuture.completedFuture(ClientIDNetworkHandler.createPackListPacket()));
        ClientLoginNetworking.registerGlobalReceiver(ResourceLocation.parse(CLIENTVERSION_CHANNEL), (client, handler, buf, callbacks) -> CompletableFuture.completedFuture(ClientIDNetworkHandler.createClientVersionPacket()));
    }
}