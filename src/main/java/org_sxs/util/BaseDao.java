package org_sxs.util;

import java.sql.*;

/**
 * @ClassName: BaseDao
 * @Description: TODO
 * @Author: SXS
 * @date: 2020/11/30 14:07
 * @Version: V1.0
 */
public class BaseDao {

    protected Connection con;
    protected PreparedStatement ps;
    protected ResultSet rs;

    //声明常量
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/school";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123";

    /**
     * 连接方法
     *
     * @return
     */
    public Connection getConnection() {
        //1.加载驱动
        try {
            Class.forName(DRIVER);
            //2.连接对象
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询
     *
     * @param sql
     * @param objs
     * @return
     * @throws SQLException
     */
    public ResultSet queryQuery(String sql, Object... objs) throws SQLException {
        //1.连接对象
        con = getConnection();

        //2.获得预编译对象
        ps = con.prepareStatement(sql);

        //3.设置参数
        if (objs != null) {
            //循环多个参数
            for (int i = 0; i < objs.length; i++) {
                ps.setObject(i + 1, objs[i]);
            }
        }
        //4.获得连接
        rs = ps.executeQuery();

        //不能关闭(业务层关闭)
        return rs;
    }

    /**
     * 添加，删除，修改
     *
     * @param sql
     * @param objs
     * @return
     * @throws SQLException
     */
    public int queryUpdate(String sql, Object... objs) throws SQLException {
        int count = 0;

        //1.连接对象
        con = getConnection();

        //2.获得预编译对象
        ps = con.prepareStatement(sql);

        //3.设置参数
        if (objs != null) {
            //循环多个参数
            for (int i = 0; i < objs.length; i++) {
                ps.setObject(i + 1, objs[i]);
            }
        }
        //4.执行sql
        count = ps.executeUpdate();

        //5.关闭所有的连接
        closeAll(null, ps, con);
        return count;
    }

    /**
     * 关闭所有的连接
     *
     * @param rs
     * @param ps
     * @param con
     */
    public void closeAll(ResultSet rs, PreparedStatement ps, Connection con) {
        if (rs != null) {  //结果集对象
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (ps != null) { //预编译对象
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (con != null) {  //连接对象
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

