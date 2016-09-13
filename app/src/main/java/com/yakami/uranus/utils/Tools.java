package com.yakami.uranus.utils;

import android.content.Context;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

/**
 * Created by Yakami on 2015/11/28.
 */
public class Tools {

    public static boolean isEmpty(String str) {
        return (str == null || str.trim().equals(""));
    }

    public static byte[] serial(Object objects) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(objects);
            return bos.toByteArray();
        } catch (IOException e) {
            System.out.print("Tools.Serial.IOException");
            return null;
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                System.out.print("Tools.Serial.IOException");
            }
            try {
                bos.close();

            } catch (IOException ex) {
                System.out.print("Tools.Serial.IOException");
            }
        }

    }

    public static int dp2px(Context context, int dp)
    {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }


    public static Object unSerial(byte[] bytes) {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInput in = null;
        try {
            in = new ObjectInputStream(bis);
            Object o = in.readObject();
            return o;
        } catch (IOException e) {
            System.out.print("Tools.unSerial.IOException");
            return null;
        } catch (ClassNotFoundException e) {
            System.out.print("Tools.unSerial.ClassNotFoundException");
            return null;
        } finally {
            try {
                bis.close();
            } catch (IOException ex) {
                System.out.print("Tools.unSerial.IOException");
            }
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                System.out.print("Tools.unSerial.IOException");
            }
        }
    }

    public static void toast(Context context, String str) {
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }

    @SuppressWarnings("unchecked")
    public static <T> T cast(Object obj, T tmp) {
        return (T) obj;
    }


}
