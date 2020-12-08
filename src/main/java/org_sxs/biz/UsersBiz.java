package org_sxs.biz;

import org_sxs.pojo.Users;
import org_sxs.util.BaseDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: UsersBiz
 * @Description: TODO
 * @Author: SXS
 * @date: 2020/11/30 14:21
 * @Version: V1.0
 */
public class UsersBiz extends BaseDao {

    /**
     * 登录
     *
     * @param users
     * @return
     */
    public int isLogin(Users users) {
        String sql = "SELECT COUNT(1) FROM users WHERE username =? AND password = ?";

        //设置值
        Object[] obj = new Object[]{users.getUsername(), users.getPassword()};
        ResultSet rs = null;

        //调用方法
        try {
            rs = this.queryQuery(sql, obj);

            //获得值
            rs.next();
            return rs.getInt(1);


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭
            this.closeAll(rs, this.ps, this.con);
        }

        return 0;
    }

    /**
     * 注册
     *
     * @param users
     * @return
     */
    public int add(Users users) {
        String sql = "insert into users (id,username,password) values(?,?,?)";

        //设置值
        Object[] obj = new Object[]{users.getId(), users.getUsername(), users.getPassword()};
        //调用方法
        try {
            return this.queryUpdate(sql, obj);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 修改
     *
     * @param users
     * @return
     */

    public int update(Users users) {
        String sql = "UPDATE users SET username = ?,password = ? WHERE id = ?";

        //设置值
        Object[] obj = new Object[]{users.getUsername(), users.getPassword(), users.getId()};
        //调用方法
        try {
            return this.queryUpdate(sql, obj);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * 删除
     *
     * @param id
     * @return
     */
    public int delete(int id) {
        String sql = "DELETE FROM users WHERE id = ?";

        //设置值
        Object[] obj = new Object[]{id};
        //调用方法
        try {
            return this.queryUpdate(sql, obj);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * 查询
     *
     * @param
     * @return
     */
    public List<Users> all() {
        //存储数据
        List<Users> list = new ArrayList<>();
        //sql语句
        String sql = "SELECT * FROM users";
        ResultSet rs = null;
        //调用方法
        try {
            rs = this.queryQuery(sql);
            while (rs.next()) {
                Users userinfo = new Users(rs.getInt(1), rs.getString(2), rs.getString(3));
                //保存到集合
                list.add(userinfo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭
            this.closeAll(rs, this.ps, this.con);
        }
        return list;
    }


}

