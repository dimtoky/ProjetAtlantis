package com.atlantis.Entity;

import com.atlantis.Entity.Devices;
import com.atlantis.Entity.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-30T17:58:01")
@StaticMetamodel(Userdevices.class)
public class Userdevices_ { 

    public static volatile SingularAttribute<Userdevices, Date> createdAt;
    public static volatile SingularAttribute<Userdevices, Integer> iduserdevices;
    public static volatile SingularAttribute<Userdevices, User> userID;
    public static volatile SingularAttribute<Userdevices, Devices> deviceID;

}