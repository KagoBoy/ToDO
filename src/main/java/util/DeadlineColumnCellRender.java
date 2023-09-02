/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.awt.Color;
import java.awt.Component;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import model.Task;

/**
 *
 * @author yanvi
 */
public class DeadlineColumnCellRender extends DefaultTableCellRenderer{
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, 
            boolean isSelected, boolean hasFocus, int row, int col){
        
        JLabel label;
        label = (JLabel) super.getTableCellRendererComponent(table, value, 
                isSelected, hasFocus, row, col);
        
        label.setHorizontalAlignment(CENTER);
        
        TaskTableModel tskM = (TaskTableModel) table.getModel();
        Task tsk = tskM.getTasks().get(row);
        if(tsk.getDeadline().after(new Date())){
            label.setBackground(Color.GREEN);
        }else{
            label.setBackground(Color.RED);
        }
        
        return label;
    }   
}
