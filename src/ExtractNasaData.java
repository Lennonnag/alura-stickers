import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtractNasaData implements ContentExtractor {
    public List<Content> extractContent(String json) {
        // - Extrair somente os dados interessantes: Titulo, Poster e Classificação.
        var parser = new JsonParser();
        List<Map<String, String>> attributeList = parser.parse(json);

        List<Content> contents = new ArrayList<>();

        // popular a lista de conteudos;

        for (Map<String, String> attribute : attributeList) {
            String title = attribute.get("title");
            String urlImage = attribute.get("url");

            var content = new Content(title, urlImage);
            contents.add(content);
        }
        return contents;
    }
}
