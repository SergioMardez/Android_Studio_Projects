package com.example.sergio.tabsmaster.Interfaces;

import com.example.sergio.tabsmaster.Models.Person;

// Interfaz definida en fichero aparte para la comunicación entre el FormFragment
// y el ListFragment, a través del MainAcitivty
public interface OnPersonCreated {
    void createPerson(Person person);
}
