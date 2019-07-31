package LogicaGetSet;

import java.io.File;
import javax.swing.ImageIcon;

public class Imagen {
    ImageIcon imagenFoto = null;
    File fileFoto = null;

    public Imagen() {
    }

    public Imagen(ImageIcon imagenFoto, File fileFoto) {
        this.imagenFoto = imagenFoto;
        this.fileFoto = fileFoto;
    }

    public File getFileFoto() {
        return fileFoto;
    }

    public void setFileFoto(File fileFoto) {
        this.fileFoto = fileFoto;
    }

    public ImageIcon getImagenFoto() {
        return imagenFoto;
    }

    public void setImagenFoto(ImageIcon imagenFoto) {
        this.imagenFoto = imagenFoto;
    }
}
