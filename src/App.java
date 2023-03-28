import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        // Realizar conexão HTTP e buscar filmes
        //String testKey = System.getenv("testKey");
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // Filtrar dados que nos interessam (título, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        //System.out.println(listaDeFilmes.size());
        //System.out.println(listaDeFilmes.get(0));

        // Exibir e manipular dados
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println("\u001b[44m\u001b[1mTítulo:\u001b[m " + filme.get("title"));
            System.out.println("\u001b[34m\u001b[1mLink do Pôster:\u001b[m " + filme.get("image"));
            double classification = Double.parseDouble(filme.get("imDbRating"));
            int starNumber = (int) classification;
            for(int n = 1; n <= starNumber; n++) {
                System.out.print("⭐");
            }
            System.out.println("\n");
        }

    }
}
