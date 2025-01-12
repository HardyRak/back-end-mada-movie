package com.spring.hard.DTO;

import com.spring.hard.models.Bouquet;

public class AbonnementDTO {
    Bouquet bouquet;
    String ref;

    public Bouquet getBouquet() {
        return bouquet;
    }
    public void setBouquet(Bouquet bouquet) throws Exception {
        if (bouquet==null) {
            throw new Exception("Choisissez un bouquet");
        }
        this.bouquet = bouquet;
    }
    public String getRef() {
        return ref;
    }
    public void setRef(String ref) throws Exception {
        if(ref.isEmpty() || ref==""){
            throw new Exception("Ajoute le reference de votre transaction");
        }
        this.ref = ref;
    }
}