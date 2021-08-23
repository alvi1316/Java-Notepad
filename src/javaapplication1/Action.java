package javaapplication1;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import say.swing.JFontChooser;

public class Action implements ActionListener,UndoableEditListener{
    
    NotePad notepad;
    
    //Getting the notepad class so that Action class can access notepad components
    Action(NotePad notepad){
        this.notepad = notepad;
    }
    
    //This method is for listening to actions and deciding what to do based on action
     @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == notepad.newItem){
            newItemAction();
        }else if(e.getSource() == notepad.openItem){
            openItemAction();
        }else if(e.getSource() == notepad.saveItem){
            saveItemAction();
        }else if(e.getSource() == notepad.saveAsItem){
            saveAsItemAction();
        }else if(e.getSource() == notepad.exitItem){
            exitItemAction();
        }else if(e.getSource() == notepad.undoItem){
            undoItemAction();
        }else if(e.getSource() == notepad.redoItem){
            redoItemAction();
        }else if(e.getSource() == notepad.copyItem){
            copyItemAction();
        }else if(e.getSource() == notepad.pasteItem){
            pasteItemAction();
        }else if(e.getSource() == notepad.fontItem){
            fontItemAction();
        }else if(e.getSource() == notepad.colorItem){
            colorItemAction();
        }else if(e.getSource() == notepad.wrapItem){
            wrapItemAction();
        }
    }
    
    //This is a undo redo action listener
    @Override
    public void undoableEditHappened(UndoableEditEvent e) {
        notepad.undoManager.addEdit(e.getEdit());
    }
    
    
    //These are diffrent functions that trigger when a certain action is performed
    void newItemAction(){
        notepad.setTitle("Untitled");
        notepad.textArea.setText("");
        notepad.path = "";
    }
    
    void openItemAction(){
        JFileChooser chooser = new JFileChooser(); 
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt", "text");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Open");
        int result = chooser.showOpenDialog(notepad);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();            
            System.out.println(selectedFile.getAbsolutePath());
            try {
                File myObj = new File(selectedFile.getAbsolutePath());
                Scanner myReader = new Scanner(myObj);
                String text = "";
                while (myReader.hasNextLine()) {
                  text += myReader.nextLine()+"\n";
                }
                myReader.close();
                notepad.textArea.setText(text);
                notepad.setTitle(selectedFile.getName());
                notepad.path = selectedFile.getAbsolutePath();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
            }
        }
    }
    
    void saveItemAction(){
        File file = new File(notepad.path);
        if(file.exists()){
            try {
                FileWriter myWriter = new FileWriter(file);
                myWriter.write(notepad.textArea.getText());
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
            }
        }else{
            saveAsItemAction();
        }
    }
    
    void saveAsItemAction(){
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt", "text");
        fileChooser.setFileFilter(filter);
        fileChooser.setDialogTitle("Save");   
        int userSelection = fileChooser.showSaveDialog(notepad);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try {
                FileWriter myWriter = new FileWriter(fileToSave);
                myWriter.write(notepad.textArea.getText());
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
            }
        }
    }
    
    void exitItemAction(){
        notepad.dispose();
    }
    
    void undoItemAction(){
        notepad.undoManager.undo();
    }
    
    void redoItemAction(){
       notepad.undoManager.redo();
    }
    
    void copyItemAction(){
        notepad.copy = notepad.textArea.getSelectedText();
    }
    
    void pasteItemAction(){
        notepad.textArea.setText(notepad.textArea.getText()+notepad.copy);
    }
    
    void fontItemAction(){
        JFontChooser fontChooser = new JFontChooser();
        int result = fontChooser.showDialog(notepad);
        if (result == JFontChooser.OK_OPTION){
            Font font = fontChooser.getSelectedFont(); 
            notepad.textArea.setFont(font);
        } 
    }
    
    void colorItemAction(){
        JColorChooser colorChooser = new JColorChooser();
        Color color = colorChooser.showDialog(null, "Choose a color", Color.black);
        notepad.textArea.setForeground(color);
    }
    
    void wrapItemAction(){
        if(notepad.textArea.getLineWrap()){
            notepad.textArea.setLineWrap(false);            
        }else{
            notepad.textArea.setLineWrap(true);
        }
    }

}
