/*
 * Copyright (C) 2017 Auror
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package DataLayer.Models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Auror
 */
@Entity
public class UserModel  implements Serializable
{
    @Id @GeneratedValue
    private int id;
    private String userName;
    private String password;

    public UserModel() {
    }
    
    // <editor-fold defaultstate="collapsed" desc="Getters">
    public int getId()
    {
        return id;
    }
    
    public String getUserName()
    {
        return userName;
    }
    
    public String getPassword()
    {
        return password;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Setters">
    public void setUserName(String userName)
    {
        this.userName = userName; 
    }
    
    public void setPassword(String password)
    {
        this.password = password;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Withs">
    public UserModel withUserName(String userName)
    {
        this.userName = userName;
        return this;
    }
    
    public UserModel withPassword(String password)
    {
        this.password = password;
        return this;
    }
    // </editor-fold>
}
