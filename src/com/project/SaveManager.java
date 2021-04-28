package com.project;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SaveManager {
    public static <T> void downloadObjects(String path, T object, T ... objects){
        ArrayList<T> objectToDownload = new ArrayList<T>();
        objectToDownload.add(object);
        for (T obj : objects)
            objectToDownload.add(obj);

        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(path))) {
            stream.writeObject(objectToDownload);
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public static <T> ArrayList<T> uploadObjects(String path){
        ArrayList<T> objects = new ArrayList<T>();

        try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(path))) {
            objects = (ArrayList<T>)stream.readObject();
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        return objects;
    }
}
