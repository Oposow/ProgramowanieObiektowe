package Providers;

import Consts.PartType;
import Interfaces.IConstitutionDisplayingProvider;
import Models.*;

/**
 * Created by Filip i Paulinka on 26.11.2016.
 */
public class ConstitutionDisplayingProvider implements IConstitutionDisplayingProvider
{

    public void DisplayConstitution(ConstitutionModel constitution, DisplayingArgumentsModel arguments)
    {
        if (arguments.TypeToDisplay == PartType.Chapter)
            System.out.println(GetChapterAsString(constitution, arguments.StartNum));
        else if (arguments.TypeToDisplay == PartType.Article)
        {
            for(int i = arguments.StartNum; i<=arguments.EndNum; i++)
                System.out.println(GetArticleAsString(constitution, i));
        }
        else
            throw new IllegalArgumentException();
    }

    public String GetChapterAsString(ConstitutionModel constitution, int chapterNumber)
    {
        String newLine = System.lineSeparator();
        StringBuilder resultBuilder = new StringBuilder();
        ChapterModel chapter = GetChapterModel(constitution, chapterNumber-1);

        resultBuilder.append(chapter.Header).append(newLine);
        resultBuilder.append(chapter.Title).append(newLine);

        for (SubChapterModel sub: chapter.SubChapters)
        {
            if (sub.Title != null && !sub.Title.isEmpty())
                resultBuilder.append(sub.Title).append(newLine);
            for(ArticleModel article : sub.Articles)
                resultBuilder.append(GetArticleAsString(article));
        }

        return resultBuilder.toString();
    }

    public String GetArticleAsString(ConstitutionModel constitution, int articleNumber) throws IllegalArgumentException
    {
        int i = 0;
        for (ChapterModel chapter : constitution.Chapters)
            for(SubChapterModel sub: chapter.SubChapters)
                for(ArticleModel art: sub.Articles)
                    if (++i == articleNumber)
                        return GetArticleAsString(art);
        throw new IllegalArgumentException();
    }

    private String GetArticleAsString(ArticleModel article)
    {
        String newLine = System.lineSeparator();
        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append(article.Header).append(newLine);

        for (int i = 0; i<article.TextPoints.size(); i++)
            resultBuilder.append(article.TextPoints.get(i)).append(newLine);

        return resultBuilder.toString();
    }

    private ChapterModel GetChapterModel(ConstitutionModel constitution, int chapterNumber) throws IllegalArgumentException
    {
        if (constitution.Chapters.size() < chapterNumber)
            throw new IllegalArgumentException();

        return constitution.Chapters.get(chapterNumber);
    }
}
