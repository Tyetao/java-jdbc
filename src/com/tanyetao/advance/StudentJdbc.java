package com.tanyetao.advance;

import com.tanyetao.common.ConnectionFactory;
import com.tanyetao.common.DBUtils;
import com.tanyetao.pojo.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentJdbc {
    public void save(Student stu) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.getConnection();

            // 设置手动提交事务
            conn.setAutoCommit(false);

            String sql = "insert into student VALUES(?,?,?,?,?)";

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, stu.getId());
            stmt.setString(2, stu.getName());
            stmt.setString(3, stu.getAddress());
            stmt.setString(4, stu.getGender());
            stmt.setInt(5, stu.getAge());

            int rows = stmt.executeUpdate();

            // 提交事务
            conn.commit();

            System.out.println("成功插入：" + rows + "次");
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                // 回滚事务
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            DBUtils.close(rs, stmt, conn);
        }

    }

    public void delete(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConnectionFactory.getConnection();
            String sql = "DELETE FROM student WHERE id=?";

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            System.out.println("成功删除：" + rows + "次");
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            DBUtils.close(null, stmt, conn);
        }
    }

    public void update(Student stu) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConnectionFactory.getConnection();
            String sql = "UPDATE student SET name=?, address=?, age=?, gender=? WHERE id=?";

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, stu.getName());
            stmt.setString(2, stu.getAddress());
            stmt.setInt(3, stu.getAge());
            stmt.setString(4, stu.getGender());
            stmt.setInt(5, stu.getId());

            int rows = stmt.executeUpdate();
            System.out.println("成功更新记录：" + rows + "次");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(null, stmt, conn);
        }
    }

    public Student queryId(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Student stu = new Student();

        try {
            conn = ConnectionFactory.getConnection();
            String sql = "SELECT id,name,address,age,gender from student WHERE id=?";

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            rs = stmt.executeQuery();
            if (rs.next()) {
                stu.setId(rs.getInt(1));
                stu.setName(rs.getString(2));
                stu.setAddress(rs.getString(3));
                stu.setAge(rs.getInt(4));
                stu.setGender(rs.getString(5));
            }

            System.out.println("成功查询一条记录" + stu);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            DBUtils.close(rs, stmt, conn);
        }

        return stu;
    }

    public List<Student> queryAll() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Student> list = new ArrayList<Student>();

        try {
            conn = ConnectionFactory.getConnection();
            String sql = "SELECT id,name,address,age,gender from student";

            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();
            while (rs.next()) {
                Student stu = new Student();
                stu.setId(rs.getInt(1));
                stu.setName(rs.getString(2));
                stu.setAddress(rs.getString(3));
                stu.setAge(rs.getInt(4));
                stu.setGender(rs.getString(5));
                list.add(stu);
            }

            System.out.println("成功查询" + list);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            DBUtils.close(rs, stmt, conn);
        }

        return list;
    }
}
