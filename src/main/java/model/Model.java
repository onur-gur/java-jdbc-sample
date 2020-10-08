package model;

import java.io.Serializable;

public abstract class Model implements Serializable {

    protected int id;

    public Model(){

    }

    public final Integer getId(){
        return this.id;
    }
    public final void setId(int id){
        this.id = id;
    }


}
