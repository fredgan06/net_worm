package cn.g_open.net_worm.db.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Ip4Info implements Serializable
{
    private static final long serialVersionUID = 7955159362191663122L;

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Integer ip_1;

    @Column
    private Integer ip_2;

    @Column
    private Integer ip_3;

    @Column
    private Integer ip_4_start;

    @Column
    private Integer ip_4_end;

    @Column
    private String address;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Integer getIp_1()
    {
        return ip_1;
    }

    public void setIp_1(Integer ip_1)
    {
        this.ip_1 = ip_1;
    }

    public Integer getIp_2()
    {
        return ip_2;
    }

    public void setIp_2(Integer ip_2)
    {
        this.ip_2 = ip_2;
    }

    public Integer getIp_3()
    {
        return ip_3;
    }

    public void setIp_3(Integer ip_3)
    {
        this.ip_3 = ip_3;
    }

    public Integer getIp_4_start()
    {
        return ip_4_start;
    }

    public void setIp_4_start(Integer ip_4_start)
    {
        this.ip_4_start = ip_4_start;
    }

    public Integer getIp_4_end()
    {
        return ip_4_end;
    }

    public void setIp_4_end(Integer ip_4_end)
    {
        this.ip_4_end = ip_4_end;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

}
