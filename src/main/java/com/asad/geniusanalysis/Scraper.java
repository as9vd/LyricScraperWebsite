package com.asad.geniusanalysis;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.annotation.ComponentScan;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

// Solve the problems:
// 3. Dealing with missing lyric pages on Genius.
// 5. In future, will need to check if the artist said this or if it was another artist.

@ComponentScan("src/SpringStuff")
public class Scraper {
    public static void overallAnalysis(String link) throws IOException, JSONException {
        String path = createAlbumJSON(link);
//        albumAnalyser("Artists and Songs/" + path);
//        sortFolders();
    }

    public static void sortFolders() throws IOException {
        // Purpose of this function: to put artists with multiple folders in one folder at the end.
        File folder = new File("Artists and Songs");

        folder.listFiles();

        // Holds the indices of the duplicates.
        LinkedHashMap<String, Set<Integer>> duplicates = new LinkedHashMap<>();
        for (int i = 0; i < folder.listFiles().length; i++) {
            String artist1 = folder.listFiles()[i].getName().split(" - ")[0];

            for (int j = i + 1; j < folder.listFiles().length; j++) {
                String artist2 = folder.listFiles()[j].getName().split(" - ")[0];

                // We store indices for later folder manipulation.
                if (artist1.equals(artist2)) {
                    if (!(duplicates.containsKey(artist1))) {
                        duplicates.put(artist1, new HashSet<>(Arrays.asList(i, j)));
                    } else {
                        duplicates.get(artist1).add(j);
                    }
                }
            }
        }

        Set<String> artists = duplicates.keySet();
        List<String> artistList = new ArrayList<>(artists);

        List<File> albumList = Arrays.asList(folder.listFiles());

        for (int i = albumList.size() - 1; i >= 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                String artist1 = albumList.get(i).getName().split(" - ")[0];
                String artist2 = albumList.get(j).getName().split(" - ")[0];

                // If the two artists are the same and there already exists a folder for them (e.g. if there are more than 2 occurrences of the artist).
                if (artist1.equals(artist2) && new File("Collection/" + artist1).exists()) {
                    // In the future, we might have to check if the two albums are the same, but since we have the overallAnalysis() function right now, it's not necessary.

                    albumList.get(j).renameTo(new File("Collection/" + artist1 + "/" + albumList.get(j).getName()));
                } else if (artist1.equals(artist2) && !(new File("Collection/" + artist1).exists())) {
                    new File("Collection/" + artist1).mkdirs();
                    albumList.get(i).renameTo(new File("Collection/" + artist1 + "/" + albumList.get(i).getName()));
                    albumList.get(j).renameTo(new File("Collection/" + artist1 + "/" + albumList.get(j).getName()));
                }
            }
        }

        // Sorting out the remaining artists without multiple albums in storage.
        List<File> updatedAlbumList = Arrays.asList(folder.listFiles());
        for (int i = 0; i < updatedAlbumList.size(); i++) {
            String artist = updatedAlbumList.get(i).getName().split(" - ")[0];

            // First, check if the artist already has a directory from a past sortFolders() call.
            if (new File("Collection/" + artist).exists()) {
                // If the album is a duplicate.
                if (new File("Collection/" + artist + "/" + updatedAlbumList.get(i).getName()).exists()) {
                    File dupeFile = new File("Artists and Songs/" + updatedAlbumList.get(i).getName());
                    deleteDirectory(dupeFile);
                }

                updatedAlbumList.get(i).renameTo(new File("Collection/" + artist + "/" + updatedAlbumList.get(i).getName()));
            } else {
                new File("Collection/" + artist).mkdirs();
                updatedAlbumList.get(i).renameTo(new File("Collection/" + artist + "/" + updatedAlbumList.get(i).getName()));
            }
        }
    }

    public static boolean deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        return directoryToBeDeleted.delete();
    }
    
    public static void albumAnalyser(String path) throws IOException {
        Map<String, Double> wordMap = new TreeMap<>();

        Gson gson = new Gson();

        File songFolder = new File(path + "/Songs");
        int numSongs = (songFolder.listFiles().length);

        for (int i = 0; i < numSongs; i++) {
            Reader reader = Files.newBufferedReader(Paths.get(songFolder.listFiles()[i].toString()));
            Map<?, ?> map = gson.fromJson(reader, Map.class);

            // Checking if the song has lyrics.
            if (map.values().toString().replaceAll(
                    "[^a-zA-Z0-9]", "").isEmpty()) {
                continue;
            }

            String simplifiedJson = map.values().toString().replaceAll("\\[", "").replaceAll("\\]","") // Removing all square brackets
                    .replaceAll(",", "").substring(1); simplifiedJson = simplifiedJson.substring(0, simplifiedJson.length() - 1);

            for (int j = 0; j < simplifiedJson.split("} \\{").length; j++) {
                String word = simplifiedJson.split("} \\{")[j].split(" ")[1].split("=")[1];
                double count = Double.parseDouble(simplifiedJson.split("} \\{")[j].split(" ")[0].split("=")[1]);

                if (wordMap.containsKey(word)) {
                    double wordCount = wordMap.get(word);

                    wordMap.put(word, count + wordCount);
                } else {
                    wordMap.put(word, count);
                }
            }

            reader.close();
        }

        Map<String, Double> sortedMap = wordMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));


        String updatedMapText = sortedMap.toString().replaceAll("\\{", "").replaceAll("\\}","").replaceAll(",", "");

        StringBuilder lines = new StringBuilder();

        new File(path + "/WordCount.txt");

        for (int i = 0; i < updatedMapText.split(" ").length; i++) {
            lines.append(updatedMapText.split(" ")[i] + "\n");
        }

        try (PrintWriter out = new PrintWriter(path + "/WordCount.txt")) {
            out.println(lines);
        }
    }

    public static String createSongJSON(String link, String path) throws IOException, JSONException {
        ArrayList<String> wordsUsed = new ArrayList<>();
        HashMap<String, Integer> wordsUsedCounter = new HashMap<>();
        Document doc = Jsoup.connect(link).userAgent("Chrome").get();

        String title;

        // Dealing with the weird case of Fade from TLOP and Angel from BBTM.
        if (doc.title().contains(" Lyrics - ")) {
            title = doc.title().split(" Lyrics - ")[0];
        } else {
            title = doc.title().split("– ")[1].split("Genius Lyrics")[0].split(" Lyrics")[0].replaceAll("\\W+", "");;
        }

        // There are multiple lyric containers, so unfortunately, there's a bit of weirdness here.
        Elements lyrics = doc.select("#lyrics-root > div.Lyrics__Container-sc-1ynbvzw-6.YYrds");

        boolean debounce = false;
        for (Element text: lyrics) {
            String[] words = text.getAllElements().get(0).text().split(" ");

            for (String word : words) {
                if (word.contains("[") && word.contains("]")) {
                    continue;
                } else if ((word.contains("[")) && !debounce) {
                    debounce = true;
                    continue;
                } else if (!(word.contains("[") || word.contains("]")) && debounce) {
                    continue;
                } else if (word.contains("]")) {
                    debounce = false;
                    continue;
                } else {
                    if (!(StringUtils.isEmpty(word.replaceAll("[^a-zA-Z\\d&']", "")))) {
                        wordsUsed.add(word.replaceAll("[^a-zA-Z\\d&']", "").toLowerCase());
                    }
                }
            }
        }

        for (int i = 0; i < wordsUsed.size(); i++) {
            String word = wordsUsed.get(i);

            if (!(wordsUsedCounter.containsKey(word))) {
                wordsUsedCounter.put(word, 1);
            } else {
                int value = wordsUsedCounter.get(word);

                wordsUsedCounter.put(word, value + 1);
            }
        }

        JSONArray arr = new JSONArray();

        for (Map.Entry<String, Integer> word: wordsUsedCounter.entrySet()) {
            String key = word.getKey();
            Integer value = word.getValue();

            JSONObject comb = new JSONObject().put("word", key).put("count", value);

            arr.put(comb);
        }

        JSONObject butterflyEffect = new JSONObject().put(title, arr);
        String return_val = "";

        try (PrintWriter out = new PrintWriter(path + "/" + title + ".json")) {
            out.println(butterflyEffect.toString(2));
            return_val += butterflyEffect.toString(2);
        }

        return return_val;
    }

    public static String createAlbumJSON(String link) throws IOException, JSONException { // Used STARGAZING to prove the accuracy of this.
        Document doc = Jsoup.connect(link).userAgent("Chrome").get();
        String artistName = doc.title().split("– ")[0];

        final WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);

        final HtmlPage page = webClient.getPage(link);

        final HtmlElement albumCard = page.getFirstByXPath("/html/body/div[1]/main/div[3]/div[3]/div[1]/div[5]");
        final HtmlElement tracks = (albumCard.getFirstByXPath("//*[@id=\"application\"]/main/div[3]/div[3]/div[1]/div[5]/ol"));

        HtmlElement smallerAlbumCard = albumCard.getFirstByXPath("//*[@id=\"application\"]/main/div[3]/div[3]/div[1]/div[5]/div[3]/a");
        String albumName = smallerAlbumCard.getFirstChild().getTextContent();

        StringBuilder trackList = new StringBuilder();

        for (int i = 0; i < tracks.getChildNodes().size(); i++) {
            DomNode linkCard = tracks.getChildNodes().get(i).getFirstChild().getLastChild();

            if (linkCard.hasAttributes()) {
                if (linkCard.getAttributes().item(0).getTextContent().contains("lyrics")) {
                    trackList.append(linkCard.getAttributes().item(0).getTextContent() + "\n");
                }
            } else {
                // Issue here when songs don't have lyrics.
                trackList.append(link + "\n");
            }
        }

        String folderName = artistName + "- " + albumName.replaceAll("\\W+", "");
        new File("Artists and Songs/" + folderName).mkdirs();
        new File("Artists and Songs/" + folderName + "/Songs").mkdirs();

        // Do something here to deal with titles with characters not allowed (e.g. ?, !, etc.).
        String fileName = albumName.replaceAll("\\W+", "") + " Tracklist.txt";

        try (PrintWriter out = new PrintWriter("Artists and Songs/" + folderName + "/" + fileName)) {
            out.println(trackList.toString().replaceAll("([\\n\\r]+\\s*)*$", ""));
        }

        LineIterator it = FileUtils.lineIterator(new File("Artists and Songs/" + folderName + "/" + fileName), "UTF-8");
        try {
            while (it.hasNext()) {
                String line = it.nextLine();

                if (StringUtils.isEmpty(line)) {
                    continue;
                }

                createSongJSON(line, "Artists and Songs/" + folderName + "/Songs");
            }
        } finally {
            it.close();
        }

        return folderName;

    }

}
