package com.atlantis.Entity;

import com.atlantis.Entity.Metrics;
import com.atlantis.Entity.Userdevices;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-30T17:58:01")
@StaticMetamodel(Devices.class)
public class Devices_ { 

    public static volatile SingularAttribute<Devices, String> deviceType;
    public static volatile SingularAttribute<Devices, Date> createdAt;
    public static volatile SingularAttribute<Devices, String> macAddress;
    public static volatile ListAttribute<Devices, Userdevices> userdevicesList;
    public static volatile ListAttribute<Devices, Metrics> metricsList;
    public static volatile SingularAttribute<Devices, Integer> iddevices;
    public static volatile SingularAttribute<Devices, String> deviceName;

}