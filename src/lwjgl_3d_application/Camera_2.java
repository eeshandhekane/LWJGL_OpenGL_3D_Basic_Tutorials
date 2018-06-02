package lwjgl_3d_application;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author eeshan
 */


// Import opengl
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;


/*
In order to convert to rendering 3D, we need to convert the display into a WINDOW TO A 3D WORLD!!
This is achieved through a camera. We create a separate class for the camera
*/


public class Camera_2 {
    
    
    /*
    Position and angle of the camera are the only required things for locating the camera
    Then, to fix the amount of visual field that can be seen through the camera, we need to have a field of view. Only the content in the field of view will be seen, and everything outside it is neglected. It is essentially an angle
    Aspect ratio is required as the field of view, which is cone-like, needs to be converted into a screen. It is the ratio of height to width. This is ultimately our view
    Near and far clipping planes are used to cull region of space, from the FOV, such that shapes before the clipping plane and after the far clipping plane are not drawn. There is a clipping-algorithm based explanation as to why this near clipping is necessary. Far clipping is needed so that we do not look upto infinity, which looks bad
    */


    // Define the attributes
    private float x; 
    private float y;
    private float z; // The X, Y, Z coordinates of the camera
    private float rot_x;
    private float rot_y;
    private float rot_z; // The rotations of the camera about X, Y, Z axes
    private final float field_of_view;
    private final float aspect_ratio;
    private final float near_clipping_plane;
    private final float far_clipping_plane;
    
    
    // Define a constructor

    public Camera_2(float field_of_view, float aspect_ratio, float near_clipping_plane, float far_clipping_plane) {
	
	// Set the location to origin default
	x = 0;
	y = 0;
	z = 0;
	rot_x = 0;
	rot_y = 0;
	rot_z = 0;
	
	// Set the input parameters
	this.field_of_view = field_of_view;
	this.aspect_ratio = aspect_ratio;
	this.near_clipping_plane = near_clipping_plane;
	this.far_clipping_plane = far_clipping_plane;
	
	// Initialize the projection matrix and revert to setting model view matrix
	InitializeProjection();
	
    }
    
    
    /*
    Once the attributes are defined, we create a substitute for the initializer of opengl
    */
    
    
    // Define a function to initialize the 3D projection matrix
    private void 
    InitializeProjection() {
	
	// Set projection mode
	glMatrixMode(GL_PROJECTION);
	// Clear off the matrix
	glLoadIdentity();
	// Now, set up perspective
	gluPerspective(field_of_view, aspect_ratio, near_clipping_plane, far_clipping_plane);
	// Set back to model view matrix
	glMatrixMode(GL_MODELVIEW);
	// Enable depth test. However, do not forget to clear the depth buffer everytime!!
	glEnable(GL_DEPTH_TEST);
    }
    
    
    /*
    We need to set the view from the camera
    */
    
    
    // Define a function to set the view from the display window to the view from the location of the camera
    public void 
    UpdateView() {
    
	// First rotate and then translate
	glRotatef(rot_x, 1.0f, 0.0f, 0.0f);
	glRotatef(rot_y, 0.0f, 1.0f, 0.0f);
	glRotatef(rot_z, 0.0f, 0.0f, 1.0f);
	glTranslatef(x, y, z);
	
    
    }
    
    
    // Define a function to get X
    public float GetX() {
	
	return x;
    
    }
    
    
    // Define a function to get Y
    public float GetY() {
	
	return y;
    
    }
    
    
    // Define a function to get Z
    public float GetZ() {
	
	return z;
    
    }
    
    
    // Define a function to get rot_X
    public float GetRotX() {
	
	return rot_x;
    
    }
    
    
    // Define a function to get rot_Y
    public float GetRotY() {
	
	return rot_y;
    
    }
    
    
    // Define a function to get rot_Z
    public float GetRotZ() {
	
	return rot_z;
    
    }
    
    
    // Define a function to set X
    public void SetX(float x) {
	
	this.x = x;
    
    }
    
    
    // Define a function to set Y
    public void SetY(float y) {
	
	this.y = y;
    
    }
    
    
    // Define a function to set Z
    public void SetZ(float z) {
	
	this.z = z;
    
    }
    
    
    // Define a function to set rot_X
    public void SetRotX(float rot_x) {
	
	this.rot_x = rot_x;
    
    }
    
    
    // Define a function to set rot_Y
    public void SetRotY(float rot_y) {
	
	this.rot_y = rot_y;
    
    }
    
    
    // Define a function to set rot_Z
    public void SetRotZ(float rot_z) {
	
	this.rot_z = rot_z;
    
    }
    
    
    // Define a function to move forward
    public void MoveForward() {
    
	/*
	inputs--
	*/
	
	/*
	outputs--
	*/
	
	// The simple way to move forward is to increase the z component
	float f_move_forward = 0.2f;
	this.z += f_move_forward;
	
    }

    public void MoveBackward() {
	
	/*
	inputs--
	*/
	
	/*
	outputs--
	*/
	
	float f_move_backward = 0.2f;
	this.z -= f_move_backward;
    }
    
    
    public void RotateAboutY(float rot_y_incr) {
    
	this.rot_y += rot_y_incr;
    
    }
    
    
    public void RotateAboutZ(float rot_z_incr) {
    
	this.rot_z += rot_z_incr;
    
    }
    
    
    // Define a function to move forward
    public void MoveInZ(float f_z, float f_direction) {
    
	/*
	inputs--
	*/
	
	/*
	outputs--
	*/
	
	this.z += f_z*Math.sin(Math.toRadians(this.rot_y + 90*f_direction));
	this.x += f_z*Math.cos(Math.toRadians(this.rot_y + 90*f_direction));
	
    }
    
}
