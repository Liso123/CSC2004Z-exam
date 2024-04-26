import java.util.*;
import java.io.*;

public class Extract
{
    public static void main(String[] args)
    {
        Scanner user = new Scanner(System.in);
        System.out.println("Enter the address book filename:");
        String filename = user.nextLine();
        getData(filename);
        System.out.println("DONE!");
    }

    public static void getData(String file)
    {
        try
        {
            FileReader reader = new FileReader(file);
            Scanner theFile = new Scanner(reader);

            while (theFile.hasNextLine())
            {
                String line = theFile.nextLine();
                String[] words = line.split(" ");
                processLine(words);
            }
        }
        catch (IOException e)
        {
            System.err.println(e);
        }
    }

    public static void processLine(String[] words)
    {
        for (int i=0; i<words.length; i++)
        {
            System.out.println("Line: '" + words[i] + "':");
            getWord(words[i]);
        }
    }

    public static void getWord(String word)
    {
        String newWord = "";
        int start=-1, end = word.length();
        for (int a=0; a<word.length(); a++)
        {
            if (a>0 && Character.isAlphabetic(word.charAt(a-1)))
            {
                continue;
            }
            if (Character.isAlphabetic(word.charAt(a)) && Character.isUpperCase(word.charAt(a))) 
            {
                start = a;
                break;
            }
        }
        if (start>=0)
        {
            for (int c=start+1; c<word.length(); c++)
            {   
                if (Character.isUpperCase(word.charAt(c)))
                {
                    getWord(word.substring(c+1, word.length()));
                    break;
                }
                else if (!Character.isAlphabetic(word.charAt(c)))
                {
                    end = c;
                    newWord = word.substring(start,end);
                    if (newWord.length() > 1) System.out.println(newWord);
                    getWord(word.substring(c+1, word.length()));
                    break;
                }  
                else if (c == word.length()-1) 
                {
                    newWord = word.substring(start,end);
                    if (newWord.length() > 1) System.out.println(newWord);
                }
            }
        }
    }
}