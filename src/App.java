import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        API api = API.LANGUAGE;

        String url = api.getUrl();
        ContentExtractor extractor = api.getExtractor();

        var http = new MyHttpClient();
        String json = http.searchData(url);

        // Exibir e manipular dados
        List<Content> contents = extractor.extractContent(json);

        var outputDir = new File("output/");
        outputDir.mkdir();

        var geradora = new GeradoraDeFigurinhas();

        for (Content content : contents) {

            InputStream InputStream = new URL(content.urlImage()).openStream();
            String fileName = "output/" + content.title() + ".png";
            geradora.cria(InputStream, fileName);
            
            System.out.println("\u001b[44m\u001b[1mTítulo:\u001b[m " + content.title());
            System.out.println("\u001b[34m\u001b[1mLink do Pôster:\u001b[m " + content.urlImage());
/*             double classification = Double.parseDouble(content.get("imDbRating"));
            int starNumber = (int) classification;
            for(int n = 1; n <= starNumber; n++) {
                System.out.print("⭐");
            } */
            System.out.println("\n");
        }
    }
}
