import java.util.List;
import java.util.Map;

public class NasaContentExtractor implements ContentExtractor {
    public List<Content> extractContent(String json) {
        
        // Filtrar dados que nos interessam (título, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listOfAttributes = parser.parse(json);

        return listOfAttributes.stream()
            .map(attributes -> new Content(attributes.get("title").replace(":", "-"), attributes.get("url")))
            .toList();
/*             .map(attributes -> {
                String title = attributes.get("title");
                if(title.contains(":")) {
                    title = title.replace(":", "-");
                }
                String urlImage = attributes.get("url");
                var content = new Content(title, urlImage);
                return content;
            })
            .toList(); */
    }    
}
