/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lwjgl_3d_application;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author eeshan
 */


/*
Object Oriented 3D Rendering--

The conventional techniques of rendering 3D objects is VERY convoluted and tedious
Instead, we can use object oriented programming approaches to create our own methods for easier rendering
*/


public class LWJGL_3D_Application_1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
	
	InitializeDisplay();
	InitializeGameLoop();
	CleanUp();
	
    }
    
    
    // Define a function to initialize display
    public static void 
    InitializeDisplay() {
    
	try {
	    DisplayMode display_mode = new DisplayMode(800, 600);
	    Display.setDisplayMode(display_mode);
	    Display.create();
	} catch (LWJGLException ex) {
	    Logger.getLogger(LWJGL_3D_Application.class.getName()).log(Level.SEVERE, null, ex);
	}
    
    }
    
    
    // Define a function to display the game loop
    public static void 
    InitializeGameLoop() {
	
	// For the entire game loop, the same camera will work. So, create new instance
	Camera camera = new Camera(70.0f, ((float)Display.getWidth())/((float)Display.getHeight()), 0.3f, 1000.0f); // Good field of view of games is 70
    
	float z_tr = 0;
	
	while(!Display.isCloseRequested()) {
	    
	    // Clear the frame
	    glClear(GL_COLOR_BUFFER_BIT);
	    // Clear the matrix
	    glLoadIdentity();
	    
	    // Set clear color
	    glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
	    
	    // Draw something!!
	    glPushMatrix();
		glColor4f(0.3594f, 0.8555f, 0.5820f, 0.5f);
		glTranslatef(0.0f, 0.0f, -10.0f);
		glRotatef(5*z_tr, 1.0f, 1.0f, 1.0f);
		glBegin(GL_QUADS);
		    // Face 1
		    glVertex3f(0.0f, 0.0f, 0.0f);
		    glVertex3f(0.0f, 1.0f, 0.0f);
		    glVertex3f(1.0f, 1.0f, 0.0f);
		    glVertex3f(1.0f, 0.0f, 0.0f);
		    // Face 2
		    glVertex3f(0.0f, 0.0f, 0.0f);
		    glVertex3f(1.0f, 0.0f, 0.0f);
		    glVertex3f(1.0f, 0.0f, 1.0f);
		    glVertex3f(0.0f, 0.0f, 1.0f);
		    // Face 3
		    glVertex3f(0.0f, 0.0f, 0.0f);
		    glVertex3f(0.0f, 0.0f, 1.0f);
		    glVertex3f(0.0f, 1.0f, 1.0f);
		    glVertex3f(0.0f, 1.0f, 0.0f);
		    // Face 4
		    glVertex3f(0.0f, 0.0f, 1.0f);
		    glVertex3f(0.0f, 1.0f, 1.0f);
		    glVertex3f(1.0f, 1.0f, 1.0f);
		    glVertex3f(1.0f, 0.0f, 1.0f);
		    // Face 5
		    glVertex3f(0.0f, 1.0f, 0.0f);
		    glVertex3f(1.0f, 1.0f, 0.0f);
		    glVertex3f(1.0f, 1.0f, 1.0f);
		    glVertex3f(0.0f, 1.0f, 1.0f);
		    // Face 6
		    glVertex3f(1.0f, 0.0f, 0.0f);
		    glVertex3f(1.0f, 0.0f, 1.0f);
		    glVertex3f(1.0f, 1.0f, 1.0f);
		    glVertex3f(1.0f, 1.0f, 0.0f);
		glEnd();
	    glPopMatrix();
	    
	    // Set the camera view
	    camera.UpdateView();
	
	    Display.update();
	    
	    z_tr += 0.05f;
//	    z_tr -= 0.1f;
	
	}
    
    }
    
    
    // Define a function to clean up after the game loop exits
    public static void 
    CleanUp() {
	
	Display.destroy();
    }
	 
    
}
