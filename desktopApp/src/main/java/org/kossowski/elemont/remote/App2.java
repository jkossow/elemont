/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.remote;



import org.kossowski.elemont.main.*;
import javax.swing.UIManager;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 *
 * @author Janusz Kossowski
 */
public class App2 extends SingleFrameApplication{
    
        /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        
        try {
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
            //UIManager.setLookAndFeel( "javax.swing.plaf.synth.SynthLookAndFeel" );
        } catch (Exception e) {
        }
            
        show( new RemoteMainFrame() );
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
        // tutaj np Context Spring
        ApplicationContext ctx = new ClassPathXmlApplicationContext("springRemoteAppContext.xml");
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of DesktopApplication1
     */
    public static App2 getApplication() {
        return Application.getInstance( App2.class );
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) throws Exception {
       
        launch(App2.class, args);
    }
    
}


