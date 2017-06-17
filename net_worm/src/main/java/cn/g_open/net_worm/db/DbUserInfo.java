package cn.g_open.net_worm.db;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.g_open.net_worm.db.model.UserInfo;

public interface DbUserInfo extends JpaRepository<UserInfo, Long>
{
    UserInfo findByName(String name);
}
