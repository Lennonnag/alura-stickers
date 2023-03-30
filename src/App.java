import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        // - Fazer uma conexão HTTP e buscar os TOP 250 filmes

        //String url = "https://api.nasa.gov/planetary/apod?api_key=lvf7bYGO77QBamUaxMwSFifUiEwh6wwTv9Ewiw1d&start_date=2022-06-12&end_date=2022-06-14";
       // ContentExtractor extract = new ExtractNasaData();
        
        String url = "https://imdb-api.com/en/API/Top250Movies/k_e05fralq";
        ContentExtractor extract = new ExtractImdbData();

        var http = new ClienteHttp();
        String json = http.dataSeek(url);

        List<Content> contents = extract.extractContent(json);

        var maker = new StickerMaker();

        for (int index = 0; index < 3; index++) {

            Content content = contents.get(index);

            InputStream inputStream = new URL(content.getUrlImage()).openStream();
            String fileName = "saida/" + content.getTitle() + ".png";

            maker.create(inputStream, fileName);

            System.out.println(content.getTitle());
            System.out.println();
        }

    }
}
