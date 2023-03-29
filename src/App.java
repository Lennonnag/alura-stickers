import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        // - Fazer uma conexão HTTP e buscar os TOP 250 filmes

        //String url = "https://api.nasa.gov/planetary/apod?api_key=lvf7bYGO77QBamUaxMwSFifUiEwh6wwTv9Ewiw1d"; // url da api top 250
        String url = "https://imdb-api.com/en/API/Top250Movies/k_e05fralq"; // url da api top 250
        //String url = "https://imdb-api.com/en/API/MostPopularMovies/k_e05fralq"; //
        // url da api mais populares
        URI link = URI.create(url); // converter para uri
        var client = HttpClient.newHttpClient(); //
        var request = HttpRequest.newBuilder(link).GET().build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        // System.out.println(body);

        // - Extrair somente os dados interessantes: Titulo, Poster e Classificação.
        var parser = new JsonParser();
        List<Map<String, String>> listaFilmes = parser.parse(body);
        // System.out.println(listaFilmes.size());
        // System.out.println(listaFilmes.get(0));

        // - Exibir e manipular os dados.
        //for (Map<String, String> filme : listaFilmes) {
        for (int index = 0; index < 10; index++) {            
        
            Map<String, String> filme = listaFilmes.get(index);
            String urlImage = filme.get("image").replaceAll("(@+)(.*).jpg$", "$1.jpg");
                        
            String title = filme.get("title");

            InputStream inputStream = new URL(urlImage).openStream();
            String fileName ="saida/"+ title + ".png";

            var maker = new StickerMaker();
            maker.create(inputStream, fileName);

            System.out.println(title);
            System.out.println();
        }

    }
}
