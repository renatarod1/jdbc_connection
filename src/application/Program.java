package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Program {

	static final String DATABASE_URL = "jdbc:mysql://localhost/coursejdbc";
	
	public static void main(String[] args) {
		
		Connection connection = null; //gerencia a conexão
		Statement statement = null; //instrução de consulta
		ResultSet resultSet = null; //gerencia resultados
		
		try {
			
			//Estabelece a conexão com o banco de dados
			connection = DriverManager.getConnection(DATABASE_URL, "root", "");
			
			//Cria um statement para consultar o banco de dados;
			statement = connection.createStatement();
			
			//Consulta o banco de dados
			resultSet = statement.executeQuery(
					"SELECT Id, Name, Email, BaseSalary from seller");
			
			//Processa o resultado da consulta
			/*
			ResultSetMetaData metaData = resultSet.getMetaData();
			int numberOfColumns = metaData.getColumnCount();
			
			System.out.println("Vendedores cadastrados na Tabela seller:");
			
			for(int i = 1; i <= numberOfColumns; i++)
				System.out.printf("%-18s\t", metaData.getColumnName(i));
				System.out.println();
				
			while(resultSet.next()) {
				for (int i = 1; i <= numberOfColumns; i++) {
					System.out.printf("%-18s\t", resultSet.getObject(i));
				}
				System.out.println();
			}
			*/
			
			System.out.println("Vendedores:");
			System.out.println("Id, Nome, Email, Salario Base");
			while (resultSet.next()) {
				System.out.println(resultSet.getInt("Id") +
						", "+ resultSet.getString("Name") +
						", "+ resultSet.getString("Email") +
						", "+ resultSet.getDouble("BaseSalary")
						);
				
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} finally { //Assegura que o resultSet, a instrução e a conexão sejam fechados			
			try {
				resultSet.close();
				statement.close();
				connection.close();
			} catch (Exception e) {				
				e.printStackTrace();
			}			
		}
		
	}
	
}
