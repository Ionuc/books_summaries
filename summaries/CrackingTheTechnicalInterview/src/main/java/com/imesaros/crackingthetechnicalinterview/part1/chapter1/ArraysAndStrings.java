package com.imesaros.crackingthetechnicalinterview.part1.chapter1;

public class ArraysAndStrings
{
    public static boolean isUniqueChars1(String input)
    {
        boolean[] ascii = new boolean[256];
        for (int i = 0; i < input.length(); i++)
        {
            char val = input.charAt(i);
            if (ascii[val])
            {
                return false;
            }
            ascii[val] = true;
        }
        return true;
    }

    public static boolean isUniqueChars2(String input)
    {
        int ascii = 0;
        for (int i = 0; i < input.length(); i++)
        {
            char val = input.charAt(i);
            if ((ascii & (1 << val)) > 0)
            {
                return false;
            }
            ascii |= 1 << val;
        }
        return true;
    }

    public static String removeDuplicates(String input)
    {
        if (input == null || input.length() < 2)
        {
            return input;
        }
        char[] chars = input.toCharArray();
        int tail = 1;

        for (int i = 1; i < chars.length; ++i)
        {
            int j;
            for (j = 0; j < tail; ++j)
            {
                if (chars[i] == chars[j])
                {
                    break;
                }
            }
            if (j == tail)
            {
                chars[tail] = chars[i];
                ++tail;
            }
        }
        return String.valueOf(chars).substring(0, tail);
    }
}
