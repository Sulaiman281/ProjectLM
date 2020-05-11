package FileHandling;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import org.w3c.dom.Text;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.util.Scanner;

public class FileHandling {

    private File file;
    private Scanner scanner;

    private String extension;
    private String path;
    private String name;

    public FileHandling(){

    }

    public FileHandling(String _name, String _path, String _extension){
        setName(_name);
        setPath(_path);
        setExtension(_extension);
        System.out.println(loadFile() ? "file loaded" : "not loaded");
        try {
            scanner = new Scanner(file);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
    public boolean loadFile(){
        File dir = new File(path);
        if(!dir.exists()) System.out.println("Directory created "+dir.mkdirs());
        this.file = new File(path+name+"."+extension);
        if(!file.exists())
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file.exists();
    }
    public void write(String line){
        try {
            Writer writer = new FileWriter(this.file);
            writer.write(line);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean checkDir(String _path){
        File dir = new File(_path);
        return dir.mkdirs();
    }
    public void saveImage(Image image, String path, String ext){
        if(image == null) return;
        path = path+"."+ext;
        File file = new File(path);
        BufferedImage pImage = SwingFXUtils.fromFXImage(image,null);
        try{
            ImageIO.write(pImage,ext,file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Image loadImage(String path){
        Image img = null;
        File file = new File(path);
        if(!file.exists()) return null;
        try{
            img = new Image(file.toURL().toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        return img;
    }
    public String getLine(){
        return scanner.nextLine();
    }
    public boolean hasNext(){
        return scanner.hasNext();
    }
}
