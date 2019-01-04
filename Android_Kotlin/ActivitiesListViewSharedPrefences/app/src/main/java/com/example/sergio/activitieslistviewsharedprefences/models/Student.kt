package com.example.sergio.activitieslistviewsharedprefences.models

import android.os.Parcel
import android.os.Parcelable

/*
* Parcel es un contenedor para un mensaje.
* Puede ser usado para mandar un objeto serializado a travez de Intent.
* Mejor rendimiento que Serializer.
* Parcelable es una interfaz que serializa el objeto, lo deserealiza y vuelve a serializar para poder mandarlo en un
* intent.
* */

data class Student(var name: String, var lastName: String, var age: Int, var isDeveloper: Boolean = true) : Parcelable {

    //Constructor. Leer en el mismo orden que se declaran las propiedades de la clase
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte()) //Para valores booleanos. 1 true, 0 false

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        //Tenemos que escribir los valores en el mismo orden en el que leemos en el constructor
        parcel.writeString(name)
        parcel.writeString(name)
        parcel.writeInt(age)
        parcel.writeByte(if (isDeveloper) 1 else 0)
    }

    //Describe el tipo de contenido del objeto parcelable
    //0 siempre se usa, el otro valor es 1 o CONTENTS_FILE_DESCRIPTOR, para tipos de objetos que implementa un File Descriptor
    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<Student>{
        //Esto creara el objeto desde el paarcel llamando al constructor secundario
        override fun createFromParcel(parcel: Parcel) = Student(parcel)

        //Esto ayudara a serializar arrays de objetos del mismo tipo de esta clase (Student)
        override fun newArray(size: Int) = arrayOfNulls<Student>(size)

    }

}