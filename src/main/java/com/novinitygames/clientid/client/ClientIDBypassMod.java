package com.novinitygames.clientid.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;

import com.novinitygames.clientid.client.ClientIDNetworkHandler.ModCheckPayload;
import com.novinitygames.clientid.client.ClientIDNetworkHandler.ModListPayload;
import com.novinitygames.clientid.client.ClientIDNetworkHandler.PackListPayload;
import com.novinitygames.clientid.client.ClientIDNetworkHandler.ClientVersionPayload;

public class ClientIDBypassMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        PayloadTypeRegistry.playS2C().register(ModCheckPayload.ID, ModCheckPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(ModListPayload.ID, ModListPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(PackListPayload.ID, PackListPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(ClientVersionPayload.ID, ClientVersionPayload.CODEC);

        PayloadTypeRegistry.playC2S().register(ModCheckPayload.ID, ModCheckPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(ModListPayload.ID, ModListPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(PackListPayload.ID, PackListPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(ClientVersionPayload.ID, ClientVersionPayload.CODEC);

        ClientPlayNetworking.registerGlobalReceiver(ModCheckPayload.ID, (payload, context) -> {
            context.responseSender().sendPacket(ClientPlayNetworking.createC2SPacket(new ModCheckPayload("valid_checksum")));
        });
        ClientPlayNetworking.registerGlobalReceiver(ModListPayload.ID, (payload, context) -> {
            context.responseSender().sendPacket(ClientPlayNetworking.createC2SPacket(new ModListPayload("clientid,fabric-api")));
        });
        ClientPlayNetworking.registerGlobalReceiver(PackListPayload.ID, (payload, context) -> {
            context.responseSender().sendPacket(ClientPlayNetworking.createC2SPacket(new PackListPayload("")));
        });
        ClientPlayNetworking.registerGlobalReceiver(ClientVersionPayload.ID, (payload, context) -> {
            context.responseSender().sendPacket(ClientPlayNetworking.createC2SPacket(new ClientVersionPayload("1.1.7")));
        });

        ClientPlayConnectionEvents.JOIN.register((listener, sender, client) -> {
            ClientPlayNetworking.send(new ModCheckPayload("valid_checksum"));
            ClientPlayNetworking.send(new ModListPayload("clientid,fabric-api"));
            ClientPlayNetworking.send(new PackListPayload(""));
            ClientPlayNetworking.send(new ClientVersionPayload("1.1.7"));
        });
    }
}
