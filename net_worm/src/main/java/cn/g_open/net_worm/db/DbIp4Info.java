package cn.g_open.net_worm.db;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.g_open.net_worm.db.model.Ip4Info;

public interface DbIp4Info extends JpaRepository<Ip4Info, Long>
{
}
