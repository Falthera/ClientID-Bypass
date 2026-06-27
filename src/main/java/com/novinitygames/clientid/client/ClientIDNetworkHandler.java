package com.novinitygames.clientid.client;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.network.PacketByteBuf;

public class ClientIDNetworkHandler {
    public static String MODCHECK_CHANNEL = "clientid:modcheck";
    public static String MODLIST_CHANNEL = "clientid:modlist";
    public static String PACKLIST_CHANNEL = "clientid:packlist";
    public static String CLIENTVERSION_CHANNEL = "clientid:clientversion";

    public static PacketByteBuf createModCheckPacket() {
        return createMessage("valid_checksum");
    }

    public static PacketByteBuf createModListPacket() {
        return createMessage("clientid,sodium,lithium,phosphor,iris");
    }

    public static PacketByteBuf createPackListPacket() {
        return createMessage("");
    }

    public static PacketByteBuf createClientVersionPacket() {
        return createMessage("1.1.7");
    }

    private static PacketByteBuf createMessage(String message) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeString(message);
        return buf;
    }
}