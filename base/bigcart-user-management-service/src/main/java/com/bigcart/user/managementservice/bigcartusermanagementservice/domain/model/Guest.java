package com.bigcart.user.managementservice.bigcartusermanagementservice.domain.model;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Entity
@DynamicUpdate
public class Guest extends Person{

}
