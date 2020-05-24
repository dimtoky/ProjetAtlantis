package com.atlantis.Entity;

import com.atlantis.Entity.Userdevices;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-30T17:58:01")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, Date> createdAt;
    public static volatile SingularAttribute<User, Integer> iduser;
    public static volatile SingularAttribute<User, String> password;
    public static volatile ListAttribute<User, Userdevices> userdevicesList;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, String> username;
    public static volatile SingularAttribute<User, String> token;

}