package com.mrcrayfish.modelcreator.util;

import com.mrcrayfish.modelcreator.Exporter;

import java.text.ParseException;

public class Parser
{
    public static double parseDouble(String text, double def)
    {
        try
        {
            return Exporter.FORMAT.parse(text).doubleValue();
        }
        catch(NumberFormatException | ParseException e)
        {
            e.printStackTrace();
        }
        return def;
    }
}
