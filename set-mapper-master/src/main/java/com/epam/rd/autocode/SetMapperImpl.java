package com.epam.rd.autocode;

import com.epam.rd.autocode.domain.Employee;
import com.epam.rd.autocode.domain.FullName;
import com.epam.rd.autocode.domain.Position;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class SetMapperImpl implements SetMapper<Set<Employee>> {

    @Override
    public Set<Employee> mapSet(ResultSet resultSet) {
        Set<Employee> empSet = new HashSet<>();

        try {
            while (resultSet.next()){
                Employee emp = new Employee(
                      new BigInteger(String.valueOf(resultSet.getInt(1))),
                      new FullName(resultSet.getString(2),resultSet.getString(3),resultSet.getString(4)),
                      Position.valueOf(resultSet.getString(5)),
                      LocalDate.from(resultSet.getDate(7).toLocalDate()),
                      resultSet.getBigDecimal(8),
                      insertManager(resultSet.getInt(6),resultSet)


              );

              empSet.add(emp);

            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }

        empSet.forEach(e-> System.out.println(e.toString()));

        return empSet;
    }



    public Employee insertManager(int managerId, ResultSet rs){
        Employee manager = null;
        try {
            int currentRow = rs.getRow();
            rs.beforeFirst();

            while (rs.next()){
                if (rs.getInt(1)==managerId){
                    manager = new Employee(
                            new BigInteger(String.valueOf(rs.getInt(1))),
                            new FullName(rs.getString(2),rs.getString(3),rs.getString(4)),
                            Position.valueOf(rs.getString(5)),
                            LocalDate.from(rs.getDate(7).toLocalDate()),
                            rs.getBigDecimal(8),
                            insertManager(rs.getInt(6),rs)


                    );
                }


            }
            rs.absolute(currentRow);
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return manager;
    }
}

