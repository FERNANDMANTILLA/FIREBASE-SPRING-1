package app.firebase.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;

import app.firebase.web.common.GenericServiceAPI;
import app.firebase.web.common.GenericServiceImpl;
import app.firebase.web.model.Persona;
import app.firebase.web.model.PersonaDTO;

@Service
public class PersonaServiceImpl extends GenericServiceImpl<Persona,PersonaDTO> implements PersonaServiceAPI {

    @Autowired
    Firestore firestore;


    @Override
    public CollectionReference getCollection() {
        return firestore.collection("personas");
    }
    
}
