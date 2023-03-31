import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class MyHttpClient {

    public String searchData(String url) {

        try {
            URI endereco = URI.create(url);
            var client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            String body = response.body();
            return body;

        } catch(IOException | InterruptedException ex) {
            //throw new RuntimeException(ex);
            throw new MyHttpClientException("Erro ao consultar a URL!");
        }
        
    }
}
