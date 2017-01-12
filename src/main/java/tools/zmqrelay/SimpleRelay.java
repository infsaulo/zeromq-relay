package tools.zmqrelay;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;

import org.zeromq.ZMQ;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import tools.zmqrelay.adapters.ZmqMsgRelayAdapter;

public final class SimpleRelay {

  private static final Logger LOGGER = Logger.getLogger(SimpleRelay.class.getName());

  private SimpleRelay() {

  }

  public static void main(String[] args) {

    try {

      WebSocket
          webSocket =
          new WebSocketFactory().createSocket(System.getProperty("WSURL"), Integer
              .parseInt(System.getProperty("WSCONNTIMEOUTMILIS")));

      ZmqMsgRelayAdapter
          zmqAdapter =
          new ZmqMsgRelayAdapter(System.getProperty("ZMQPUBURL"), ZMQ.PUB);

      webSocket.addListener(zmqAdapter).connect();
      webSocket.sendText(System.getProperty("WSSUBMSG"));

      while (true) {

        continue;
      }
    } catch (IOException | WebSocketException e) {

      LOGGER.log(Level.SEVERE, e.toString(), e);
    }
  }
}

