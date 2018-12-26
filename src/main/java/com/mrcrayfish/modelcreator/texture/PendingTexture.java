package com.mrcrayfish.modelcreator.texture;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class PendingTexture
{
    private File texture;
    private File meta;
    private TextureCallback callback;

    public PendingTexture(File texture)
    {
        this(texture, (TextureCallback) null);
    }

    public PendingTexture(File texture, File meta)
    {
        this(texture, meta, null);
    }

    private PendingTexture(File texture, TextureCallback callback)
    {
        this.texture = texture;
        this.callback = callback;
    }

    public PendingTexture(File texture, File meta, TextureCallback callback)
    {
        this.texture = texture;
        this.meta = meta;
        this.callback = callback;
    }

    public void load()
    {
        try
        {
            boolean result;
            String fileName = this.texture.getName().replace(".png", "").replaceAll("\\d*$", "");
            Texture texture = TextureManager.getTexture(fileName);
            if(texture == null)
            {
                FileInputStream is = new FileInputStream(this.texture);
                result = TextureManager.loadExternalTexture(this.texture, this.meta);
                is.close();
            }
            else
            {
                result = true;
            }

            if(callback != null)
            {
                callback.callback(result, fileName);
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
