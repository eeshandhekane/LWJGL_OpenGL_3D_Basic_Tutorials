/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lwjgl_3d_application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

/**
 *
 * @author eeshan
 */


/*
Object Oriented 3D Rendering--

The conventional techniques of rendering 3D objects is VERY convoluted and tedious
Instead, we can use object oriented programming approaches to create our own methods for easier rendering
*/


/*
Loading textures--

LWJGL is not having a decent support to load textures. Using I/O, textures can be loaded manually
However, using slick util library, we can load textures easily
This library provides a texture loader class and it LOADS TEXTURES
*/


public class LWJGL_3D_Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
	
	InitializeDisplay();
	InitializeGameLoop();
	CleanUp();
	
    }
    
    
    // Define a function to laod textures
    public static Texture LoadTexture(String st_texture_name, String st_extension) {

	FileInputStream fstream_key = null; // To avoid all the errors of MIGHT NOT BE INITIALIZED!!
	Texture new_texture_loaded = null;
	try {
	    File text_file = new File(st_texture_name);
	    fstream_key = new FileInputStream(text_file);
	    try {
		new_texture_loaded = TextureLoader.getTexture(st_extension, fstream_key); // First argument is the texture. Second argument is file stream
	    } catch (IOException ex) {
		Logger.getLogger(LWJGL_3D_Application.class.getName()).log(Level.SEVERE, null, ex);
	    }
	} catch (FileNotFoundException ex) {
	    Logger.getLogger(LWJGL_3D_Application.class.getName()).log(Level.SEVERE, null, ex);
	} finally {
	    try {
		fstream_key.close();
	    } catch (IOException ex) {
		Logger.getLogger(LWJGL_3D_Application.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
	
	return new_texture_loaded;
    
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
	
	try {
	    // For the entire game loop, the same camera will work. So, create new instance
	    Camera camera; // Good field of view of games is 70
	    camera = new Camera(70.0f, ((float)Display.getWidth())/((float)Display.getHeight()), 0.3f, 1000.0f);
	    // Initialize the keyboard instance
	    Keyboard.create();
	    
	    
	    // Load a texture!!
	    Texture wood_texture = LoadTexture("wood.png", ".png"); // The texture must be square and exponent of 2 in size. Put the file of texture in the location/directory INSIDE WHICH THE src RESIDES!!
	    
	    float z_tr = 0;
	    boolean is_close = true;
	    
	    while(!Display.isCloseRequested() && is_close) {
		
		// Key input
		if(Keyboard.isKeyDown(Keyboard.KEY_Q) || Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
		    is_close = false;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_W) || Keyboard.isKeyDown(Keyboard.KEY_UP)) {
//		    camera.MoveForward();
		    camera.MoveInZ(0.1f, 1);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_S) || Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
//		    camera.MoveBackward();
		    camera.MoveInZ(-0.1f, 1);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_A) || Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
//		    camera.RotateAboutY(0.05f);
		    camera.MoveInZ(0.01f, 0);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_D) || Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
//		    camera.RotateAboutY(-0.05f);
		    camera.MoveInZ(-0.01f, 0);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_H)) {
		    camera.RotateAboutY(0.05f);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_J)) {
		    camera.RotateAboutY(-0.05f);
		}
		
		// Clear the frame
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // OR GL_DEPTH_BUFFER
		
		// Clear the matrix
		glLoadIdentity();
		// Set the camera view
		camera.UpdateView();
		
		// Set clear color
		glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		
		// Draw something!!
		glPushMatrix();
		
		
//		glColor4f(0.3594f, 0.8555f, 0.5820f, 0.5f);
		glTranslatef(0.0f, 0.0f, -10.0f);
		glRotatef(z_tr*5, 1.0f, 1.0f, 1.0f);
		// Bind the texture after pushing matrix
		wood_texture.bind();
		
		/*
		Just binding a texture is not sufficient, each vertex needs to know where exactly should it be mapped
		The corners of the texture are mapped to s, t coordinates of the texture
		Bottom_Left = 0, 0
		Top_Left = 0, 1
		Top_Right = 1, 1
		Bottom_Right = 1, 0
		Now, before each vertex, put the appropriate texture coordinate info!!
		*/
		
		/*
		By default, textures are disabled in OpenGL!!
		We need to enable the texture mode in order to display them!!
		*/
		
		glBegin(GL_QUADS);
		// Face 1
		glColor4f(1.0f, 0.0f, 0.0f, 1.0f);
		glTexCoord2f(0.0f, 0.0f); glVertex3f(0.0f, 0.0f, 0.0f);
		glTexCoord2f(0.0f, 1.0f); glVertex3f(0.0f, 1.0f, 0.0f);
		glTexCoord2f(1.0f, 1.0f); glVertex3f(1.0f, 1.0f, 0.0f);
		glTexCoord2f(1.0f, 0.0f); glVertex3f(1.0f, 0.0f, 0.0f);
		// Face 2
		glColor4f(0.0f, 1.0f, 0.0f, 0.0f);
		glVertex3f(0.0f, 0.0f, 0.0f);
		glVertex3f(1.0f, 0.0f, 0.0f);
		glVertex3f(1.0f, 0.0f, 1.0f);
		glVertex3f(0.0f, 0.0f, 1.0f);
		// Face 3
		glColor4f(0.0f, 0.0f, 1.0f, 0.0f);
		glVertex3f(0.0f, 0.0f, 0.0f);
		glVertex3f(0.0f, 0.0f, 1.0f);
		glVertex3f(0.0f, 1.0f, 1.0f);
		glVertex3f(0.0f, 1.0f, 0.0f);
		// Face 4
		glColor4f(0.0f, 1.0f, 1.0f, 0.0f);
		glVertex3f(0.0f, 0.0f, 1.0f);
		glVertex3f(0.0f, 1.0f, 1.0f);
		glVertex3f(1.0f, 1.0f, 1.0f);
		glVertex3f(1.0f, 0.0f, 1.0f);
		// Face 5
		glColor4f(1.0f, 0.0f, 1.0f, 0.0f);
		glVertex3f(0.0f, 1.0f, 0.0f);
		glVertex3f(1.0f, 1.0f, 0.0f);
		glVertex3f(1.0f, 1.0f, 1.0f);
		glVertex3f(0.0f, 1.0f, 1.0f);
		// Face 6
		glColor4f(1.0f, 1.0f, 0.0f, 0.0f);
		glVertex3f(1.0f, 0.0f, 0.0f);
		glVertex3f(1.0f, 0.0f, 1.0f);
		glVertex3f(1.0f, 1.0f, 1.0f);
		glVertex3f(1.0f, 1.0f, 0.0f);
		glEnd();
		glPopMatrix();
		
		
		
		Display.update();
		
		z_tr += 0.05f;
		
	    }
	} catch (LWJGLException ex) {
	    Logger.getLogger(LWJGL_3D_Application.class.getName()).log(Level.SEVERE, null, ex);
	}
    
    }
    
    
    // Define a function to clean up after the game loop exits
    public static void 
    CleanUp() {
	
	Display.destroy();
	Keyboard.destroy();
    }
	 
    
}
