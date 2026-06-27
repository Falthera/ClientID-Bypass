package com.novinitygames.clientid.client;

import com.novinitygames.clientid.client.records.ChartsS2CPayload;
import com.novinitygames.clientid.client.records.ModCheckC2SPayload;
import com.novinitygames.clientid.client.records.ModListC2SPayload;
import com.novinitygames.clientid.client.records.PackListC2SPayload;
import com.novinitygames.clientid.client.records.VersionC2SPayload;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class ClientIDBypassMod implements ClientModInitializer {
    public static Boolean isConnectedToServer = false;
    public static Boolean pieChartDisabled = false;

    @Override
    public void onInitializeClient() {
        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
            isConnectedToServer = true;
            sendBypassPackets();
        });

        ClientPlayConnectionEvents.DISCONNECT.register((handler, sender) -> {
            isConnectedToServer = false;
            pieChartDisabled = false;
        });

        ClientPlayNetworking.registerGlobalReceiver(ChartsS2CPayload.ID, (payload, ctx) -> {
            pieChartDisabled = payload.val();
        });
    }

    private static void sendBypassPackets() {
        ClientPlayNetworking.send(new ModCheckC2SPayload("valid_checksum"));
        ClientPlayNetworking.send(new ModListC2SPayload("clientid,fabric-api,fabricloader,sponge-mixin,mixinextras"));
        ClientPlayNetworking.send(new PackListC2SPayload(""));
        ClientPlayNetworking.send(new VersionC2SPayload("1.1.7"));
    }
}
