package main.gerta;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {
    /**
     *
     */
    private int width, height;
    private String title;
    private long glfwWindow;

    private static Window window;

    static {
        window = null;
    }

    private Window() {
        this.width = 800;
        this.height = 600;
        this.title = "Window";
    }

    public static  Window get(){
        if (Window.window == null){
            Window.window = new Window();
        }
        return Window.window;
    }

    public void run () {
        System.out.println("Hello LWJGL" + Version.getVersion()+ "!");

        init();
        loop();
    }

    /**
     * Setup and error callback
     */
    public void init(){
        GLFWErrorCallback.createPrint(System.err).set();

        /**
         * Initialize GLFW
         */
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW.");
        }
        /**
         * Configure GLFW
         */
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);

        /**
         * Create the window
         */
        glfwWindow = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);
        if(glfwWindow == NULL){
            throw new IllegalStateException("Failed to create the GLFW window.");
        }
        /**
         * Make the OpenGL context current
         * glfwSwapInterval(1) turn On v-sync
         */
        glfwMakeContextCurrent(glfwWindow);
        glfwSwapInterval(1);

        /**
         * Make the window visible
         */
        glfwShowWindow(glfwWindow);

        /**
         * This line is critical for LWJGL's interoperation with GLFW's
         * OpenGL context, or any context that is managed externally.
         * LWJGL detects the context that is current in the current thread,
         * creates the GLCapabilities instance and makes the OpenGL
         * bindings available for use.
         */
        GL.createCapabilities();
    }

    public void loop(){
        while (!glfwWindowShouldClose(glfwWindow)){
            //Poll events
            glfwPollEvents();

            glClearColor(1.0f, 0.0f, 0.0f, 1.0f);
            glClear(GL_COLOR_BUFFER_BIT);
            glfwSwapBuffers(glfwWindow);
        }
    }

}
