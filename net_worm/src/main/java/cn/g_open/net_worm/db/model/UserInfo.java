package cn.g_open.net_worm.db.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
public class UserInfo implements Serializable
{

    private static final long serialVersionUID = -5936112053480483362L;

    @Id
    @Column(name = "id", unique = false, nullable = false)
    @GeneratedValue
    private Long id;

    @Column(nullable = true, unique = false)
    private String name;

    @Column(nullable = true, unique = false)
    private String password;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

}
