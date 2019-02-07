package com.example.this_user.ourproject5778_9075_4711_02.model.backend;

/**
 * Created by This_user on 25/03/2018.
 */

public class DataSourceFactory
{

    public DataSourceFactory(){}
    protected static BackEndMySQL backEnd = null;
    public static BackEndInterface getBackEnd()
    {
        if(backEnd == null )
        {
               backEnd = new BackEndMySQL();
        }
        return backEnd;
    }

}
