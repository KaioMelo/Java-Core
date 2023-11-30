package application;

import db.DB;
import db.DbException;
import db.DbIntegrityException;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Program {
    public static void main(String[] args) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Connection connect = null;
        Statement st = null;
        ResultSet rs = null;
        PreparedStatement pst = null;

        try{
            connect = DB.getConnection();
            // RECUPERAÇÃO DE DADOS
            st = connect.createStatement();
            rs = st.executeQuery("SELECT * FROM department");
            while(rs.next()){
                System.out.println(rs.getInt("Id") + ", " + rs.getString("Name"));
            }
            // INSERIR DADOS
            pst = connect.prepareStatement(
                    "INSERT INTO seller "
                    + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
                    + "VALUES"
                    + "(?, ?, ?, ?, ?);",
                    Statement.RETURN_GENERATED_KEYS
            );
            pst.setString(1, "Joana Darc");
            pst.setString(2, "ju2b@gmail.com");
            pst.setDate(3, new java.sql.Date(sdf.parse("22/04/1995").getTime()));
            pst.setDouble(4, 3000.00);
            pst.setInt(5, 4);

            int rowsAffected = pst.executeUpdate();
            if(rowsAffected > 0){
                rs = pst.getGeneratedKeys();
                while (rs.next()){
                    int id = rs.getInt(1);
                    System.out.println("Done! Id = "+ id);
                }
            }else{
                System.out.println("No rown affected!");
            }
            // ATUALIZAR
            pst = connect.prepareStatement(
                    "UPDATE seller "
                    + "SET BaseSalary = BaseSalary + ? "
                    + "WHERE "
                    + " DepartmentId = ?"
            );
            pst.setDouble(1, 200.00);
            pst.setInt(2, 2);

            rowsAffected = pst.executeUpdate();
            System.out.println("Done! Rows affected: "+ rowsAffected);
            // DELETAR
            pst = connect.prepareStatement(
                    "DELETE FROM seller "
                    + "WHERE "
                    + "Id = ?"
            );

            pst.setInt(1, 10);
            rowsAffected = pst.executeUpdate();
            System.out.println("Done! Rows affected: "+ rowsAffected);
            // TRANSAÇÕES
            connect.setAutoCommit(false);
            int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE departmentId = 1");
            int x = 1;
            if(x < 2){
                throw new SQLException("Fake error");
            }
            int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE departmentId = 2");
            connect.commit();
        }
        catch (SQLException e){
            try{
                connect.rollback();
                throw new DbException("Transaction rolled back! Caused by: "+ e.getMessage());
            }
            catch (SQLException e1){
                throw new DbException("Error trying to rollback! caused by"+ e1.getMessage());
            }
        }
        catch (ParseException e){
            e.printStackTrace();
        }
        finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
            DB.closeStatement(pst);
            DB.closeConnection();
        }

    }
}
