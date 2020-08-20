package com.example.TennisReservation.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@Entity

public class Distribution {
    
  

    @Id
    private int court; 

    private int count;



    
    public int getCourt() {
        return court;
    }

    public int getCount() {
        return count;
    }

    private void setCourt(int court) {
        
    }

    private void setCount(int count) {

    }



}