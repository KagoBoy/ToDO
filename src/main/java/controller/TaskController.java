/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Task;
import util.ConnectionFactory;

/**
 *
 * @author yanvi
 */
public class TaskController {
    
    public void save(Task task){
        
        String sql = "INSERT INTO tasks (id_project, name, description,"
                + "completed, notes, deadline, createdAt, updatedAt)" 
                + "values (?, ?, ?, ?, ?, ?, ?, ?)";
        
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = ConnectionFactory.getConnection();
            statement = con.prepareStatement(sql);
            statement.setInt(1, task.getId_project());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.getIsCompleted());
            statement.setString(5, task.getNotes());
            //Trocar pra getDeadline depois
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate(7, new Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
            statement.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao salvar tarefa " 
            + ex.getMessage(), ex);
        } finally {
            ConnectionFactory.closeConnection(con, statement);
               
        }
        
    }
    
    public void update(Task task){
        
        String sql = "UPDATE tasks SET id_project = ?, name = ?, description = ?,"
                + "notes = ?, completed = ?, deadline = ?, createdAt = ?,"
                + "updatedAt = ? WHERE idTasks = ?";
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = ConnectionFactory.getConnection();
            statement = con.prepareStatement(sql);
            statement.setInt(1, task.getId_project());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setString(4, task.getNotes());
            statement.setBoolean(5, task.getIsCompleted());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate(7, new Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
            statement.setInt(9, task.getId());
            statement.execute();
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar tarefa " 
            + ex.getMessage(), ex);
        } finally {
            ConnectionFactory.closeConnection(con, statement);
        }
        
    }
    
    public void deleteById(int taskId){
        
        String sql = "DELETE FROM tasks WHERE idTasks = ?";
        
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = ConnectionFactory.getConnection();
            statement = con.prepareStatement(sql);
            statement.setInt(1, taskId);
            statement.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao deletar tarefa" 
                    + ex.getMessage(), ex);
        }finally{
            ConnectionFactory.closeConnection(con, statement);
        }
  
        
    }
    
    public List<Task> getAll(int idProject){
        
        String sql = "SELECT * FROM tasks WHERE id_project = ?";
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        
        List<Task> tasks = new ArrayList<>();
        try {
            con = ConnectionFactory.getConnection();
            statement = con.prepareStatement(sql);
            statement.setInt(1, idProject);
            rs = statement.executeQuery();
            
            while (rs.next()){
                Task task = new Task();
                task.setId(rs.getInt("idTasks"));
                task.setId_project(rs.getInt("id_project"));
                task.setName(rs.getString("name"));
                task.setDescription(rs.getString("description"));
                task.setNotes(rs.getString("notes"));
                task.setIsCompleted(rs.getBoolean("completed"));
                task.setDeadline(rs.getDate("deadline"));
                task.setCreatedAt(rs.getDate("createdAt"));
                task.setUpdatedAt(rs.getDate("updatedAt"));
                tasks.add(task);
            }
            
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar a tarefa" 
                    + ex.getMessage(), ex);
        } finally {
            ConnectionFactory.closeConnection(con, statement, rs);
        }
        
        return tasks;
    }
    
}
