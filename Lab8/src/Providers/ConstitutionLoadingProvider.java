package Providers;

import Models.ArticleModel;
import Models.ChapterModel;
import Models.ConstitutionModel;
import Models.SubChapterModel;

import javax.naming.NoPermissionException;
import java.io.*;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Filip on 2016-11-26.
 */
public class ConstitutionLoadingProvider
{

    public ConstitutionModel LoadConstitution(String filePath)
            throws IOException, NoPermissionException
    {
        ConstitutionModel constitution = new ConstitutionModel();
        File f = new File(filePath);
        if (!f.exists() || f.isDirectory())
            throw new FileNotFoundException();
        if (!f.canRead())
            throw new NoPermissionException();
        String line;
        LinkedList<String> lines = new LinkedList<String>();
        InputStream fis = new FileInputStream(f);
        InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
        BufferedReader br = new BufferedReader(isr);
        while ((line = br.readLine()) != null)
            lines.add(line.trim());

        ArrayList<String> linesArray = new ArrayList<String>(lines);
        // Ladujemy preambułę
        int currentIndex = LoadPreamble(linesArray, constitution);
        // Dopóki nie dojdziemy do końca pliku ładujemy wczytujemy rozdziały
        while (currentIndex != -1 && currentIndex < linesArray.size())
            currentIndex = LoadChapter(linesArray, constitution, currentIndex);
        return constitution;
    }

    private boolean IsLineValid(String line)
    {
        // Sprawdza, czy linia nie jest datą
        try {
            new SimpleDateFormat("yyyy-MM-dd").parse(line);
            return false;
        }
        catch(ParseException ex) {}

        // Sprawdza, czy linia nie jest napisem  ©Kancelaria Sejmu
        if (line.startsWith("©Kancelaria Sejmu"))
            return false;

        // Sprawdza, czy linia nie jest pojedynczym znakiem
        if (line.length() <= 1)
            return false;

        return true;
    }

    private int LoadPreamble(ArrayList<String> lines, ConstitutionModel constitution)
    {
        StringBuilder preamble = new StringBuilder();
        for (int i=0; i<lines.size(); i++)
        {
            if (lines.get(i).startsWith("Rozdział"))
            {
                constitution.Preamble = preamble.toString();
                return i;
            }
            if (IsLineValid(lines.get(i))) {
                preamble.append(lines.get(i));
                if (preamble.charAt(preamble.length() - 1) == '-')
                    preamble.deleteCharAt(preamble.length()-1);
                else
                    preamble.append(' ');
            }
        }
        return -1;
    }

    private int LoadChapter(ArrayList<String> lines, ConstitutionModel constitution, int startIndex)
    {
        // Pobieramy pierwszą poprawną linię i zapisujemy ją jako nagłówek rozdziału (np "Rozdział I")
        while (!IsLineValid(lines.get(startIndex)))
            startIndex++;
        ChapterModel chapter = new ChapterModel();
        chapter.Header = lines.get(startIndex++);
        // Pobieramy drugą poprawną linię i zapisujemy ją jako tytuł rozdziału
        while (!IsLineValid(lines.get(startIndex)))
            startIndex++;
        chapter.Title = lines.get(startIndex++);
        // Dopóki nie dojdziemy do następnego rozdziału wczytujemy podrozdziały
        while (startIndex != -1
                && startIndex < lines.size()
                && !lines.get(startIndex).startsWith("Rozdział"))
            startIndex = LoadSubChapter(lines, chapter, startIndex);
        // Na koniec dodajemy rozdział
        constitution.Chapters.add(chapter);

        return startIndex;
    }

    private int LoadSubChapter(ArrayList<String> lines, ChapterModel chapter, int startIndex)
    {
        SubChapterModel sub = new SubChapterModel();
        while (!IsLineValid(lines.get(startIndex)))
            startIndex++;
        // Jeśli pierwsza ważna linia jest napisana wielkimi literami, to zapisujemy ją jako tytuł podrozdziału
        if (lines.get(startIndex).toUpperCase() == lines.get(startIndex))
            sub.Title = lines.get(startIndex++);
        // Wczytujemy artykuły dopóki nie dojdziemy do końca podrozdziału
        while(startIndex != -1
                && startIndex < lines.size()
                && lines.get(startIndex).toUpperCase() != lines.get(startIndex)
                && !lines.get(startIndex).startsWith("Rozdział"))
            startIndex = LoadArticle(lines, sub, startIndex);
        // Na koniec dodajemy podrozdział
        chapter.SubChapters.add(sub);
        return startIndex;
    }

    private int LoadArticle(ArrayList<String> lines, SubChapterModel sub, int startIndex)
    {
        ArticleModel article = new ArticleModel();
        // Pierwszą ważną linię zapisujemy jako nagłówek artykułu (np. Art. 1.)
        while (!IsLineValid(lines.get(startIndex)))
            startIndex++;

        article.Header = lines.get(startIndex++);
        // Ustawiamy zmienną, dzięki której będziemy wiedzieć, czy nie doszliśmy do kolejnego punktu artykułu
        int currentPoint = 2;
        int currentSubPoint = 1;
        StringBuilder builder = new StringBuilder();
        // Wczytujemy treść artykułu dopóki nie dojdziemy do jego końca
        while(startIndex < lines.size()
                && !lines.get(startIndex).startsWith("Art.")
                && lines.get(startIndex).toUpperCase() != lines.get(startIndex)
                && !lines.get(startIndex).startsWith("Rozdział"))
        {
            // Jeśli natknęliśmy się na nieważne linie, to należy sprawdzić na nowo warunki początkowe
            boolean wereBadlines = false;
            while (!IsLineValid(lines.get(startIndex))) {
                startIndex++;
                wereBadlines = true;
            }
            if (wereBadlines)
                continue;
            // Jeśli obecna linia jest podpunktem, to dodajemy znak nowej linii przed nią
            if (lines.get(startIndex).startsWith(Integer.toString(currentSubPoint)+ ") "))
            {
                builder.append(System.lineSeparator());
                currentSubPoint++;
            }
            // Jeśli dotarliśmy do kolejnego punktu artykułu, to dodajemy stary do kolekcji
            // I składamy kolejny od nowa. Liczymy również od nowa podpunkty
            if (lines.get(startIndex).startsWith(Integer.toString(currentPoint) + ". "))
            {
                article.TextPoints.add(builder.toString());
                currentPoint++;
                currentSubPoint = 1;
                builder = new StringBuilder();
            }
            builder.append(lines.get(startIndex++));
            // Usuwamy ewentualny znak przełamania wiersza
            if (builder.charAt(builder.length()-1) == '-')
                builder.deleteCharAt(builder.length()-1);
        }

        article.TextPoints.add(builder.toString());
        sub.Articles.add(article);
        return startIndex;
    }
}
