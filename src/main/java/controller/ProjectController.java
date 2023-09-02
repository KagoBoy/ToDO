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
import model.Project;
import util.ConnectionFactory;

/**
 *
 * @author yanvi
 */
public class ProjectController {
    
    public void save(Project pj){
        
        String sql = "INSERT INTO projects (name, description,"
                + "createdAt, updatedAt)" 
                + "values (?, ?, ?, ?)";
        
        Connection con = null;
        PreparedStatement statement = null;
        
        try {
            con = ConnectionFactory.getConnection();
            statement = con.prepareStatement(sql);
            
            statement.setString(1, pj.getName());
            statement.setString(2, pj.getDescription());
            statement.setDate(3, new Date(pj.getCreatedAt().getTime()));
            statement.setDate(4, new Date(pj.getUpdatedAt().getTime()));
            statement.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao salvar projeto " 
            + ex.getMessage(), ex);
        } finally {
            ConnectionFactory.closeConnection(con, statement);
           
        }
        
    }
    
    public void update(Project pj){
        String sql = "UPDATE projects SET name = ?, description = ?,"
                + "createdAt = ?, updatedAt = ? WHERE idProject = ?";

        
        Connection con = null;
        PreparedStatement statement = null;
        
        try {
            con = ConnectionFactory.getConnection();
            statement = con.prepareStatement(sql);
            
            statement.setString(1, pj.getName());
            statement.setString(2, pj.getDescription());
            statement.setDate(3, new Date(pj.getCreatedAt().getTime()));
            statement.setDate(4, new Date(pj.getUpdatedAt().getTime()));
            statement.setInt(5, pj.getId());
            statement.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar projeto " 
            + ex.getMessage(), ex);
        } finally {
            ConnectionFactory.closeConnection(con, statement);
           
        }
    }
    
   public void deletById(int ProjectId){
       
       String sql = "DELETE FROM projects WHERE idProject = ?";
       
       Connection con = null;
       PreparedStatement statement = null;
       
       try {
           con = ConnectionFactory.getConnection();
           statement = con.prepareStatement(sql);
           statement.setInt(1, ProjectId);
           statement.execute();
       } catch (SQLException ex) {
           throw new RuntimeException("Erro ao deletar projeto " 
            + ex.getMessage(), ex);
       } finally {
           ConnectionFactory.closeConnection(con, statement);
           
       }
       
   }
   
   public List<Project> getAll(){
        String sql = "SELECT * FROM projects";
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        
        List<Project> projects = new ArrayList<>();
        
        try {
            con = ConnectionFactory.getConnection();
            statement = con.prepareStatement(sql);
            rs = statement.executeQuery();
            
            while (rs.next()){
                Project pj = new Project();
                pj.setId(rs.getInt("idProject"));
                pj.setName(rs.getString("name"));
                pj.setDescription(rs.getString("description"));
                pj.setCreatedAt(rs.getDate("createdAt"));
                pj.setUpdatedAt(rs.getDate("updatedAt"));
                projects.add(pj);
            }
       } catch (SQLException ex) {
           throw new RuntimeException("Erro ao listar projetos" 
                    + ex.getMessage(), ex);
       } finally {
            ConnectionFactory.closeConnection(con, statement, rs);
       }
      return projects;
   }
    
    
}
