package cc.ghast.packet.wrapper.packet.play.server;

import ac.artemis.packet.protocol.ProtocolVersion;
import ac.artemis.packet.spigot.protocol.PacketLink;
import ac.artemis.packet.wrapper.server.PacketPlayServerAbilities;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import ac.artemis.packet.spigot.wrappers.GPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import lombok.Getter;

import java.util.Optional;
import java.util.UUID;

@Getter
@PacketLink(PacketPlayServerAbilities.class)
public class GPacketPlayServerAbilities extends GPacket implements PacketPlayServerAbilities, ReadableBuffer {
    public GPacketPlayServerAbilities(UUID player, ProtocolVersion version) {
        super("PacketPlayOutAbilities", player, version);
    }

    private Optional<Boolean> invulnerable;
    private boolean flying;
    private Optional<Boolean> allowedFlight;
    private Optional<Boolean> creativeMode;

    private Optional<Float> flySpeed;
    private Optional<Float> walkSpeed;

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        // These are under getX bit mask, see https://stackoverflow.com/questions/31575691/what-is-a-bitmask-and-a-mask
        byte flags = byteBuf.readByte();

        /*
            Creative Mode -> 0x01
            Flying -> 0x02
            Allowed Flight -> 0x04
            Invulnerable -> 0x08
         ------------------
            For those who don't understand why this works, basically we're reading binary: 0000 0001, 0000 0010
            The '&' symbol merges two bytes and UNIQUELY keeps the common bytes. If we take this into what's being done
            Here, basically we are placing getX 1 on different scales. If the one is here, it's true. Simple yet complex.

        */
        this.flying = (flags & 0x02) > 0;
        if(version.isBelow(ProtocolVersion.V1_15)) {
            this.creativeMode = Optional.of((flags & 0x01) > 0);
            this.allowedFlight = Optional.of((flags & 0x04) > 0);
            this.invulnerable = Optional.of((flags & 0x08) > 0);

            this.flySpeed = Optional.of(byteBuf.readFloat());
            this.walkSpeed = Optional.of(byteBuf.readFloat());
        }
        else {
            //Don't exist on 1.16+ :(
            this.creativeMode = Optional.empty();
            this.allowedFlight = Optional.empty();
            this.invulnerable = Optional.empty();

            this.flySpeed = Optional.empty();
            this.walkSpeed = Optional.empty();
        }
    }
}
