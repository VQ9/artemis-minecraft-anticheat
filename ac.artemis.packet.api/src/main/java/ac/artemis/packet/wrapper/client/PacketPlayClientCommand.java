package ac.artemis.packet.wrapper.client;

import ac.artemis.packet.wrapper.PacketClient;
import ac.artemis.packet.wrapper.mc.ClientCommand;

public interface PacketPlayClientCommand extends PacketClient {
    /**
     * @return Returns the client command executed by the client
     */
    ClientCommand getClientCommand();
}
