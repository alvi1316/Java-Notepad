package javaapplication1;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.undo.UndoManager;


class NotePad extends JFrame{
    
    //All menuBar
    JMenuBar menuBar;
    
    //All Menu
    JMenu fileMenu, editMenu, formatMenu;
    
    //All MenuItems
    JMenuItem newItem, openItem, saveItem, saveAsItem, exitItem, undoItem, redoItem, copyItem, pasteItem, fontItem, colorItem;
    JCheckBoxMenuItem wrapItem;
    
    //All body components
    JTextArea textArea;
    JScrollPane scrollPane;
    
    //File Path
    String path = "";
    
    //Copied text for copy paste functionality
    String copy = "";
    
    //Undo Manager for undo redo functionality
    UndoManager undoManager;
    
    //Action class to handle all the event
    Action action;
    
    NotePad(){
        //Initializing all JMenuBar
        menuBar = new JMenuBar();
        
        //Initializing all JMenu
        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");
        formatMenu = new JMenu("Format");
        
        //Initializing all JMenuItem under File Menu
        newItem = new JMenuItem("New");
        openItem = new JMenuItem("Open");
        saveItem = new JMenuItem("Save");
        saveAsItem = new JMenuItem("Save As");
        exitItem = new JMenuItem("exit");
        
        //Initializing all JMenuItem under Edit Menu
        undoItem = new JMenuItem("Undo");
        redoItem = new JMenuItem("Redo");
        copyItem = new JMenuItem("Copy");
        pasteItem = new JMenuItem("Paste");
        
        //Initializing all JMenuItem under Format Menu
        wrapItem = new JCheckBoxMenuItem("Text warp");
        fontItem = new JMenuItem("Font");
        colorItem = new JMenuItem("Color");
        
        //Initializing textarea components
        textArea = new JTextArea();
        scrollPane = new JScrollPane();
        
        //Initialize undoManager
        undoManager = new UndoManager();
        
        //Initializing Action class and passing this Notepad class 
        //so that Action class can access Notepad components
        action = new Action(this);
        
        //Setting up event listener for every MenuItems
        setEventHandeler();
   
        //Adding all Items to Menu
        createFileMenu();
        createEditMenu();
        createFormatMenu();  
        
        //Adding all Menu to MenuBar
        createMenuBar();
        
        //Creating the body and window and adding MenuBar to window
        createWindow();        
        createTextArea();  
        
        setVisible(true);       
    }
    
    
    //Function to create window and add MenuBar to the window
    void createWindow(){
        setTitle("Untitled");
        setSize(800,600);
        setJMenuBar(menuBar);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    //Function to create Text Area
    void createTextArea(){
        textArea.getDocument().addUndoableEditListener(action);
        scrollPane.setViewportView(textArea);
        add(scrollPane);
    }
    
    //Function to bind all the components to the action class which can handle events
    void setEventHandeler(){
        newItem.addActionListener(action);
        openItem.addActionListener(action);
        
        
        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        saveItem.addActionListener(action);
        
        saveAsItem.addActionListener(action);
        exitItem.addActionListener(action);
        
        undoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        undoItem.addActionListener(action);
        
        redoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
        redoItem.addActionListener(action);
        
        
        copyItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        copyItem.addActionListener(action);
        
        pasteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        pasteItem.addActionListener(action);
        
        fontItem.addActionListener(action);
        colorItem.addActionListener(action);
        wrapItem.addActionListener(action);
    }
    
    //Function to create FileMenu by adding all the items to it
    void createFileMenu(){
        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(saveAsItem);
        fileMenu.add(exitItem);
    }
    
    //Function to create EditMenu by adding all the items to it
    void createEditMenu(){
        editMenu.add(undoItem);
        editMenu.add(redoItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);
    }
    
    //Function to create FormatMenu by adding all the items to it
    void createFormatMenu(){
        formatMenu.add(fontItem);
        formatMenu.add(colorItem);
        formatMenu.add(wrapItem);
    }
    
    //Function to create Menubar by adding all Menu to it
    void createMenuBar(){
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(formatMenu);
    }
    
}