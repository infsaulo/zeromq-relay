package tools.zmqrelay.adapters;

import com.google.api.client.util.Charsets;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;

import java.util.logging.Level;
import java.util.logging.Logger;

import tools.utils.zeromq.ZeroMqPubSubInterface;

public class ZmqMsgRelayAdapter extends WebSocketAdapter {

  private static final Logger LOGGER = Logger.getLogger(ZmqMsgRelayAdapter.class.getName());

  private final ZeroMqPubSubInterface zmqInterface;

  public ZmqMsgRelayAdapter(final String zmqUrl, final int zmqMode) {

    zmqInterface = new ZeroMqPubSubInterface(zmqUrl, zmqMode);
  }

  @Override
  public void onTextMessage(WebSocket webSocket, String message) {

    LOGGER.log(Level.INFO, "Relying msg to zmq: " + message);

    zmqInterface.sendMsg(message.getBytes(Charsets.UTF_8));
  }
}
