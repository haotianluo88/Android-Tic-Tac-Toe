package com.example.mad_tictactoe;

/**
 * User
 * By Janet Joy (20342054)
 */
public class User {

    private String name;
    private int resourceId;
    private GameStat history;

    public User(String pName, int pResourceId)
    {
        name = pName;
        resourceId = pResourceId;
        history = new GameStat();
    }

    public int getResourceId()
    {
        return resourceId;
    }

    public GameStat getGameStat()
    {
        return history;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name){this.name = name;}

    public void setResourceId(int id){resourceId = id;}

    public void setGameStat(GameStat gameStat){ history = gameStat;}

    public boolean isEqual(User u)
    {
        //if name and pic is same its equal
        boolean result = false;

        if (resourceId == u.getResourceId())
        {
            if(name.equals(u.getName()))
            {
                result = true;
            }
        }
        return result;
    }
}
