package networking.highlevelapis.websockets;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.WebSocket;

public class WebSocketClient {

    public static void main(String[] args) throws URISyntaxException {
        HttpClient client = HttpClient.newHttpClient();

        client.newWebSocketBuilder().buildAsync(new URI("ws://localhost:8090"), new WebSocket.Listener() {
            public void onOpen(final WebSocket webSocket) {

            }
        }).join();
    }
}
