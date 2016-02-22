/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kossowski.elemont.main;



import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;
import org.jdesktop.application.Application;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.SingleFrameApplication;
import org.kossowski.elemont.services.InitDataBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 *
 * @author Janusz Kossowski
 */
public class App2 extends SingleFrameApplication{
    
    
    private ApplicationContext springContext;

    
    
    
    
    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        
        try {
            //UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
            //UIManager.setLookAndFeel( "com.sun.java.swing.plaf.gtk.GTKLookAndFeel" );
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            
            UIManager.setLookAndFeel( "javax.swing.plaf.metal.MetalLookAndFeel" );
            MetalLookAndFeel.setCurrentTheme( new OceanTheme() );
            
            //UIManager.setLookAndFeel( "javax.swing.plaf.synth.SynthLookAndFeel" );
            //UIManager.setLookAndFeel( "javax.swing.plaf.nimbus.NimbusLookAndFeel" );
            //UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
            //UIManager.setLookAndFeel("javax.swing.plaf.multi.MultiLookAndFeel");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        springContext = new ClassPathXmlApplicationContext("springMainAppContext.xml");    
        show( (MasterMainFrame) springContext.getBean("masterMainFrame")  );
        //show( new MasterMainFrame() );
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
        // tutaj np Context Spring
        
        InitDataBean ib = (InitDataBean)springContext.getBean("initDataBean");
        ib.init();
        for( String s : springContext.getBeanDefinitionNames() )
            System.out.println(s);
    }

    
    /**
     * A convenient static getter for the application instance.
     * @return the instance of DesktopApplication1
     */
    public static App2 getApplication() {
        return Application.getInstance( App2.class );
    }

    public ApplicationContext getSpringContext() {
        return springContext;
    }

    public void setSpringContext(ApplicationContext springContext) {
        this.springContext = springContext;
    }

    
    
    
    
    /**
     * Main method launching the application.
     */
    public static void main(String[] args) throws Exception {
       
        launch(App2.class, args);
    }
    
}


