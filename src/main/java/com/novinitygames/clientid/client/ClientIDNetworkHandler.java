package com.novinitygames.clientid.client;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public class ClientIDNetworkHandler {
    public static final Identifier MODCHECK_CHANNEL = Identifier.of("clientid", "modcheck");
    public static final Identifier MODLIST_CHANNEL = Identifier.of("clientid", "modlist");
    public static final Identifier PACKLIST_CHANNEL = Identifier.of("clientid", "packlist");
    public static final Identifier CLIENTVERSION_CHANNEL = Identifier.of("clientid", "clientversion");

    public record ModCheckPayload(String value) implements CustomPayload {
        public static final CustomPayload.Id<ModCheckPayload> ID = new CustomPayload.Id<>(MODCHECK_CHANNEL);
        public static final PacketCodec<PacketByteBuf, ModCheckPayload> CODEC = PacketCodec.of(
            (ModCheckPayload payload, PacketByteBuf buf) -> buf.writeString(payload.value()),
            (PacketByteBuf buf) -> new ModCheckPayload(buf.readString())
        );

        @Override
        public CustomPayload.Id<? extends CustomPayload> getId() {
            return ID;
        }
    }

    public record ModListPayload(String value) implements CustomPayload {
        public static final CustomPayload.Id<ModListPayload> ID = new CustomPayload.Id<>(MODLIST_CHANNEL);
        public static final PacketCodec<PacketByteBuf, ModListPayload> CODEC = PacketCodec.of(
            (ModListPayload payload, PacketByteBuf buf) -> buf.writeString(payload.value()),
            (PacketByteBuf buf) -> new ModListPayload(buf.readString())
        );

        @Override
        public CustomPayload.Id<? extends CustomPayload> getId() {
            return ID;
        }
    }

    public record PackListPayload(String value) implements CustomPayload {
        public static final CustomPayload.Id<PackListPayload> ID = new CustomPayload.Id<>(PACKLIST_CHANNEL);
        public static final PacketCodec<PacketByteBuf, PackListPayload> CODEC = PacketCodec.of(
            (PackListPayload payload, PacketByteBuf buf) -> buf.writeString(payload.value()),
            (PacketByteBuf buf) -> new PackListPayload(buf.readString())
        );

        @Override
        public CustomPayload.Id<? extends CustomPayload> getId() {
            return ID;
        }
    }

    public record ClientVersionPayload(String value) implements CustomPayload {
        public static final CustomPayload.Id<ClientVersionPayload> ID = new CustomPayload.Id<>(CLIENTVERSION_CHANNEL);
        public static final PacketCodec<PacketByteBuf, ClientVersionPayload> CODEC = PacketCodec.of(
            (ClientVersionPayload payload, PacketByteBuf buf) -> buf.writeString(payload.value()),
            (PacketByteBuf buf) -> new ClientVersionPayload(buf.readString())
        );

        @Override
        public CustomPayload.Id<? extends CustomPayload> getId() {
            return ID;
        }
    }
}
