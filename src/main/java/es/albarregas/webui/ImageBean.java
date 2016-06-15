/**
 * Posible problema:
 * "Can be @ViewScoped, but caution should be taken with byte[] property. You don't want to save it in session."
 */

package es.albarregas.webui;

import java.io.IOException;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.omnifaces.util.Utils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@Named
@ViewScoped 
public class ImageBean implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private UploadedFile file;
    private byte[] content;

    
    public void read(FileUploadEvent event) throws IOException {
    	
    	file = event.getFile();
        content = Utils.toByteArray(file.getInputstream());
        
    }//read

    
    public byte[] getContent() {
        return content;
    }
	public void setContent(byte[] content) {
		this.content = content;
	}
	public UploadedFile getFile() {
		return file;
	}
	public void setFile(UploadedFile file) {
		this.file = file;
	}

}//CLASS