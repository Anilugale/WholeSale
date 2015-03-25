package com.anilugale.wholesale.pojo;

/**

  Created by AnilU on 25-03-2015.
 */
public class Vendor
{
    private int id;
    private String username;
    private String name;
    private String password;
    private String contact;

    public int getId ()
    {
        return id;
    }

    public void setId (int id)
    {
        this.id = id;
    }

    public String getUsername ()
    {
        return username;
    }

    public void setUsername (String username)
    {
        this.username = username;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getPassword ()
    {
        return password;
    }

    public void setPassword (String password)
    {
        this.password = password;
    }

    public String getContact ()
    {
        return contact;
    }

    public void setContact (String contact)
    {
        this.contact = contact;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", username = "+username+", isactive = "+"name = "+name+", password = "+password+", contact = "+contact+"]";
    }
}