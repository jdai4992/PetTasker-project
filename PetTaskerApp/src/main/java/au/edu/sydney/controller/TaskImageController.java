package au.edu.sydney.controller;

import au.edu.sydney.service.TaskService;
import au.edu.sydney.model.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.awt.Graphics2D;  
import java.awt.Image;  
import java.awt.geom.AffineTransform;  
import java.awt.image.AffineTransformOp;  
import java.awt.image.BufferedImage;  
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;  

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Controller
public class TaskImageController {

    @Autowired
    private TaskService taskService;

    @RequestMapping(value="/newTask/{taskId}/imageUpload", method = RequestMethod.GET)
    public String displayUploadForm(@PathVariable("taskId") int taskId, ModelMap modelMap, HttpSession session) {
	    	if (session.getAttribute("currentUser") != null) {
	    		modelMap.addAttribute("task", taskService.getTask(taskId));
	    		return "imageUpload";
	    	}
	    	//redirect to login page if the user isn't logged in
	    	return "redirect:/";
	}
    
    @RequestMapping(value = "/newTask/{taskId}/imageUpload", method = RequestMethod.POST)
    public String createNewTask(@PathVariable("taskId") int taskId, @RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, HttpSession session) throws IOException {
	    	
	    	User user = (User) session.getAttribute("currentUser");
	    	int userId = user.getId();
	    	if (!file.isEmpty() && file != null){
	    		//get physical path of webapp
	    		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
	    		//get original filename
	    		String originalFileName = file.getOriginalFilename();
	    		//set the path for storing
	    		Path path = Paths.get(rootDirectory + "/WEB-INF/resources/taskImages/" + taskId + ".png");
	    		Path previewPath = Paths.get(rootDirectory + "/WEB-INF/resources/taskImagePreviews/" + taskId + ".png");
	    		//show the path of file will be stored
	    		System.out.println(path.toString());  
	    		File targetFile = new File(path.toString());
	    		File previewTargetFile = new File(previewPath.toString());
	    		if(!targetFile.exists()){  
	                targetFile.mkdirs();  
	            }
	    		if(!previewTargetFile.exists()){  
	    			previewTargetFile.mkdirs();  
	            }
	    		try {
	    		// convert the image type to png
	    		file.transferTo(targetFile);
	    		} catch (IOException e) {
	    		// oops! something did not work as expected
	    		e.printStackTrace();
	    		throw new RuntimeException("Saving User image was not successful", e);
	    		}
	    		imageOp(path.toString(), previewPath.toString(), 400);
	    	}
	    	return "redirect:/userHomePage";
    }  
    
    private void imageOp(String inFilePath, String outFilePath, int width){  
        File tempFile = new File(inFilePath);  
        Image image = null;  
        try {  
            image = ImageIO.read(tempFile);  
        } catch (IOException e) {  
            System.out.println("file path error...");  
        }  
          
        int originalImageWidth = image.getWidth(null);  
        int originalImageHeight = image.getHeight(null);
        double scale = (double) originalImageWidth/originalImageHeight;
        int height = (width * originalImageHeight) / originalImageWidth;
          
        BufferedImage originalImage = new BufferedImage(  
                originalImageWidth,  
                originalImageHeight,  
                BufferedImage.TYPE_3BYTE_BGR);  
        Graphics2D g2d = originalImage.createGraphics();  
        g2d.drawImage(image, 0, 0, null);  
          
        BufferedImage changedImage =  
            new BufferedImage(  
                    width,  
                    height,  
                    BufferedImage.TYPE_3BYTE_BGR);  
          
        double widthBo = (double)width/originalImageWidth;  
        double heightBo = (double)height/originalImageHeight;  
          
        AffineTransform transform = new AffineTransform();  
        transform.setToScale(widthBo, heightBo);  
          
        AffineTransformOp ato = new AffineTransformOp(transform, null);  
        ato.filter(originalImage, changedImage);  
          
        File fo = new File(outFilePath); 
  
        try {  
            ImageIO.write(changedImage, "jpeg", fo);  
        } catch (Exception e) {  
        	e.printStackTrace();  
        } 
    }
    
    @RequestMapping(value = "/image/{taskId}.png", method = RequestMethod.GET)
    public void createFlow(@PathVariable("taskId") int taskId, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
    	String rootDirectory = request.getSession().getServletContext().getRealPath("/") + "/WEB-INF/resources/taskImages/" + taskId + ".png";
    	File file = new File(rootDirectory);
    	String defaultDirectory = request.getSession().getServletContext().getRealPath("/") + "/WEB-INF/img/noimagefound.png";
    	
    	response.setContentType("image/png"); // set format of image  
        InputStream in = null;  
        OutputStream os = null;
    	
    	if(!file.exists()){ 
    		file = new File(defaultDirectory);
    	}
    		try {  
                in = new FileInputStream(file);  
                os = response.getOutputStream(); // create output stream  
                byte[] b = new byte[1024];  
                while (in.read(b) != -1) {  
                    os.write(b);  
                }  
                os.flush();  
            } catch (FileNotFoundException e) {  
                e.printStackTrace();  
            } catch (IOException e) {  
                e.printStackTrace();  
            } finally {  
                try {  
                    if (in != null) {  
                        in.close();  
                    }  
                    if (os != null) {  
                        os.close();  
                    }  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }    
            }  
    	
    }
    
    @RequestMapping(value = "/imagePreview/{taskId}.png", method = RequestMethod.GET)
    public void createPreviewFlow(@PathVariable("taskId") int taskId, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
    	String rootDirectory = request.getSession().getServletContext().getRealPath("/") + "/WEB-INF/resources/taskImagePreviews/" + taskId + ".png";
    	File file = new File(rootDirectory);
    	response.setContentType("image/png"); // set format of image  
        InputStream in = null;  
        OutputStream os = null;  
        try {  
            in = new FileInputStream(file);  
            os = response.getOutputStream(); // create output stream  
            byte[] b = new byte[1024];  
            while (in.read(b) != -1) {  
                os.write(b);  
            }  
            os.flush();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                if (in != null) {  
                    in.close();  
                }  
                if (os != null) {  
                    os.close();  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }    
        }  
    }
    
}


