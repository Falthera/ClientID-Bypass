package com.novinitygames.clientid.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;

import com.novinitygames.clientid.client.records.ModCheckC2SPayload;
import com.novinitygames.clientid.client.records.ModListC2SPayload;
import com.novinitygames.clientid.client.records.PackListC2SPayload;
import com.novinitygames.clientid.client.records.VersionC2SPayload;

public class ClientIDBypassMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        PayloadTypeRegistry.playC2S().register(ModCheckC2SPayload.ID, ModCheckC2SPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(ModListC2SPayload.ID, ModListC2SPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(PackListC2SPayload.ID, PackListC2SPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(VersionC2SPayload.ID, VersionC2SPayload.CODEC);

        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
            sendBypassPackets();
        });

        ClientPlayNetworking.registerGlobalReceiver(ModCheckC2SPayload.ID, (payload, ctx) -> {
            ctx.responseSender().sendPacket(new ModCheckC2SPayload("valid_checksum"));
        });
        ClientPlayNetworking.registerGlobalReceiver(ModListC2SPayload.ID, (payload, ctx) -> {
            ctx.responseSender().sendPacket(new ModListC2SPayload("clientid,fabric-api"));
        });
        ClientPlayNetworking.registerGlobalReceiver(PackListC2SPayload.ID, (payload, ctx) -> {
            ctx.responseSender().sendPacket(new PackListC2SPayload(""));
        });
        ClientPlayNetworking.registerGlobalReceiver(VersionC2SPayload.ID, (payload, ctx) -> {
            ctx.responseSender().sendPacket(new VersionC2SPayload("1.1.7"));
        });
    }

    private static void sendBypassPackets() {
        ClientPlayNetworking.send(new ModCheckC2SPayload("valid_checksum"));
        ClientPlayNetworking.send(new ModListC2SPayload("clientid,fabric-api"));
        ClientPlayNetworking.send(new PackListC2SPayload(""));
        ClientPlayNetworking.send(new VersionC2SPayload("1.1.7"));
    }
}