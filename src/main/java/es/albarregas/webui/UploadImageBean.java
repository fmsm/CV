/**
 * Posible problema:
 * "Can be @ViewScoped, but caution should be taken with byte[] property. You don't want to save it in session."
 */

package es.albarregas.webui;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.omnifaces.util.Utils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@Named
@ViewScoped 
public class UploadImageBean implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private UploadedFile file;
    private byte[] content;

    
    public void read(FileUploadEvent event) throws IOException {
    	
    	file = event.getFile();
        content = Utils.toByteArray(file.getInputstream());
    }//read
    
    
    public void save(String filename) {
    	
        filename = FilenameUtils.getName(file.getFileName());
        
        ByteArrayInputStream input = new ByteArrayInputStream(content);
 		
        FileOutputStream output = null;
        
        //PARA QUE FUNCIONE HAY QUE PONER EL JODIDO PATH ABSOLUTO
        //PERO CUANDO LO EJECUTE EN ECLIPSE NO VA A SER LO MISMO QUE CUANDO SE EJECUTE EN UN SERVIDOR
        //Y ADEMAS AL HACER ALGUNO DE LOS CLEAN SUPONGO QUE SE BORRARAN LOS FICHEROS SUBIDOS
        
		try {
			File f = new File("/home/francisco/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/bolsaempleofp/resources/uploads", filename);
			
			if (!f.exists()) {
				f.createNewFile();
			}
			
			output = new FileOutputStream(f);
						
		} catch (IOException e1) {
			e1.printStackTrace();
		}

        try {
            IOUtils.copy(input, output);
        } catch (IOException e) {
        	e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(input);
            IOUtils.closeQuietly(output);
        }
        
    }//save 
    
        
    public byte[] getContent() {
        return content;
    }
	public UploadedFile getFile() {
		return file;
	}
	public void setFile(UploadedFile file) {
		this.file = file;
	}

}//CLASS